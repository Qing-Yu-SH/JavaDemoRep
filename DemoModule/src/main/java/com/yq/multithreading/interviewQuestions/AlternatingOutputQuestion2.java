package com.yq.multithreading.interviewQuestions;

import java.util.concurrent.CountDownLatch;

/**
 * @program: JavaDemoRep
 * @description: 交替输出 —— 两个线程交替输出内容
 *               通过 synchronized、notify、wait 实现
 *               wait 会释放锁，并阻塞自己；因此要先 notify，才 wait，不然无法执行 notify
 * @author: Yuqing
 * @create: 2023-09-03 16:26
 **/
public class AlternatingOutputQuestion2 {

    // 控制两个线程交替执行的顺序
    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) {
        final Object o = new Object();

        char[] a1 = "1234567".toCharArray();
        char[] a2 = "ABCDEFG".toCharArray();

        new Thread(() -> {

            try {
                // 等待
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (o){
                for(char c: a1){
                    System.out.print(c);

                    try {
                        // 唤醒阻塞线程
                        o.notify();
                        // 释放锁
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 必须，否则无法终止程序
                // 整个程序的执行过程是两个线程交替执行的，所以到最后，一定存在一个线程处于 wait 状态
                // 在最后再执行一次 notify，能够使 wait 的线程唤醒，完成最后程序的执行
                o.notify();
            }

        },"t1").start();

        new Thread(() -> {

            synchronized (o){
                for(char c: a2){
                    System.out.print(c);
                    // t2 线程输出字符后，才可以让 t1 线程往下执行
                    latch.countDown();

                    try {
                        // 唤醒阻塞线程
                        o.notify();
                        // 释放锁
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                o.notify();
            }

        },"t2").start();

    }

}
