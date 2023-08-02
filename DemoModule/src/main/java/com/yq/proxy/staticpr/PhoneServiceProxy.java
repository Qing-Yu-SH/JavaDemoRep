package com.yq.proxy.staticpr;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-02 12:59
 **/
public class PhoneServiceProxy implements PhoneService{

    private PhoneService phoneService;

    public PhoneServiceProxy(PhoneService phoneService) {
        this.phoneService = phoneService;
    }

    @Override
    public String call(String phoneNumber) {
        // 在调用方法前的操作
        System.out.println("before call");
        System.out.println(phoneService.call(phoneNumber));
        // 在调用方法后的操作
        System.out.println("after call");
        return null;
    }

    @Override
    public String send(String phoneNumber, String message) {
        // 在调用方法前的操作
        System.out.println("before send");
        System.out.println(phoneService.send(phoneNumber,message));
        // 在调用方法后的操作
        System.out.println("after send");
        return null;
    }

}
