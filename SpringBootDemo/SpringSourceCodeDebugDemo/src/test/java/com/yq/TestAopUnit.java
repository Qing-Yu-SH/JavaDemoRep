package com.yq;

import com.yq.aop.AopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

    @Test
    public void test_aop(){
        ApplicationContext context =new
                ClassPathXmlApplicationContext("classpath:applicationAopContext.xml");
        AopService aopService = (AopService) context.getBean("aopService");
        aopService.think();
    }

}
