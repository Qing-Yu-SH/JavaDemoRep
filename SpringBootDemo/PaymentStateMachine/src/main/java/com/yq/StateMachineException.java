package com.yq;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-01-15 10:21
 **/
public class StateMachineException extends RuntimeException{

    private PaymentStatus currentStatus;
    private PaymentEvent event;
    private String description;

    public StateMachineException(PaymentStatus currentStatus, PaymentEvent event, String description) {
        super(description);
        this.currentStatus = currentStatus;
        this.event = event;
        this.description = description;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("当前状态：").append(currentStatus.getDescription()).append("；")
                .append("触发事件：").append(event.getDescription()).append("；")
                .append("异常原因：").append(description);
        return builder.toString();
    }

}
