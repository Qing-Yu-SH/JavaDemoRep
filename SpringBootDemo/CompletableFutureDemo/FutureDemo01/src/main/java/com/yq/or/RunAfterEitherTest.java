package com.yq.or;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @program: JavaDemoRep
 * @description: runAfterEither 用于在多个 CompletableFuture 中的任何一个完成后执行指定的操作
 *               不会把执行结果当做方法入参，且没有返回值
 * @author: Yuqing
 * @create: 2023-10-26 19:45
 **/
public class RunAfterEitherTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println("Task 1 is running");
            // Simulate some task
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println("Task 2 is running");
            // Simulate some task
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CompletableFuture<Void> completableFuture = future1.runAfterEither(future2, () -> {
            System.out.println("One of the tasks is completed.");
        });

        System.out.println(completableFuture.get());

    }

}
