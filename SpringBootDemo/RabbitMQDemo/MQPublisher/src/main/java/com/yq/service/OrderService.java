package com.yq.service;

import cn.hutool.core.util.RandomUtil;
import com.yq.constants.MqConstants;
import com.yq.domain.MultiDelayMessage;
import com.yq.mq.DelayMessageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: JavaDemoRep
 * @description: 订单服务
 * @author: Yuqing
 * @create: 2023-10-16 19:04
 **/
@Slf4j
@Service
public class OrderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Long createOrder(){
        Long id = RandomUtil.randomLong(5);

        log.info("创建订单..");

        // 业务操作


        // 发送延迟检查订单状态消息
        try {
            MultiDelayMessage<Long> delayMessage = MultiDelayMessage.of(id, 10000L, 10000L, 15000L, 15000L, 50000L);
            rabbitTemplate.convertAndSend(MqConstants.DELAY_EXCHANGE,
                    MqConstants.DELAY_ORDER_ROUTING_KEY,
                    delayMessage, new DelayMessageProcessor(delayMessage.removeNextDelay().intValue()));
            log.info("发送延迟订单消息成功..");
        }catch (Exception e){
            log.error("延迟消息发送异常：{}", e.getMessage());
        }
        return id;
    }


}
