package com.yq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-24 19:05
 **/

@EnableAsync
@SpringBootApplication
public class ThreadPoolDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(ThreadPoolDemo1Application.class);
    }

}


