package com.yq.pattern.strategy;

import java.math.BigDecimal;

/**
 * @program: JavaDemoRep
 * @description: 策略控制类
 * @author: Yuqing
 * @create: 2023-08-03 14:52
 **/
public class Context<T> {

    private ICouponDiscount<T> couponDiscount;
    public Context(ICouponDiscount<T> couponDiscount) {
        this.couponDiscount = couponDiscount;
    }
    public BigDecimal discountAmount(T couponInfo, BigDecimal skuPrice) {
        return couponDiscount.discountAmount(couponInfo, skuPrice);
    }

}
