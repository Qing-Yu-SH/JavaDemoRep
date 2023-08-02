package com.yq.proxy.staticpr;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-02 13:01
 **/

/**
 * 静态代理步骤：
 * 1.定义相应接口和实现类
 * 2.定义代理类，实现该接口，并将实现类注入代理类，在相应方法中增加扩展功能
 */
public class Main {

    public static void main(String[] args) {
        PhoneService phoneService = new PhoneServiceImpl();
        PhoneServiceProxy serviceProxy = new PhoneServiceProxy(phoneService);
        serviceProxy.call("1889265762");
        System.out.println();
        serviceProxy.send("1889265762","等你");
    }

}
