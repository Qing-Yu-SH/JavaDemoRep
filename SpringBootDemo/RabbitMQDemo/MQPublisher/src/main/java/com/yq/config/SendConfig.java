package com.yq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @program: JavaDemoRep
 * @description: 配置发送回调
 * @author: Yuqing
 * @create: 2023-10-16 18:27
 **/
@Slf4j
@Configuration
public class SendConfig  implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    /**
     * 设置 ReturnCallback, 只设置一个
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        rabbitTemplate.setReturnsCallback((returnedMessage) -> {
            log.info("消息发送失败, 应答码 {}, 原因 {}, 交换机{}, 路由键{}, 消息{}",
                    returnedMessage.getReplyCode(), returnedMessage.getReplyText(),
                    returnedMessage.getExchange(), returnedMessage.getRoutingKey(),
                    returnedMessage.getMessage());
        });
    }

}
