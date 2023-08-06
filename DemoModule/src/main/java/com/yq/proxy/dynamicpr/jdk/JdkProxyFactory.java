package com.yq.proxy.dynamicpr.jdk;

import java.lang.reflect.Proxy;

/**
 * @program: JavaDemoRep
 * @description: 代理对象的工厂类
 * @author: Yuqing
 * @create: 2023-08-02 13:20
 **/
public class JdkProxyFactory {

    public static Object getProxy(Object target){
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),  // 目标类的类加载
                target.getClass().getInterfaces(),   // 代理需要实现的接口，可指定多个
                new DebugInvocationHandler(target)   // 代理对象对应的自定义 InvocationHandler
        );
    }

}