package com.yq.domain;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @program: JavaDemoRep
 * @description: 延迟消息
 * @author: Yuqing
 * @create: 2023-10-16 18:56
 **/
@Data
public class MultiDelayMessage<T> {

    // 消息体
    private T data;

    // 记录延迟时间的集合
    private List<Long> delayMillis;

    // jackson 的反序列化需要无参构造函数
    public MultiDelayMessage() {
    }

    public MultiDelayMessage(T data, List<Long> delayMillis) {
        this.data = data;
        this.delayMillis = delayMillis;
    }

    public static <T> MultiDelayMessage<T> of(T data, Long... delayMills){
        return new MultiDelayMessage<>(data, CollUtil.newArrayList(delayMills));
    }

    // 获取并一处下一个延迟时间
    public Long removeNextDelay(){
        return delayMillis.remove(0);
    }

    // 是否还有下一个延迟时间
    public boolean hasNextDelay(){
        return !delayMillis.isEmpty();
    }

}
