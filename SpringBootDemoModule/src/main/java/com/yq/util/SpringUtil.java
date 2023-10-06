package com.yq.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 14:55
 **/
@Component
public class SpringUtil implements ApplicationContextAware, EnvironmentAware{
    private volatile static ApplicationContext context;
    private volatile static Environment environment;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.context = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        SpringUtil.environment = environment;
    }


    public static ApplicationContext getContext() {
        return context;
    }


    public static <T> T getBean(Class<T> clazz){
        return context.getBean(clazz);
    }

    public static <T> T getBeanOrNull(Class<T> clazz){
        try {
            return context.getBean(clazz);
        }catch (Exception e){
            return null;
        }
    }

    public static Object getBean(String beanName){
        return context.getBean(beanName);
    }

    public static Object getBeanOrNull(String beanName){
        try {
            return context.getBean(beanName);
        }catch (Exception e){
            return null;
        }
    }

    public static String getConfig(String key) {
        return environment.getProperty(key);
    }

    public static String getConfigOrElse(String mainKey, String slaveKey) {
        String ans = environment.getProperty(mainKey);
        if (ans == null) {
            return environment.getProperty(slaveKey);
        }
        return ans;
    }

    public static String getConfig(String key, String val) {
        return environment.getProperty(key, val);
    }



}
