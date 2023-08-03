package com.yq.pattern.strategy.event;

import com.yq.pattern.strategy.ICouponDiscount;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @program: JavaDemoRep
 * @description: 折扣
 * @author: Yuqing
 * @create: 2023-08-03 14:22
 **/
public class ZKCouponDiscount implements ICouponDiscount<String> {

    /**
     * 折扣计算
     * 1. 使⽤商品价格乘以折扣⽐例，为最后⽀付⾦额
     * 2. 保留两位⼩数
     * 3. 最低⽀付⾦额1元
     */
    @Override
    public BigDecimal discountAmount(String couponInfo, BigDecimal skuPrice) {
        BigDecimal discountAmount = skuPrice.multiply(new BigDecimal(couponInfo)).setScale(2, RoundingMode.UP);
        if(discountAmount.compareTo(BigDecimal.ZERO) < 1) return BigDecimal.ONE;
        return discountAmount;
    }

}
