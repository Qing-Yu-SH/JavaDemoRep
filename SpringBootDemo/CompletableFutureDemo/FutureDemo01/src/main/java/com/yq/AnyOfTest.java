package com.yq;

import java.util.concurrent.CompletableFuture;

/**
 * @program: JavaDemoRep
 * @description: 任意一个任务执行完，就执行 anyOf 返回的 CompletableFuture
 *               如果执行的任务异常，anyOf 的 CompletableFuture，执行 get 方法，会抛出异常
 * @author: Yuqing
 * @create: 2023-10-26 19:53
 **/
public class AnyOfTest {

    public static void main(String[] args) {
        CompletableFuture<Void> a = CompletableFuture.runAsync(()->{
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我执行完了");
        });
        CompletableFuture<Void> b = CompletableFuture.runAsync(() -> {
            System.out.println("我也执行完了");
        });
        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(a, b).whenComplete((m,k)->{
            System.out.println("finish");
        });

        System.out.println(anyOfFuture.join());
    }

}
