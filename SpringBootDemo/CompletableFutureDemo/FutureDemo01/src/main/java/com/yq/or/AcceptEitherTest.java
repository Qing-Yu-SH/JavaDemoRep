package com.yq.or;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @program: JavaDemoRep
 * @description: acceptEither 用于处理多个 CompletableFuture 中的第一个完成的结果
 *               它接受两个参数：一个 CompletableFuture 对象的集合和一个 Consumer 函数，用于处理第一个完成的结果
 *                会将已经执行完成的任务，作为方法入参，传递到指定方法中，且无返回值
 * @author: Yuqing
 * @create: 2023-10-26 19:41
 **/
public class AcceptEitherTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future1 = CompletableFuture.supplyAsync(() -> 10);
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> 20);

        CompletableFuture<Void> result = future1.acceptEither(future2, firstResult -> {
            System.out.println("First completed result: " + firstResult);
        });

        System.out.println(result.get());
    }

}
