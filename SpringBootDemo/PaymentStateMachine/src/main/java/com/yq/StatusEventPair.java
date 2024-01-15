package com.yq;

import java.util.Objects;

/**
 * @program: JavaDemoRep
 * @description: 状态事件对，指定的状态只能接受指定的事件
 * @author: Yuqing
 * @create: 2024-01-15 09:57
 **/
public class StatusEventPair<S extends BaseStatus, E extends BaseEvent> {

    private final S status;

    private final E event;

    public StatusEventPair(S status, E event) {
        this.status = status;
        this.event = event;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof StatusEventPair) {
            StatusEventPair<S, E> other = (StatusEventPair<S, E>)obj;
            return this.status.equals(other.status) && this.event.equals(other.event);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, event);
    }

}
