package com.yq.pattern.observer;

import com.yq.pattern.observer.event.EventManager;
import com.yq.pattern.observer.event.listener.MQEventListener;
import com.yq.pattern.observer.event.listener.MessageEventListener;

/**
 * @program: JavaDemoRep
 * @description: 业务抽象类接⼝
 * @author: Yuqing
 * @create: 2023-08-07 20:15
 **/
public abstract class LotteryService {

    private EventManager eventManager;

    public LotteryService() {
        eventManager = new EventManager(EventManager.EventType.MQ, EventManager.EventType.Message);
        eventManager.subscribe(EventManager.EventType.MQ, new MQEventListener());
        eventManager.subscribe(EventManager.EventType.Message, new MessageEventListener());
    }

    public LotteryResult draw(String uId){
        LotteryResult result = doDraw(uId);
        // 需要什么通知就给调⽤什么⽅法
        eventManager.notify(EventManager.EventType.MQ, result);
        eventManager.notify(EventManager.EventType.Message, result);
        return result;
    }

    /**
     * 提供抽奖操作；定义为抽象方法，让类的继承者实现
     * 同时⽅法的定义使⽤的是 protected，也就是保证将来外部的调⽤⽅不会调⽤到此⽅法
     */
    protected abstract LotteryResult doDraw(String uId);

}
