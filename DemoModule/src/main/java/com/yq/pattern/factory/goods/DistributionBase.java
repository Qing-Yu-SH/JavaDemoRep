package com.yq.pattern.factory.goods;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-02 15:15
 **/
public class DistributionBase {

    protected Logger logger = LoggerFactory.getLogger(DistributionBase.class);

    protected void updateUserAwardState(String uid){
        logger.info("模拟用户 {} 的奖品发货落库",uid);
    }

}
