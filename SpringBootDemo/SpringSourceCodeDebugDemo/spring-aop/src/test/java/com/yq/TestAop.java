package com.yq;

import com.yq.aop.AopService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: sumAll
 * @create: 2024-04-30 14:34
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestAop {

    @Test
    public void test_aop(){
        // 断点打在这里，进入 refresh() 方法
        ApplicationContext context =new
                ClassPathXmlApplicationContext("classpath:applicationAopContext.xml");
        AopService aopService = (AopService) context.getBean("aopService");
        aopService.think();
    }

}
