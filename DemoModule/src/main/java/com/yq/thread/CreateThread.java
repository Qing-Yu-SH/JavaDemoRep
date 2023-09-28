package com.yq.thread;

import java.util.concurrent.*;

/**
 * @program: JavaDemoRep
 * @description: 创建线程的方式
 * @author: Yuqing
 * @create: 2023-09-28 15:18
 **/
public class CreateThread {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 方式 1
        Thread01 thread01 = new Thread01();
        thread01.start();

        // 方式 2
        Thread thread02 = new Thread(new Thread02());
        thread02.start();
        // 方式 2_2： 匿名内部类
        Thread thread02_2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Thread02_2 running..");
            }
        });
        thread02_2.start();
        // 方式 2_3： Lambda 表达式
        Thread thread02_3 = new Thread(() -> System.out.println("Thread02_3 running.."));
        thread02_3.start();

        // 方式 3
        FutureTask<String> futureTask = new FutureTask<>(new Thread03());
        Thread thread03 = new Thread(futureTask);
        thread03.start();
        String result = futureTask.get();
        System.out.println(result);

        // 方式 4：线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(() -> System.out.println("Thread04 running.."));
        executorService.shutdown();

    }

}

/**
 * 方式1：继承 Thread 类
 */
class Thread01 extends Thread{
    @Override
    public void run() {
        System.out.println("Thread01 running..");
    }
}

/**
 * 方式2：实现 Runnable 接口
 */
class Thread02 implements Runnable{
    @Override
    public void run() {
        System.out.println("Thread02 running..");
    }
}

/**
 * 方式3：实现 Callable 接口，需要与 FutureTask 搭配使用
 */
class Thread03 implements Callable<String>{
    @Override
    public String call() throws Exception {
        return "Thread03 running..";
    }
}
