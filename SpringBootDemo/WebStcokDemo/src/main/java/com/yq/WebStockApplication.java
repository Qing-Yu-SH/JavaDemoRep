package com.yq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-05 19:21
 **/
@EnableScheduling
@SpringBootApplication
public class WebStockApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebStockApplication.class);
    }

}
