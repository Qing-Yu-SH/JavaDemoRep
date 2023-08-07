package com.yq.pattern.observer.event.listener;

import com.yq.pattern.observer.LotteryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: JavaDemoRep
 * @description: 短消息事件
 * @author: Yuqing
 * @create: 2023-08-07 20:03
 **/
public class MessageEventListener implements EventListener{

    private Logger logger = LoggerFactory.getLogger(MessageEventListener.class);

    @Override
    public void doEvent(LotteryResult result) {
        logger.info("给⽤户 {} 发送短信通知(短信)：{}", result.getUId(), result.getMsg());
    }

}
