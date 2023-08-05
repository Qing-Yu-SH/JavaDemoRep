package com.yq.pattern.adapter.mq;

import com.alibaba.fastjson.JSON;

import java.util.Date;

/**
 * @program: JavaDemoRep
 * @description: 内部订单MQ
 * @author: Yuqing
 * @create: 2023-08-05 15:00
 **/
public class OrderMq {

    private String uid; // ⽤户ID
    private String sku; // 商品
    private String orderId; // 订单ID
    private Date createOrderTime; // 下单时间

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getCreateOrderTime() {
        return createOrderTime;
    }

    public void setCreateOrderTime(Date createOrderTime) {
        this.createOrderTime = createOrderTime;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
