package com.yq.pattern.strategy;

import java.math.BigDecimal;

/**
 * @program: JavaDemoRep
 * @description: 优惠券接⼝
 * @author: Yuqing
 * @create: 2023-08-03 14:10
 **/
public interface ICouponDiscount<T> {

    /**
     * 优惠券⾦额计算
     * @param couponInfo  券折扣信息；直减、满减、折扣、N元购
     * @param skuPrice sku⾦额
     * @return 优惠后⾦额
     */
    BigDecimal discountAmount(T couponInfo,BigDecimal skuPrice);

}
