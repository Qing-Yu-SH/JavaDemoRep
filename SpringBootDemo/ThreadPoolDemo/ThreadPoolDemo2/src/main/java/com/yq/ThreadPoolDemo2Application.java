package com.yq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-24 22:50
 **/
@EnableAsync
@SpringBootApplication
public class ThreadPoolDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(ThreadPoolDemo2Application.class);
    }

}
