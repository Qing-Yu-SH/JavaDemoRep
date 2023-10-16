package com.yq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-16 18:32
 **/
@Configuration
public class MQConfiguration {

    // 声明交换机、队列和绑定

    @Bean
    public FanoutExchange fanoutExchange(){
        // ExchangeBuilder.fanoutExchange("").build();
        return new FanoutExchange("ks.fanout2");
    }

    @Bean
    public Queue fanoutQueue3(){
        // QueueBuilder.durable("").build();
        return new Queue("fanout.queue3");
    }

    @Bean
    public Binding fanoutBinding(Queue fanoutQueue3, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue3).to(fanoutExchange);
    }

    @Bean
    public Queue fanoutQueue4(){
        // QueueBuilder.durable("").build();
        return new Queue("fanout.queue4");
    }

    @Bean
    public Binding fanoutBinding2(){
        // fanoutExchange()：会先从 Spring 中找是否存在 bean，存在直接方法该 bean；因为这个是配置类中的方法，会被 Spring 管理
        return BindingBuilder.bind(fanoutQueue4()).to(fanoutExchange());
    }

    @Bean
    public Queue lazyQueue(){
        return QueueBuilder
                .durable("lazy.queue")
                .lazy()  // 开启 lazy 模式
                .build();
    }

    /**
     * 设置延迟交换机，方式 1
     */
    @Bean
    public DirectExchange delayExchange(){
        return ExchangeBuilder
                .directExchange("delay.direct2")
                .delayed()      // 设置 delay 属性为 true
                .durable(true)
                .build();
    }



}
