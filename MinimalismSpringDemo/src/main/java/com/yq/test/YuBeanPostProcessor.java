package com.yq.test;

import com.yq.spring.BeanPostProcessor;
import com.yq.spring.Component;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 11:19
 **/
@Component
public class YuBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if(bean.getClass().isAssignableFrom(User.class)){
            System.out.println(bean + "初始化前..");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if(bean.getClass().isAssignableFrom(User.class)){
            System.out.println(bean + "初始化后..");
        }
        return bean;
    }

}
