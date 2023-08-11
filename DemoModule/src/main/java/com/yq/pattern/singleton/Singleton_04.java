package com.yq.pattern.singleton;

/**
 * @program: JavaDemoRep
 * @description: 使用类的内部类 - 线程安全
 *               既保证了线程安全又保证了懒加载，同时不会因为加锁的⽅式耗费性能
 *               静态内部类的实例化过程是延迟加载的，从而实现了懒加载
 *               静态内部类的初始化由 Java 虚拟机保证线程安全，从而实现了线程安全
 *               推荐的方式
 * @author: Yuqing
 * @create: 2023-08-11 10:06
 **/
public class Singleton_04 {

    private static class SingletonHolder{
        private static Singleton_04 instance = new Singleton_04();
    }

    private Singleton_04(){
    }

    public static Singleton_04 getInstance(){
        return SingletonHolder.instance;
    }

}
