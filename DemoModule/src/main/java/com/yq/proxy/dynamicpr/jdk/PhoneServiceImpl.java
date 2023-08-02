package com.yq.proxy.dynamicpr.jdk;



/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-02 13:12
 **/
public class PhoneServiceImpl implements PhoneService {

    @Override
    public String call(String phoneNumber) {
        System.out.println("正在拨打：" + phoneNumber);
        return "已打通";
    }

    @Override
    public String send(String phoneNumber, String message) {
        System.out.println("正在发送：" + message + "\n to：" + phoneNumber);
        return "已发送";
    }

}
