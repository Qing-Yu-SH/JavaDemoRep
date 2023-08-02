package com.yq.proxy.dynamicpr.cglib;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-02 13:37
 **/

/**
 * CGLIB 动态代理
 *  MethodInterceptor 接口和 Enhancer 类是核心
 *
 *  步骤：
 *  1.定义一个类
 *  2.实现 MethodInterceptor 接口，实现 intercept() 方法，用于拦截增强被代理类的方法
 *  3.定义代理对象工厂类，通过 Enhancer 类的 create() 方法创建代理类
 */
public class Main {

    public static void main(String[] args) {
        PhoneService proxy = (PhoneService) CglibProxyFactory.getProxy(PhoneService.class);
        proxy.call("1889265762");
        System.out.println();
        proxy.send("1889265762","miss you");
    }

}
