package com.yq.proxy.dynamicpr.jdk;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-02 13:22
 **/

/**
 * JDK 动态代理
 * 只能代理实现了接口的类
 *
 * 步骤：
 * 1.定义接口和实现类
 * 2.自定义 InvocationHandler，并重写 invoke() 方法
 * 3.通过 Proxy.newProxyInstance() 方法创建代理对象
 */
public class Main {

    public static void main(String[] args) {
        PhoneService proxy = (PhoneService) JdkProxyFactory.getProxy(new PhoneServiceImpl());
        proxy.call("1889265762");
        System.out.println();
        proxy.send("1889265762","等你");
    }
}
