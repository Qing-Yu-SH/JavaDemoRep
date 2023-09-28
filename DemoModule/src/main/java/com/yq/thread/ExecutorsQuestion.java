package com.yq.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: JavaDemoRep
 * @description: 为什么不建议使用 Executors 来创建线程池
 *                  1.创建的队列是无界阻塞队列，可能导致内存耗尽，而产生 OOM
 *                  2.不能自定义线程名字，不利于排查问题，建议使用 ThreadPoolExecutor
 * @author: Yuqing
 * @create: 2023-09-28 15:44
 **/
public class ExecutorsQuestion {

    public static void main(String[] args) {
        // 创建的队列是 LinkedBlockingQueue，是一个无界阻塞队列
        // 如果使用该线程池执行任务，当任务过多时，就会不断的将任务添加到队列中；任务越多，占用的内存越大，最终可能耗尽内存，导致 OOM
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 创建的队列是 LinkedBlockingQueue，是一个无界阻塞队列
        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

    }

}
