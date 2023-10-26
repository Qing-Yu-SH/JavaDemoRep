package com.yq;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: JavaDemoRep
 * @description: 测试 supplyAsync 和 runAsync
 * @author: Yuqing
 * @create: 2023-10-26 16:51
 **/
public class CreateFutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        // runAsync 的使用
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> System.out.println("执行 runAsync().."),threadPool);
        // supplyAsync 的使用
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("执行 supplyAsync()..");
            return "sumAll";
        },threadPool);

        // runAsync 的 future 没有返回值，输出 null
        System.out.println(runAsync.get());
        // supplyAsync 的 future，有返回值
        System.out.println(supplyAsync.get());

        threadPool.shutdown();
    }

}
