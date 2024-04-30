package com.yq.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: sumAll
 * @create: 2024-04-15 15:08
 **/
public class LifecycleBean implements InitializingBean, DisposableBean, BeanNameAware, BeanFactoryAware, ApplicationContextAware {

    private String name;

    public LifecycleBean(){
        System.out.println("1.调⽤构造⽅法：我出⽣了！");
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
        System.out.println("2.设置属性：我的名字叫" + name);
    }

    @Override
    public void setBeanName(String s) {
        System.out.println("3.调⽤ BeanNameAware#setBeanName ⽅法:我要上学了，起了个学名");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("4.调⽤ BeanFactoryAware#setBeanFactory ⽅法：选好学校了");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("5.调⽤ ApplicationContextAware#setApplicationContext ⽅法：购买上学所需用品");
    }

    // 6.BeanPostProcessor#postProcessBeforeInitialization

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("7.调用 InitializingBean#afterPropertiesSet ⽅法：⼊学登记");
    }

    public void init() {
        System.out.println("8.⾃定义init⽅法：努⼒上学ing");
    }

    // 9.BeanPostProcessor#postProcessAfterInitialization

    @Override
    public void destroy() throws Exception {
        System.out.println("10.DisposableBean#destroy⽅法：平淡的⼀⽣落幕了");
    }

    public void destroyMethod() {
        System.out.println("11.⾃定义destroy⽅法:睡了，别想叫醒我");
    }

    public void work(){
        System.out.println("Bean使⽤中：⼯作，只有对社会没有⽤的⼈才放假。。");
    }


}