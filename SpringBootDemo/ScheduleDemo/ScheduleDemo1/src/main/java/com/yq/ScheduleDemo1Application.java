package com.yq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-25 11:54
 **/


@EnableScheduling
@SpringBootApplication
public class ScheduleDemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleDemo1Application.class);
    }

}




