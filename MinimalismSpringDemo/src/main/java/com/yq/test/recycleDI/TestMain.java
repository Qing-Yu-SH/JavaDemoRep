package com.yq.test.recycleDI;

import com.yq.spring.YuApplicationContext;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 12:25
 **/
public class TestMain {

    public static void main(String[] args) {
        YuApplicationContext applicationContext = new YuApplicationContext(RecycleConfig.class);
        Object1 obj1 = (Object1) applicationContext.getBean("obj1");
    }
}
