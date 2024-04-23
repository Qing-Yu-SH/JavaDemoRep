package com.yq;

import com.yq.aop.AopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: sumAll
 * @create: 2024-04-23 11:21
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAopUnit {

    @Autowired
    private AopService aopService;

    @Test
    public void test_aop(){
        aopService.think();
    }

}
