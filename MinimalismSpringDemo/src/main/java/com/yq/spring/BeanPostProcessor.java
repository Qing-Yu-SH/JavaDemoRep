package com.yq.spring;

/**
 * 后置处理器
 */
public interface BeanPostProcessor {

    default Object postProcessBeforeInitialization(Object bean, String beanName){
        return bean;
    }

    default Object postProcessAfterInitialization(Object bean, String beanName){
        return bean;
    }

}
