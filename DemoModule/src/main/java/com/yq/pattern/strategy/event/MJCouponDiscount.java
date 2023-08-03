package com.yq.pattern.strategy.event;

import com.yq.pattern.strategy.ICouponDiscount;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: 满减
 * @author: Yuqing
 * @create: 2023-08-03 14:12
 **/
public class MJCouponDiscount implements ICouponDiscount<Map<String, String>> {

    /**
     * 满减计算
     * 1. 判断满⾜x元后-n元，否则不减
     * 2. 最低⽀付⾦额1元
     */
    @Override
    public BigDecimal discountAmount(Map<String, String> couponInfo, BigDecimal skuPrice) {
        String x = couponInfo.get("x");
        String n = couponInfo.get("n");

        // ⼩于商品⾦额条件的，直接返回商品原价
        if(skuPrice.compareTo(new BigDecimal(x)) < 0) return skuPrice;

        // 减去优惠⾦额判断
        BigDecimal discountAmount = skuPrice.subtract(new BigDecimal(n));
        if(discountAmount.compareTo(BigDecimal.ZERO) < 1) return BigDecimal.ONE;
        return discountAmount;
    }

}
