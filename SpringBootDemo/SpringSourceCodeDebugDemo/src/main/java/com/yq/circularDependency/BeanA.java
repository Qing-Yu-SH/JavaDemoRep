package com.yq.circularDependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: sumAll
 * @create: 2024-04-12 11:59
 **/
@Service
public class BeanA {

    @Autowired
    private BeanB beanB;

    public void log(){
        System.out.println("BeanA log");
    }

}
