package com.yq.mq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-16 19:11
 **/
@RequiredArgsConstructor
public class DelayMessageProcessor implements MessagePostProcessor {


    private final int delay;

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties().setDelay(delay);
        return message;
    }

}
