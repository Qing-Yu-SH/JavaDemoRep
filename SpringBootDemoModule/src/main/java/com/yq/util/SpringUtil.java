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
public class SpringUtil implements ApplicationContextAware {
    private volatile static ApplicationContext context;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.context = applicationContext;
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


}
