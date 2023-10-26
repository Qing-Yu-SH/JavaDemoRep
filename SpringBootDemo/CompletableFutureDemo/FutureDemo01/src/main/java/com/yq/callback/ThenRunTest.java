package com.yq.callback;

import com.yq.thread.ThreadPool;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @program: JavaDemoRep
 * @description: 调用 thenRun 方法时传入自定义线程池，第一个任务是自定义线程池执行的，第二个任务是 main 线程执行的
 *               调用 thenRunAsync 方法时传入自定义线程池，第一个任务是自定义线程池执行的，第二个任务是 ForkJoin 线程池
 * @author: Yuqing
 * @create: 2023-10-26 18:29
 **/
public class ThenRunTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ThreadPoolExecutor threadPoolExecutor = ThreadPool.threadPoolExecutor();

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("线程 "+ Thread.currentThread().getName() +"-"+ Thread.currentThread().getId() +" 正在执行 supplyAsync..");
            return "true";
        },threadPoolExecutor);

        CompletableFuture<Void> thenRun = future.thenRun(() -> {
            System.out.println("线程 "+ Thread.currentThread().getName() +"-"+ Thread.currentThread().getId() +" 正在执行 thenRun..");
        });

        System.out.println(thenRun.get());

        System.out.println("----------------------------------------------------------------------------");

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("线程 "+ Thread.currentThread().getName() +"-"+ Thread.currentThread().getId() +" 正在执行 supplyAsync2..");
            return "true";
        },threadPoolExecutor);

        CompletableFuture<Void> thenRunAsync = future2.thenRunAsync(() -> {
            System.out.println("线程 " + Thread.currentThread().getName() + "-" + Thread.currentThread().getId() + " 正在执行 thenRunAsync..");
        });

        System.out.println(thenRunAsync.get());

        threadPoolExecutor.shutdown();
    }

}
