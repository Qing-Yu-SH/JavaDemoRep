package com.yq.and;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: JavaDemoRep
 * @description: thenAcceptBoth 会将两个任务的执行结果作为方法入参，传递到指定方法中，且无返回值
 * @author: Yuqing
 * @create: 2023-10-26 19:21
 **/
public class ThenAcceptBothTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> first = CompletableFuture.supplyAsync(() -> "第一个异步任务");
        ExecutorService executor = Executors.newFixedThreadPool(10);

        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> "第二个异步任务", executor)
                .thenAcceptBoth(first, (s, w) -> {
                    System.out.println(w);
                    System.out.println(s);
                });

        System.out.println(future.get());
        executor.shutdown();
    }

}
