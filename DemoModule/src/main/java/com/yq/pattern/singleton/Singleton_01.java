package com.yq.pattern.singleton;

/**
 * @program: JavaDemoRep
 * @description: 懒汉模式 - 线程不安全
 *               单例模式不允许外部创建，所以构造函数由 private 修饰
 *               该模式实现了懒加载；但是当有多个线程同时执行时，会造成多个实例的存在
 *               例如多个线程在 instance 没有被实例化时，都执行了第一行代码，进入了实例化的代码，之后这些线程都会实例化一个 Singleton_01
 * @author: Yuqing
 * @create: 2023-08-11 09:54
 **/
public class Singleton_01 {

    private static Singleton_01 instance;

    private Singleton_01(){
    }

    public static Singleton_01 getInstance(){
        if(instance != null) return instance;
        instance = new Singleton_01();
        return instance;
    }

}
