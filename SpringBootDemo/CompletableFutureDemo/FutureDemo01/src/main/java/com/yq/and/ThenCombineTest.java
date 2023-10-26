package com.yq.and;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: JavaDemoRep
 * @description: thenCombine 会将两个任务的执行结果作为方法入参，传递到指定方法中，且有返回值
 * @author: Yuqing
 * @create: 2023-10-26 19:17
 **/
public class ThenCombineTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> first = CompletableFuture.completedFuture("第一个异步任务");

        ExecutorService executor = Executors.newFixedThreadPool(10);

        CompletableFuture<String> future = CompletableFuture
                //第二个异步任务
                .supplyAsync(() -> "第二个异步任务", executor)
                .thenCombineAsync(first, (s, w) -> {
                    System.out.println(w);
                    System.out.println(s);
                    return "两个异步任务的组合";
                }, executor);

        System.out.println(future.get());

        executor.shutdown();

    }

}
