package com.yq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-16 18:30
 **/

@Slf4j
@Configuration
@ConditionalOnProperty(value = "spring.rabbitmq.listener.simple.retry.enable", havingValue = "true")
public class ErrorExchangeConfiguration {

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("error.direct");
    }

    @Bean
    public Queue errorQueue(){
        return new Queue("error.queue");
    }

    @Bean
    public Binding errorBinding(Queue errorQueue, DirectExchange directExchange){
        return BindingBuilder.bind(errorQueue).to(directExchange).with("error");
    }

    @Bean
    public MessageRecoverer republishMessageRecoverer(RabbitTemplate rabbitTemplate){
        log.info("RepublishMessageRecoverer 配置成功..");
        return new RepublishMessageRecoverer(rabbitTemplate, "error.direct", "error");
    }

}
