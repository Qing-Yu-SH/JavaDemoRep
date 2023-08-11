package com.yq.pattern.singleton;

/**
 * @program: JavaDemoRep
 * @description: 枚举方式 - 线程安全
 *               枚举在Java中是天然的单例，所以不需要担心线程安全问题
 *               使用枚举实现的单例不会被反序列化破坏，因为枚举的序列化和反序列化由 JVM 保证
 *               不适用于需要延迟初始化的情况，或者需要更复杂的初始化操作
 * @author: Yuqing
 * @create: 2023-08-11 10:47
 **/
public enum Singleton_07 {

    INSTANCE;

    public void doSomething(){
        System.out.println("业务执行操作..");
    }

}
