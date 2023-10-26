package com.yq.and;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: JavaDemoRep
 * @description: runAfterBoth 不会把执行结果当做方法入参，且没有返回值
 * @author: Yuqing
 * @create: 2023-10-26 19:29
 **/
public class RunAfterBothTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> first = CompletableFuture.runAsync(() -> System.out.println("第一个异步任务"));
        ExecutorService executor = Executors.newFixedThreadPool(10);

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> System.out.println("第二个异步任务"),executor)
                .runAfterBoth(first,() -> {
                    System.out.println("第三个异步任务");
                });

        System.out.println(future.get());
        executor.shutdown();
    }

}
