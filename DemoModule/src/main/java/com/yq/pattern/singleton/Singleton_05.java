package com.yq.pattern.singleton;

/**
 * @program: JavaDemoRep
 * @description: 双重锁校验 - 线程安全
 *               双᯿锁的⽅式是⽅法级锁的优化，减少了部分获取实例的耗时
 * @author: Yuqing
 * @create: 2023-08-11 10:16
 **/

/**
 * instance 判空需要经历两次：
 *      假设线程 A 执行了第一个判空，准备获取锁，此时 CPU 时间到，让出 CPU
 *      线程 B 得到 CPU，执行方法，并顺利初始化
 *      然后线程 A 获取 CPU 后，继续上次的执行，获取锁进入代码块；如果此时不判空，线程 A 会再次初始化，导致程序中存在多个实例
 *
 * instance 需要被 volatile 修饰，volatile 能够防止代码重排：
 * instance = new Singleton_05(); 这行代码不是原子性操作，它会分为三部分执行
 *      1）分配内存空间
 *      2）初始化 Singleton_05
 *      3）将 instance 指向分配的内存
 * 代码重排使得上面三部分的执行顺序不是固定的，可能是 1-3-2
 *      假设线程 A 执行了初始化代码，由于代码重排，执行了 1-3 步骤，还未执行步骤 2，让出了 CPU，此时 instance 已经非空
 *      此时线程 B 也获取该实例，由于 instance!=null，所以直接返回 instance，但是 Singleton_05 还未进行初始化
 */
public class Singleton_05 {

    private static volatile Singleton_05 instance;

    private Singleton_05(){
    }

    public static Singleton_05 getInstance(){
        if(instance == null){
            synchronized (Singleton_05.class){
                if(instance == null){
                    instance = new Singleton_05();
                }
            }
        }
        return instance;
    }


}
