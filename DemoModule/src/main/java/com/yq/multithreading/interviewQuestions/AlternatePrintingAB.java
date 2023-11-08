package com.yq.multithreading.interviewQuestions;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @program: JavaDemoRep
 * @description:  问题：三个线程循环打印 ABC
 *                CyclicBarrier 指回环屏障，可以让一组线程全部达到一个状态后再全部同时执行；当所有等待线程执行完毕后，并重置 CyclicBarrier 的状态后它可以被重用
 *
 * @author: sumAll
 * @create: 2023-11-08 09:13
 **/
public class AlternatePrintingAB {

    // 共享计数器
    private static int sharedCounter = 0;

    public static void main(String[] args) {
        // 打印的内容
        String printString = "ABC";
        // 定义循环栅栏
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3, () -> {
        });
        // 执行任务
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < printString.length(); i++) {
                    synchronized (this) {
                        sharedCounter = sharedCounter > 2 ? 0 : sharedCounter;
                        System.out.println(printString.toCharArray()[sharedCounter++]);
                    }
                    try {
                        // 等待 3 个线程都打印一遍之后，继续走下一轮的打印
                        cyclicBarrier.await();
                    } catch (BrokenBarrierException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        // 开启多个线程
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

}
