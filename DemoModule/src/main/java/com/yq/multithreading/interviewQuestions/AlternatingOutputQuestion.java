package com.yq.multithreading.interviewQuestions;

import java.util.concurrent.locks.LockSupport;

/**
 * @program: JavaDemoRep
 * @description: 交替输出 —— 两个线程交替输出内容
 *               通过 LockSupport 实现
 *               假设 t1 先执行，当执行代码 unpark() 时，t2 还未启动；此时并不会存在问题
 *               因为 LockSupport 设置了一个标志位；当 t2 启动并调用 park() 时，通过标志位发现已经被 unpark() 了，则这次不阻塞
 * @create: 2023-09-03 16:14
 **/
public class AlternatingOutputQuestion {

    static Thread t1 = null,t2 = null;

    public static void main(String[] args) {
        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();

        t1 = new Thread(() -> {
            for(char c: a1){
                System.out.print(c);
                LockSupport.unpark(t2);  // 叫醒 t2
                LockSupport.park();      // 阻塞 t1
            }
        },"t1");

        t2 = new Thread(() -> {
            for(char c: a2){
                LockSupport.park();     // 阻塞 t2
                System.out.print(c);
                LockSupport.unpark(t1); // 叫醒 t1
            }
        },"t2");

        t1.start();
        t2.start();
    }

}
