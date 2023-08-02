package com.yq.pattern.factory;

import com.alibaba.fastjson.JSON;
import com.yq.pattern.factory.goods.IDistributionGoods;
import com.yq.pattern.factory.res.DistributionRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-02 15:30
 **/

/**
 * 简单工厂方法
 * 为所有的奖品定义统一的接口，并通过工厂类根据 id 获取对应的奖品
 *
 * 优点：
 * 1.避免创建者与具体的产品逻辑耦合，满足单一职责
 * 2.每⼀个业务逻辑实现都在所属⾃⼰的类中完成，满足开闭原则
 * 3.⽆需更改使用调用方就可以在程序中引⼊新的产品类型
 *
 * 缺点：
 * 1.实现的子类会极速扩张
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        // 1.发放优惠券
        IDistributionGoods coupon = DistributionGoodsFactory.getDistributionGoods(1);
        DistributionRes res = coupon.doDistribution("user01");
        logger.info(JSON.toJSON(res).toString());

        // 2.发放实物
        IDistributionGoods physical = DistributionGoodsFactory.getDistributionGoods(2);
        DistributionRes res1 = physical.doDistribution("user02");
        logger.info(JSON.toJSON(res1).toString());

        // 3.发放兑换码
        IDistributionGoods redeem = DistributionGoodsFactory.getDistributionGoods(3);
        DistributionRes res2 = redeem.doDistribution("user06");
        logger.info(JSON.toJSON(res2).toString());
    }

}
