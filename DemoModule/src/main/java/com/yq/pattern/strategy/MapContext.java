package com.yq.pattern.strategy;

import com.yq.pattern.strategy.constant.Constants;
import com.yq.pattern.strategy.event.MJCouponDiscount;
import com.yq.pattern.strategy.event.NYGCouponDiscount;
import com.yq.pattern.strategy.event.ZJCouponDiscount;
import com.yq.pattern.strategy.event.ZKCouponDiscount;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: 策略控制类 - Map 结构
 * @author: Yuqing
 * @create: 2023-08-03 14:33
 **/
public class MapContext {

    private static Map<Constants.Discount,ICouponDiscount> discountMap = new HashMap<Constants.Discount,ICouponDiscount>(){{
        put(Constants.Discount.MJCoupon,new MJCouponDiscount());
        put(Constants.Discount.NYGCoupon,new NYGCouponDiscount());
        put(Constants.Discount.ZJCoupon,new ZJCouponDiscount());
        put(Constants.Discount.ZKCoupon,new ZKCouponDiscount());
    }};

    public static ICouponDiscount getCouponDiscount(Constants.Discount discount){
        return discountMap.get(discount);
    }

}
