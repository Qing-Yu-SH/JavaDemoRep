package com.yq.callback;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @program: JavaDemoRep
 * @description: whenComplete 方法表示，某个任务执行完成后，执行的回调方法，无返回值；并且 whenComplete 方法返回 的 result 是上个任务的结果
 * @author: Yuqing
 * @create: 2023-10-26 19:05
 **/
public class WhenCompleteTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> orgFuture = CompletableFuture.supplyAsync(
                ()->{
                    System.out.println("当前线程名称：" + Thread.currentThread().getName());
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "sumAll";
                }
        );

        CompletableFuture<String> stringCompletableFuture = orgFuture.whenComplete((a, throwable) -> {
            System.out.println("当前线程名称：" + Thread.currentThread().getName());
            System.out.println("上个任务执行完啦，还把" + a + "传过来");
        });

        System.out.println(stringCompletableFuture.get());
    }

}
