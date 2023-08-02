package com.yq.pattern.factory;

import com.yq.pattern.factory.goods.IDistributionGoods;
import com.yq.pattern.factory.goods.impl.CouponGoods;
import com.yq.pattern.factory.goods.impl.PhysicalGoods;
import com.yq.pattern.factory.goods.impl.RedeemCodeGoods;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: 工厂
 * @author: Yuqing
 * @create: 2023-08-02 15:26
 **/
public class DistributionGoodsFactory {

    private static Map<Integer, IDistributionGoods> goods = new HashMap<Integer, IDistributionGoods>(){{
        put(1,new CouponGoods());
        put(2,new PhysicalGoods());
        put(3,new RedeemCodeGoods());
    }};

    public static IDistributionGoods getDistributionGoods(Integer type){
        return goods.get(type);
    }

}
