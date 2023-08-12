package com.yq.pattern.bridge.channel;

import com.yq.pattern.bridge.model.IPayMode;

import java.math.BigDecimal;

/**
 * @program: JavaDemoRep
 * @description: 微信支付
 * @author: Yuqing
 * @create: 2023-08-12 13:13
 **/
public class WxPay extends Pay {

    public WxPay(IPayMode payMode) {
        super(payMode);
    }

    @Override
    public String transfer(String uId, String tradeId, BigDecimal amount) {
        logger.info("模拟微信渠道⽀付划账开始。uId：{} tradeId：{} amount：{}", uId, tradeId, amount);
        boolean security = payMode.security(uId);
        logger.info("模拟微信渠道⽀付⻛控校验。uId：{} tradeId：{} security： {}", uId, tradeId, security);
        if (!security) {
            logger.info("模拟微信渠道⽀付划账拦截。uId：{} tradeId：{} amount： {}", uId, tradeId, amount);
            return "0001";
        }
        logger.info("模拟微信渠道⽀付划账成功。uId：{} tradeId：{} amount：{}", uId, tradeId, amount);
        return "0000";
    }

}
