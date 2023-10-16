package com.yq;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-16 18:19
 **/
@SpringBootApplication
public class MQPublisherApplication {

    public static void main(String[] args) {
        SpringApplication.run(MQPublisherApplication.class);
    }

    @Bean
    public MessageConverter jacksonMessageConverter(){
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        // 此处设置为 true 时，当将 JSON 转化按为对象时，从 MessageProperties 中获取 id 为 null，则调用 UUID 设置一个唯一的 id
        converter.setCreateMessageIds(true);
        return converter;
    }

}
