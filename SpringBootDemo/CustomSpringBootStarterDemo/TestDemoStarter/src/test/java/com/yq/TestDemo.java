package com.yq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-18 15:45
 **/
@RunWith ( SpringRunner. class)
@SpringBootTest
public class TestDemo {

    @Autowired
    HelloProperties helloProperties;

    @Test
    public void test_hello(){
        System.out.println(helloProperties.getName());
    }


}
