package com.yq.pattern.bridge.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: JavaDemoRep
 * @description: 指纹识别
 * @author: Yuqing
 * @create: 2023-08-12 13:19
 **/
public class PayFingerprintMode implements IPayMode{

    private Logger logger = LoggerFactory.getLogger(PayFingerprintMode.class);

    @Override
    public boolean security(String uId) {
        logger.info("用户 {} 指纹识别中..",uId);
        return true;
    }

}
