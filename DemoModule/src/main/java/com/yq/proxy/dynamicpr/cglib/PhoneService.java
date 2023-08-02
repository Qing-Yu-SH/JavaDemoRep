package com.yq.proxy.dynamicpr.cglib;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-02 13:31
 **/
public class PhoneService {

    public String call(String phoneNumber) {
        System.out.println("正在拨打：" + phoneNumber);
        return "已打通";
    }

    public String send(String phoneNumber, String message) {
        System.out.println("正在发送：" + message + "\n to：" + phoneNumber);
        return "已发送";
    }

}
