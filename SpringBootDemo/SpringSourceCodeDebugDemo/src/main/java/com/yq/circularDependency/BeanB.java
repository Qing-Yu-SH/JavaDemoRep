package com.yq.circularDependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: sumAll
 * @create: 2024-04-12 12:00
 **/
@Service
public class BeanB {

    @Autowired
    private BeanA beanA;

    public void log(){
        System.out.println("BeanB log");
    }

}
