package com.yq.config;

import com.yq.service.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-01-08 17:13
 **/
@Configuration
public class WebConfig {

    @Bean
    public User user2(){
        return new User(2,"yq2","123456");
    }

}
