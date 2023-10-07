package com.yq.service.impl;

import com.rabbitmq.client.*;
import com.yq.common.CommonConstants;
import com.yq.common.CommonException;
import com.yq.common.StatusEnum;
import com.yq.rabbitmq.RabbitmqClient;
import com.yq.rabbitmq.RabbitmqConnection;
import com.yq.rabbitmq.RabbitmqConnectionPool;
import com.yq.service.RabbitmqService;
import com.yq.util.SpringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-06 21:27
 **/
@Slf4j
@Component
public class RabbitmqServiceImpl implements RabbitmqService {

//    @Autowired
    private RabbitmqClient rabbitmqClient;

    @Override
    public boolean enabled() {
        return "true".equalsIgnoreCase(SpringUtil.getConfig("rabbitmq.switchFlag"));
    }

    @Override
    public void publishMsg(String exchange, BuiltinExchangeType exchangeType, String toutingKey, String message) throws IOException, TimeoutException {
        try {
            RabbitmqConnection rabbitmqConnection = RabbitmqConnectionPool.getConnection();
            Connection connection = rabbitmqConnection.getConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(exchange,exchangeType,true,false,null);
            channel.basicPublish(exchange,toutingKey,null,message.getBytes());
            log.info("Publish msg：{}",message);
            channel.close();
            RabbitmqConnectionPool.returnConnection(rabbitmqConnection);
        } catch (InterruptedException | NullPointerException e) {
            e.printStackTrace();
            throw CommonException.newInstance(StatusEnum.UNKNOWN_ERROR);
        }
    }

    @Override
    public void consumerMsg(String exchange, String queueName, String routingKey) throws IOException, TimeoutException {
        try {
            RabbitmqConnection rabbitmqConnection = RabbitmqConnectionPool.getConnection();
            Connection connection = rabbitmqConnection.getConnection();
            final Channel channel = connection.createChannel();
            channel.queueDeclare(queueName,true,false,false,null);
            channel.queueBind(queueName,exchange,routingKey);
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body,"UTF-8");
                    log.info("Consumer msg：{}",message);
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            };
            // 取消自动 ack
            channel.basicConsume(queueName,false,consumer);
            RabbitmqConnectionPool.returnConnection(rabbitmqConnection);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processConsumerMsg() {
        log.info("Begin to processConsumerMsg.");

        Integer stepTotal = 1;
        Integer step = 0;

        // TODO: 这种方式非常 Low，后续会改造成阻塞 I/O 模式
        while (true) {
            step++;
            try {
                log.info("processConsumerMsg cycle.");
                consumerMsg(CommonConstants.EXCHANGE_NAME_DIRECT, CommonConstants.QUERE_NAME_PRAISE,
                        CommonConstants.QUERE_KEY_PRAISE);
                if (step.equals(stepTotal)) {
                    Thread.sleep(10000);
                    step = 0;
                }
            } catch (Exception e) {

            }
        }
    }


//    @Override
//    public void publishMsg(String exchange, BuiltinExchangeType exchangeType, String toutingKey, String message) throws IOException, TimeoutException {
//        ConnectionFactory factory = rabbitmqClient.getConnectionFactory(toutingKey);
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//        // 声明 exchange 中的消息为可持久化，不自动删除
//        channel.exchangeDeclare(exchange,exchangeType,true,false,null);
//        // 发布消息
//        channel.basicPublish(exchange,toutingKey,null,message.getBytes());
//        log.info("Publish msg：{}",message);
//        channel.close();
//        connection.close();
//    }
//
//    @Override
//    public void consumerMsg(String exchange, String queue, String routingKey) throws IOException, TimeoutException {
//        ConnectionFactory factory = rabbitmqClient.getConnectionFactory(routingKey);
//        Connection connection = factory.newConnection();
//        final Channel channel = connection.createChannel();
//        // 消息队列
//        channel.queueDeclare(queue,true,false,false,null);
//        // 绑定队列到交换机
//        channel.queueBind(queue,exchange,routingKey);
//
//        Consumer consumer = new DefaultConsumer(channel){
//            @Override
//            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                String message = new String(body,"UTF-8");
//                log.info("Consumer msg：{}",message);
//                channel.basicAck(envelope.getDeliveryTag(),false);
//            }
//        };
//        // 取消自动 ack
//        channel.basicConsume(queue,false,consumer);
//    }
//
//    @Override
//    public void processConsumerMsg() {
//        log.info("Begin to processConsumerMsg");
//
//        Integer stepTotal = 1;
//        Integer step = 0;
//
//        while(true){
//            step++;
//            try {
//                log.info("processConsumerMsg cycle.");
//                consumerMsg(CommonConstants.EXCHANGE_NAME_DIRECT,CommonConstants.QUERE_NAME_PRAISE,CommonConstants.QUERE_KEY_PRAISE);
//                if(step.equals(stepTotal)){
//                    log.info("thread sleep..");
//                    Thread.sleep(100000);
//                    step = 0;
//                }
//            } catch (IOException | TimeoutException | InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }

}
