package com.yq.pattern.bridge.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: JavaDemoRep
 * @description: 密码校验
 * @author: Yuqing
 * @create: 2023-08-12 13:20
 **/
public class PayCypherMode implements IPayMode{

    private Logger logger = LoggerFactory.getLogger(PayFingerprintMode.class);

    @Override
    public boolean security(String uId) {
        logger.info("用户 {} 密码校验中..",uId);
        return true;
    }

}
