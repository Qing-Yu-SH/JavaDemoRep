package com.yq.pattern.observer.event.listener;

import com.yq.pattern.observer.LotteryResult;

/**
 * @program: JavaDemoRep
 * @description: 事件监听接口
 * @author: Yuqing
 * @create: 2023-08-07 20:02
 **/
public interface EventListener {

    void doEvent(LotteryResult result);

}
