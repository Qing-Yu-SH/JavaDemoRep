package com.yq;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: sumAll
 * @create: 2024-04-12 11:55
 **/
@SpringBootApplication
@EnableAspectJAutoProxy
public class SpringApplication {

    public static void main(String[] args) {
        org.springframework.boot.SpringApplication.run(SpringApplication.class, args);
    }

}
