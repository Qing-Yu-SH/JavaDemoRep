package com.yq.pattern.strategy.event;

import com.yq.pattern.strategy.ICouponDiscount;

import java.math.BigDecimal;

/**
 * @program: JavaDemoRep
 * @description: N元购
 * @author: Yuqing
 * @create: 2023-08-03 14:24
 **/
public class NYGCouponDiscount implements ICouponDiscount<Double> {

    /**
     * n元购购买
     * 1. ⽆论原价多少钱都固定⾦额购买
     */
    @Override
    public BigDecimal discountAmount(Double couponInfo, BigDecimal skuPrice) {
        return new BigDecimal(couponInfo);
    }

}
