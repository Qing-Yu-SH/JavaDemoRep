package com.yq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-25 16:31
 **/
@EnableAsync
@EnableScheduling
@SpringBootApplication
public class ScheduleDemo2Application {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleDemo2Application.class);
    }

}
