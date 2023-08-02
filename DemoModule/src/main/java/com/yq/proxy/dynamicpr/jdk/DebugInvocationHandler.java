package com.yq.proxy.dynamicpr.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @program: JavaDemoRep
 * @description: 自定义 InvocationHandler
 * @author: Yuqing
 * @create: 2023-08-02 13:17
 **/
public class DebugInvocationHandler implements InvocationHandler {

    // 代理类中的真实对象
    private final Object target;

    public DebugInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 代理对象在调用方法的时候，实际会调用到实现InvocationHandler 接口的类的 invoke()方法
     * @param proxy 动态生成的代理类
     * @param method 与代理类对象调用的方法相对应
     * @param args 当前 method 方法的参数
     * @return 方法执行后的返回值
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //调用方法之前，我们可以添加自己的操作
        System.out.println("before method " + method.getName());
        Object result = method.invoke(target, args);
        //调用方法之后，我们同样可以添加自己的操作
        System.out.println("after method " + method.getName());
        return result;
    }

}
