package com.yq.pattern.bridge.channel;

import com.yq.pattern.bridge.model.IPayMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * @program: JavaDemoRep
 * @description: 支付方式接口
 * @author: Yuqing
 * @create: 2023-08-12 13:11
 **/
public abstract class Pay {

    protected Logger logger = LoggerFactory.getLogger(Pay.class);

    protected IPayMode payMode;

    public Pay(IPayMode payMode) {
        this.payMode = payMode;
    }

    public abstract String transfer(String uId, String tradeId, BigDecimal amount);

}
