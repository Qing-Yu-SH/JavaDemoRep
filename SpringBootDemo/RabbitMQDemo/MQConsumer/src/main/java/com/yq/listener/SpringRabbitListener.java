package com.yq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-16 18:33
 **/
@Slf4j
@Component
public class SpringRabbitListener {


    //    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueueMessage(String msg) throws InterruptedException{
        log.info("spring 消费者接收到消息：[ {} ]",msg);
//        if(true){
//            throw new MessageConversionException("故意");
//        }
        log.info("消息处理完成!");
    }

    /**
     * 创建一个队列 work.queue
     * 消息被发送到指定的队列中；一个消息只能被一个消费者消费
     */
//    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue(String msg) throws InterruptedException {
        log.info("消费者1 收到了 work.queue 的消息：[ {} ]",msg);
        Thread.sleep(20);
    }

    //    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue2(String msg) throws InterruptedException {
        log.info("消费者2 收到了 work.queue 的消息：[ {} ]",msg);
        Thread.sleep(200);
    }

    /**
     * 声明两个队列 fanout.queue1 和 fanout.queue2
     * 声明交换机 ks.fanout，类型为 fanout
     * 消息会被发送给所有绑定到该交换机上的队列中
     */
//    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg){
        log.info("消费者1 收到了 fanout.queue1 的消息：[ {} ]",msg);
    }

    //    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg){
        log.error("消费者2 收到了 fanout.queue2 的消息：[ {} ]",msg);
    }

    /**
     * 声明两个队列 direct.queue1 和 direct.queue2
     * 声明交换机 ks.direct，类型为 direct
     * 将两个队列绑定到交换机上
     *       direct.queue1 Routing key：red
     *       direct.queue1 Routing key：black
     *       direct.queue2 Routing key：red
     *       direct.queue2 Routing key：grey
     */
//    @RabbitListener(queues = "direct.queue1")
    public void listenDirectQueue1(String msg){
        log.info("消费者1 收到了 direct.queue1 的消息：[ {} ]",msg);
    }

    //    @RabbitListener(queues = "direct.queue2")
    public void listenDirectQueue2(String msg){
        log.error("消费者2 收到了 direct.queue2 的消息：[ {} ]",msg);
    }

    /**
     * 声明两个队列 topic.queue1 和 topic.queue2
     * 声明交换机 ks.topic，类型为 topic
     * 将两个队列绑定到交换机上
     *       topic.queue1 Routing key：china.#
     *       topic.queue2 Routing key：#.news
     */
//    @RabbitListener(queues = "topic.queue1")
    public void listenTopicQueue1(String msg){
        log.info("消费者1 收到了 topic.queue1 的消息：[ {} ]",msg);
    }

    //    @RabbitListener(queues = "topic.queue2")
    public void listenTopicQueue2(String msg){
        log.error("消费者2 收到了 topic.queue2 的消息：[ {} ]",msg);
    }

    /**
     * 通过注解的方式声明队列、交换机和绑定
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue8",durable = "true"),
            exchange = @Exchange(name = "ks.direct2",type = ExchangeTypes.DIRECT),
            key = {"aa","bb"}
    ))
    public void listenDirectQueue8(String msg){
        log.info("消费者1 收到了 direct.queue8 的消息：[ {} ]",msg);
    }


    @RabbitListener(queuesToDeclare = @Queue(
            name = "lazy.queue",
            durable = "true",
            arguments = @Argument(name = "x-queue-mode", value = "lazy")
    ))
    public void listensLazyQueue(String msg){
        log.info("消费者1 收到了 lazy.queue 的消息：[ {} ]",msg);
    }


    /**
     * 设置延迟的交换机，方式 2
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "delay.queue",durable = "true"),
            exchange = @Exchange(name = "delay.direct",delayed = "true"),
            key = "delay" // 路由键
    ))
    public void listensDelayMessage(String msg){
        log.info("接收到 delay.queue 的延迟消息：{}", msg);
    }


}
