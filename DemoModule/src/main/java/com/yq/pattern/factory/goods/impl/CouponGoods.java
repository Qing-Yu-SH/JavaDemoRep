package com.yq.pattern.factory.goods.impl;

import com.yq.pattern.factory.goods.DistributionBase;
import com.yq.pattern.factory.goods.IDistributionGoods;
import com.yq.pattern.factory.res.DistributionRes;

/**
 * @program: JavaDemoRep
 * @description: 优惠券
 * @author: Yuqing
 * @create: 2023-08-02 15:19
 **/
public class CouponGoods extends DistributionBase implements IDistributionGoods {

    @Override
    public DistributionRes doDistribution(String uid) {
        logger.info("模拟给用户 {} 发放优惠券",uid);
        updateUserAwardState(uid);
        return new DistributionRes(uid,200,"成功");
    }

}
