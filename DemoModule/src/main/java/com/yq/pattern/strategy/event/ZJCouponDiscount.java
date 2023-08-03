package com.yq.pattern.strategy.event;

import com.yq.pattern.strategy.ICouponDiscount;

import java.math.BigDecimal;

/**
 * @program: JavaDemoRep
 * @description: 直减
 * @author: Yuqing
 * @create: 2023-08-03 14:20
 **/
public class ZJCouponDiscount implements ICouponDiscount<Double> {

    /**
     * 直减计算
     * 1. 使⽤商品价格减去优惠价格
     * 2. 最低⽀付⾦额1元
     */
    @Override
    public BigDecimal discountAmount(Double couponInfo, BigDecimal skuPrice) {
        BigDecimal discountAmount = skuPrice.subtract(new BigDecimal(couponInfo));
        if(discountAmount.compareTo(BigDecimal.ZERO) < 1) return BigDecimal.ONE;
        return discountAmount;
    }

}
