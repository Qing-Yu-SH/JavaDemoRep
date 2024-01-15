package com.yq;

/**
 * @program: JavaDemoRep
 * @description: 支付事件
 * @author: Yuqing
 * @create: 2024-01-15 10:06
 **/
public enum PaymentEvent implements BaseEvent{


    PAY_CREATE("PAY_CREATE", "支付创建"),
    PAY_PROCESS("PAY_PROCESS", "支付中"),
    PAY_SUCCESS("PAY_SUCCESS", "支付成功"),
    PAY_FAIL("PAY_FAIL", "支付失败");

    // 事件
    private final String event;
    // 事件描述
    private final String description;

    PaymentEvent(String event, String description) {
        this.event = event;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
