package com.yq.pattern.observer.event;

import com.yq.pattern.observer.LotteryResult;
import com.yq.pattern.observer.event.listener.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description: 事件处理类
 * @author: Yuqing
 * @create: 2023-08-07 20:07
 **/
public class EventManager {

    HashMap<Enum<EventType>, List<EventListener>> listeners = new HashMap<>();

    public EventManager(Enum<EventType>... operations) {
        for (Enum<EventType> operation : operations) {
            this.listeners.put(operation, new ArrayList<>());
        }
    }

    public enum EventType{
        MQ,Message
    }

    /**
     * 订阅
     * @param eventType  事件类型
     * @param listener   监听
     */
    public void subscribe(Enum<EventType> eventType,EventListener listener){
        listeners.get(eventType).add(listener);
    }

    /**
     * 取消订阅
     * @param eventType  事件类型
     * @param listener   监听
     */
    public void unsubscribe(Enum<EventType> eventType,EventListener listener){
        listeners.get(eventType).remove(listener);
    }

    /**
     * 通知
     * @param eventType  事件类型
     * @param result     监听
     */
    public void notify(Enum<EventType> eventType, LotteryResult result){
        for(EventListener listener: listeners.get(eventType)){
            listener.doEvent(result);
        }
    }

}
