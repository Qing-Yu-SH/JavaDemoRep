package com.yq.pattern.bridge.model;

/**
 * @program: JavaDemoRep
 * @description: 支付模式接口
 * @author: Yuqing
 * @create: 2023-08-12 13:12
 **/
public interface IPayMode {

    boolean security(String uId);

}
