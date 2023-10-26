package com.yq;

import java.util.concurrent.CompletableFuture;

/**
 * @program: JavaDemoRep
 * @description: 所有任务都执行完成后，才执行 allOf 返回的 CompletableFuture
 *               如果任意一个任务异常，allOf 的 CompletableFuture，执行 get 方法，会抛出异常
 * @author: Yuqing
 * @create: 2023-10-26 19:52
 **/
public class AllOfTest {

    public static void main(String[] args) {
        CompletableFuture<Void> a = CompletableFuture.runAsync(()->{
            System.out.println("我执行完了1");
        });

        CompletableFuture<Void> b = CompletableFuture.runAsync(() -> {
            System.out.println("我也执行完了2");
        });

        CompletableFuture<Void> allOfFuture = CompletableFuture.allOf(a, b).whenComplete((m,k)->{
            System.out.println("finish");
        });
    }

}
