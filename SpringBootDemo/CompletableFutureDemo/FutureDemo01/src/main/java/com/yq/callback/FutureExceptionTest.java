package com.yq.callback;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @program: JavaDemoRep
 * @description: exceptionally 方法表示，某个任务发生异常时，执行的回调方法;并且有抛出异常作为参数，传递到回调方法
 * @author: Yuqing
 * @create: 2023-10-26 19:02
 **/
public class FutureExceptionTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("线程 "+ Thread.currentThread().getName() +"-"+ Thread.currentThread().getId());
            throw new RuntimeException();
        });

        CompletableFuture<String> exceptionFuture = future.exceptionally((e) -> {
            e.printStackTrace();
            return "出现异常情况..";
        });

        System.out.println(exceptionFuture.get());
    }

}
