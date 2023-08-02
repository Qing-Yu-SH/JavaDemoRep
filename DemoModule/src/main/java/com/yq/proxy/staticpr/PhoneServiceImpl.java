package com.yq.proxy.staticpr;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-02 12:57
 **/
public class PhoneServiceImpl implements PhoneService{

    @Override
    public String call(String phoneNumber) {
        System.out.println("正在拨打：" + phoneNumber);
        return "已打通";
    }

}
