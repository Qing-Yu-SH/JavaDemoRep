package com.yq.pattern.strategy;

import com.yq.pattern.strategy.constant.Constants;
import com.yq.pattern.strategy.event.NYGCouponDiscount;
import com.yq.pattern.strategy.event.ZKCouponDiscount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-03 14:47
 **/

/**
 * 策略模式
 * 策略模式的控制类主要是外部可以传递不同的策略实现，在通过统⼀的⽅法执⾏优惠策略计算
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // 直减；100-10，商品100元
        BigDecimal discountAmount = MapContext.getCouponDiscount(Constants.Discount.ZJCoupon).discountAmount(10D, new BigDecimal(100));
        logger.info("测试结果：直减优惠后⾦额 {}", discountAmount);

        // 满100减10，商品100元
        Map<String,String> mapReq = new HashMap<String, String>();
        mapReq.put("x","100");
        mapReq.put("n","10");
        BigDecimal discountAmount2 = MapContext.getCouponDiscount(Constants.Discount.MJCoupon).discountAmount(mapReq, new BigDecimal(100));
        logger.info("测试结果：满减优惠后⾦额 {}", discountAmount2);

        // 折扣9折，商品100元
        Context<String> context = new Context<String>(new ZKCouponDiscount());
        BigDecimal discountAmount3 = context.discountAmount("0.9", new BigDecimal(100));
        logger.info("测试结果：折扣9折后⾦额 {}", discountAmount3);

        // n元购；100-10，商品100元
        Context<Double> context2 = new Context<>(new NYGCouponDiscount());
        BigDecimal discountAmount4 = context2.discountAmount(90D, new BigDecimal(100));
        logger.info("测试结果：n元购优惠后⾦额 {}", discountAmount4);
    }

}
