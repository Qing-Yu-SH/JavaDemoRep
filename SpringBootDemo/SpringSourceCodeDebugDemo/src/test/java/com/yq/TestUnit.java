package com.yq;

import com.yq.lifecycle.LifecycleBean;
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
 * @create: 2024-04-15 15:25
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestUnit {

    @Test
    public void test_lifecycle(){
        // debug bean 生命周期；端点打在这里，从这里进入 refresh() 方法，进入创建 bean 的流程
        ApplicationContext context =new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        LifecycleBean lifecycleBean = (LifecycleBean) context.getBean("lifecycleBean");
        lifecycleBean.work();
        ((ClassPathXmlApplicationContext) context).close();
    }


}
