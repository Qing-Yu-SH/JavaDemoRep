package com.yq;

import com.yq.service.UserInfoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: sumAll
 * @create: 2024-04-30 15:34
 **/
public class TestTransaction {

    public static void main(String[] args) throws Exception {
        String xmlPath = "applicationContext.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(xmlPath);
        UserInfoService userInfoService = (UserInfoService) applicationContext.getBean("userInfoService");
        userInfoService.testSuccess();
    }

}
