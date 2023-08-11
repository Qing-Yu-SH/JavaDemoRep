package com.yq.pattern.singleton;

/**
 * @program: JavaDemoRep
 * @description: 懒汉模式 - 线程安全
 *               通过在方法上加 synchronized 锁，实现每次只有一个线程调用该方法，从而达到线程安全
 *               由于把锁加到了方法上，导致性能差
 * @author: Yuqing
 * @create: 2023-08-11 09:59
 **/
public class Singleton_02 {

    private static Singleton_02 instance;

    private Singleton_02(){
    }

    public static synchronized Singleton_02 getInstance(){
        if(instance!=null) return instance;
        instance = new Singleton_02();
        return instance;
    }

}
