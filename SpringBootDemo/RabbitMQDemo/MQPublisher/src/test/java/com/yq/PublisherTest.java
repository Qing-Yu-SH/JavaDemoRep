package com.yq;

import cn.hutool.core.lang.UUID;
import com.yq.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.nio.charset.StandardCharsets;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-16 18:20
 **/
@Slf4j
@SpringBootTest
public class PublisherTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderService orderService;

    @Test
    public void test_sendMessage2Queue(){
        String queueName = "simple.queue";
        String msg = "hello,amqp!";
        rabbitTemplate.convertAndSend(queueName,msg);
    }

    @Test
    public void test_workQueue() throws InterruptedException {
        String queueName = "work.queue";
        for(int i=0;i<50;i++){
            String msg = "hello, worker, message_" + i;
            rabbitTemplate.convertAndSend(queueName,msg);
            Thread.sleep(20);
        }
    }

    @Test
    public void test_sendFanout() throws InterruptedException {
        String exchangeName = "ks.fanout";
        for(int i=0;i<50;i++){
            String msg = "hello, worker, message_" + i;
            rabbitTemplate.convertAndSend(exchangeName, null, msg);
        }
        Thread.sleep(10000);
    }


    @Test
    public void test_sendDirectRoutingKeyRed() {
        // 两个队列都收到了
        String exchangeName = "ks.direct";
        String msg = "routing key：red";
        rabbitTemplate.convertAndSend(exchangeName,"red",msg);
    }

    @Test
    public void test_sendDirectRoutingKeyBlack() {
        // direct.queue1 队列收到了
        String exchangeName = "ks.direct";
        String msg = "routing key：black";
        rabbitTemplate.convertAndSend(exchangeName,"black",msg);
    }

    @Test
    public void test_sendDirectRoutingKeyNull() {
        // 没有队列收到
        String exchangeName = "ks.direct";
        String msg = "routing key：NULL";
        rabbitTemplate.convertAndSend(exchangeName,"",msg);
    }

    @Test
    public void test_sendTopicRoutingKey(){
        // topic.queue1 收到了消息
        String exchangeName = "ks.topic";
        String msg = "routing key：china.topic";
        rabbitTemplate.convertAndSend(exchangeName,"china.topic",msg);
    }

    @Test
    public void test_sendTopicRoutingKey2(){
        // topic.queue2 收到了消息
        String exchangeName = "ks.topic";
        String msg = "routing key：shanghai.news";
        rabbitTemplate.convertAndSend(exchangeName,"shanghai.news",msg);
    }

    @Test
    public void test_sendTopicRoutingKey3(){
        // 两个队列都收到了消息
        String exchangeName = "ks.topic";
        String msg = "routing key：china.news";
        rabbitTemplate.convertAndSend(exchangeName,"china.news",msg);
    }

    @Test
    public void test_confirmCallback() throws InterruptedException {
        // 1.创建 cd，需要指定唯一 id
        CorrelationData cd = new CorrelationData(UUID.randomUUID().toString());
        // 2.添加 ConfirmCallback，每发送一个消息都可以添加一个回调
        cd.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("消息回调失败，{}",ex.toString());
            }

            @Override
            public void onSuccess(CorrelationData.Confirm result) {
                log.debug("收到 confirm callback 回执..");
                if(result.isAck()){
                    // 消息发送成功
                    log.debug("消息发送成功，收到 ack");
                }else {
                    // 消息发送失败
                    log.debug("消息发送失败，收到 nack，原因 {}", result.getReason());
                }
            }
        });
        String exchangeName = "ks.topic";
        String msg = "routing key：china2.news2";
        rabbitTemplate.convertAndSend(exchangeName,"china2.news2",msg, cd);
        Thread.sleep(2000);
    }

    @Test
    public void test_persistentMsg(){
        String exchangeName = "ks.direct";
        Message message = MessageBuilder
                .withBody("hello,msg".getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
        rabbitTemplate.convertAndSend(exchangeName, message);
    }

    @Test
    public void test_sendTTLMessage(){
        Message message = MessageBuilder
                .withBody("hello".getBytes(StandardCharsets.UTF_8))
                .setExpiration("10000")
                .build();
        rabbitTemplate.convertAndSend("simple.direct","hi", message);
    }


    @Test
    public void test_sendTTLMessage2(){
        rabbitTemplate.convertAndSend("simple.direct", "hi", "hello", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                MessageProperties messageProperties = message.getMessageProperties();
                messageProperties.setExpiration("10000");
                return message;
            }
        });
    }

    @Test
    public void test_sendDelayMsg(){
        String message = "hello,delay queue";
        rabbitTemplate.convertAndSend("delay.direct", "delay", message, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                // 添加延迟消息属性
                message.getMessageProperties().setDelay(10000);
                return message;
            }
        });
    }

    @Test
    public void test_createOrder(){
        orderService.createOrder();
    }

}
