package com.yq.pattern.bridge.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: JavaDemoRep
 * @description: 人脸支付
 * @author: Yuqing
 * @create: 2023-08-12 13:17
 **/
public class PayFaceMode implements IPayMode{

    private Logger logger = LoggerFactory.getLogger(PayFaceMode.class);

    @Override
    public boolean security(String uId) {
        logger.info("用户 {} 人脸识别中..",uId);
        return true;
    }

}
