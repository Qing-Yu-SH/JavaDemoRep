package com.yq.pattern.singleton;

/**
 * @program: JavaDemoRep
 * @description: 饿汉模式 - 线程安全
 *               与 Singleton_00 原理相同；在加载时，就初始化对象；在使用时，直接返回该对象
 *               这种方式不是懒加载，可能导致启动缓慢，以及占用大量内存
 * @author: Yuqing
 * @create: 2023-08-11 10:02
 **/
public class Singleton_03 {

    private static Singleton_03 instance = new Singleton_03();

    private Singleton_03(){
    }

    public static Singleton_03 getInstance(){
        return instance;
    }

}
