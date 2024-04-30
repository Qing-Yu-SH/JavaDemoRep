package com.yq;

import com.yq.bean.BeanA;
import com.yq.bean.BeanB;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: sumAll
 * @create: 2024-04-30 13:49
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCircularDependency {

    @Autowired
    private BeanA beanA;

    @Autowired
    private BeanB beanB;

    /**
     * 将断点打在 AbstractApplicationContext 的 refresh() 方法
     */
    @Test
    public void test_circularDependency(){
        beanA.log();
        beanB.log();
    }

}
