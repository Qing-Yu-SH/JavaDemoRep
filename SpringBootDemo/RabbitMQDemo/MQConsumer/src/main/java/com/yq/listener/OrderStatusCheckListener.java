package com.yq.listener;

import com.yq.constants.MqConstants;
import com.yq.domain.MultiDelayMessage;
import com.yq.mq.DelayMessageProcessor;
import com.yq.service.Order;
import com.yq.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-16 19:15
 **/
@Slf4j
@Component
public class OrderStatusCheckListener {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = MqConstants.DELAY_ORDER_QUEUE,durable = "true"),
            exchange = @Exchange(value = MqConstants.DELAY_EXCHANGE,delayed = "true",type = ExchangeTypes.TOPIC),
            key = MqConstants.DELAY_ORDER_ROUTING_KEY
    ))
    public void listenOrderDelayMessage(MultiDelayMessage<Long> message){
        // 1.查询订单状态
        // 2.判断订单是否已经支付
        //     2.1 已支付，标记订单状态为已支付
        //     2.2 未支付，获取下次订单延迟时间
        // 3.判断是否存在延迟时间
        //     3.1 存在，重发延迟消息
        //     3.2 不存在，取消订单
        // 4.恢复库存

//        log.info("开始处理订单延迟消息..");

        Long id = message.getData();
        // 1.查询订单状态
        Order order = orderService.getById(id);
        // 2.判断订单是否已经支付
        if(order==null || order.getStatus()==2){
            // 订单不存在或者已经被处理
            return;
        }

        // 3.查询订单的支付状态
        boolean isPay = false;
        if(isPay){
            //     3.1 已支付，标记订单状态为已支付
            orderService.markOrderPaySuccess(order.getId());
            return;
        }

        // 4.判断是否存在延迟时间
        if(message.hasNextDelay()){
            //     4.1 存在，重发延迟消息
            Long nextDelay = message.removeNextDelay();
            rabbitTemplate.convertAndSend(MqConstants.DELAY_EXCHANGE,
                    MqConstants.DELAY_ORDER_ROUTING_KEY,
                    message,
                    new DelayMessageProcessor(nextDelay.intValue()));
            log.info("存在延迟时间，重新发送延迟消息..");
        }else{
            log.info("不存在延迟时间，取消订单..");
            //     4.2 不存在，取消订单
            orderService.cancelOrder(order.getId());
        }

        // 5.恢复库存
//        log.info("处理订单延迟消息结束..");
    }

}
