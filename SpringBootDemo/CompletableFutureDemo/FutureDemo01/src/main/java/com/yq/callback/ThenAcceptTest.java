package com.yq.callback;

import com.yq.thread.ThreadPool;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @program: JavaDemoRep
 * @description: thenAccept 方法表示，第一个任务执行完成后，执行第二个回调方法任务，会将该任务的执行结果作为入参，传递到回调方法中，但是回调方法是没有返回值的
 *               thenApply 方法表示，第一个任务执行完成后，执行第二个回调方法任务，会将该任务的执行结果作为入参，传递到回调方法中，并且回调方法是有返回值的
 * @author: Yuqing
 * @create: 2023-10-26 18:56
 **/
public class ThenAcceptTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = ThreadPool.threadPoolExecutor();

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("线程 "+ Thread.currentThread().getName() +"-"+ Thread.currentThread().getId() +" 正在执行 supplyAsync..");
            return "true";
        },threadPoolExecutor);

        CompletableFuture<Void> thenAccept = future.thenAccept((a) -> {
            System.out.println("线程 "+ Thread.currentThread().getName() +"-"+ Thread.currentThread().getId() +" 正在执行 thenAccept，参数："+ a);
        });

        System.out.println(thenAccept.get());

        System.out.println("----------------------------------------------------------------------------");

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("线程 "+ Thread.currentThread().getName() +"-"+ Thread.currentThread().getId() +" 正在执行 supplyAsync2..");
            return "great";
        },threadPoolExecutor);

        CompletableFuture<String> thenApply = future2.thenApply((a) -> {
            System.out.println("线程 " + Thread.currentThread().getName() + "-" + Thread.currentThread().getId() + " 正在执行 thenApply，参数："+ a);
            return "good";
        });

        System.out.println(thenApply.get());

        threadPoolExecutor.shutdown();
    }

}
