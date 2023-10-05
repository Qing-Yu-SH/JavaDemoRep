package com.yq.spring;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 09:14
 **/
public class YuApplicationContext {

    private Class configClass;

    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();

    private Map<String,Object> singletonObjects = new HashMap<>();

    private List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    public YuApplicationContext(Class configClass) {
        this.configClass = configClass;

        scan(configClass);  // 生成 BeanDefinition

        preInstantiateSingletons();  // 实例化单例


    }

    private void preInstantiateSingletons() {
        // 扫描 beanDefinitionMap，创建单例 bean，并保存到 singletonObjects
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            BeanDefinition beanDefinition = entry.getValue();
            String beanName = entry.getKey();
            if("singleton".equals(beanDefinition.getScope())){
                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName,bean);
            }
        }
    }

    /**
     * 解析路径下的类，并封装为 BeanDefinition
     * @param configClass 配置类
     */
    private void  scan(Class configClass){
        // 解析配置类
        ComponentScan annotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
        String path = annotation.value();

        // 扫描
        //     注意：Spring 源码中并不是通过类加载器去扫描注解是否存在，而是使用了另一种技术直接解析字节码文件，无需将类加载进来
        try {
            ClassLoader classLoader = YuApplicationContext.class.getClassLoader();
            URL url = classLoader.getResource(path.replace(".","/"));
            String filePath = URLDecoder.decode(url.getFile(), "UTF-8"); // 解决路径中存在中文问题
            File file = new File(filePath);
            File[] files = file.listFiles();
            for (File f : files) {
                // User.class
                String name = f.getName();
                if(name.endsWith(".class")){
                    // user
                    name = name.substring(0,name.indexOf("."));
                    // com.test.user
                    String className = path + "." + name;
                    Class<?> clazz = classLoader.loadClass(className);
                    // 封装到 BeanDefinition
                    if(clazz.isAnnotationPresent(Component.class)){

                        if(BeanPostProcessor.class.isAssignableFrom(clazz)){
                            BeanPostProcessor o = (BeanPostProcessor) clazz.newInstance();
                            beanPostProcessorList.add(o);
                        }


                        Component component = clazz.getAnnotation(Component.class);
                        String beanName = component.value();
                        beanName = beanName==null||beanName.isEmpty() ? toLowerCase(name) : beanName;
                        BeanDefinition beanDefinition = new BeanDefinition();
                        beanDefinition.setClazz(clazz);
                        beanDefinition.setName(beanName);

                        if(clazz.isAnnotationPresent(Scope.class)){
                            Scope scope = clazz.getAnnotation(Scope.class);
                            beanDefinition.setScope(scope.value());
                        }else{
                            beanDefinition.setScope("singleton");
                        }

                        beanDefinitionMap.put(beanName,beanDefinition);
                    }
                }
            }
        }catch (UnsupportedEncodingException | ClassNotFoundException | IllegalAccessException | InstantiationException ue){
            ue.printStackTrace();
        }
    }

    private Object createBean(String beanName,BeanDefinition beanDefinition){

        try {
            Class clazz = beanDefinition.getClazz();
            Object instance = clazz.newInstance();

            // 依赖注入
            for (Field field : clazz.getDeclaredFields()) {
                if(field.isAnnotationPresent(Autowired.class)){
                    // notice：只实现了 byName 获取实例
                    // fixme：存在循环依赖的问题
                    String name = field.getName();
                    Object bean = getBean(name);

                    // required：要求 @Autowired 注入的属性不能为空
                    if(bean==null && field.getAnnotation(Autowired.class).required()){
                        throw new NullPointerException("属性不能为空");
                    }

                    field.setAccessible(true);
                    field.set(instance,bean);
                }
            }

            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
            }

            // 初始化
            if(instance instanceof InitializingBean){
                ((InitializingBean) instance).afterPropertiesSet();
            }

            for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
                instance = beanPostProcessor.postProcessAfterInitialization(instance, beanName);
            }

            return instance;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public Object getBean(String beanName){
        if(beanDefinitionMap.containsKey(beanName)){
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if("singleton".equals(beanDefinition.getScope())){
                // 单例
                return singletonObjects.get(beanName);
            }else{
                // 多例
                return createBean(beanName,beanDefinition);
            }
        }else{
            throw new NullPointerException("bean 不存在");
        }
    }

    private String toLowerCase(String beanName){
        return Character.toLowerCase(beanName.charAt(0)) + beanName.substring(1);
    }
}
