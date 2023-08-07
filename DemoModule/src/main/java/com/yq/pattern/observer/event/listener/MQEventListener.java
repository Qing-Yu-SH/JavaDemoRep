package com.yq.pattern.observer.event.listener;

import com.yq.pattern.observer.LotteryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: JavaDemoRep
 * @description: MQ 发送事件
 * @author: Yuqing
 * @create: 2023-08-07 20:04
 **/
public class MQEventListener implements EventListener{

    private Logger logger = LoggerFactory.getLogger(MQEventListener.class);

    @Override
    public void doEvent(LotteryResult result) {
        logger.info("记录⽤户 {} 摇号结果(MQ)：{}", result.getUId(), result.getMsg());
    }

}
