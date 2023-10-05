package com.yq.test;

import com.yq.spring.YuApplicationContext;

import java.io.UnsupportedEncodingException;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 09:13
 **/
public class Main {

    public static void main(String[] args){
        // Spring 测试
        YuApplicationContext applicationContext = new YuApplicationContext(AppConfig.class);
        User user = (User) applicationContext.getBean("userBean");
        User user2 = (User) applicationContext.getBean("userBean");
        System.out.println(user == user2);

        UserService userService = (UserService) applicationContext.getBean("userService");
        UserService userService2 = (UserService) applicationContext.getBean("userService");
        System.out.println(userService == userService2);

        userService.test();

    }

}
