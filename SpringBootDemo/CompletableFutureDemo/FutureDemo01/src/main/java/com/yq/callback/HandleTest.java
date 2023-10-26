package com.yq.callback;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @program: JavaDemoRep
 * @description: handle 方法表示，某个任务执行完成后，执行回调方法，并且是有返回值的; 并且 handle 方法返回的 CompletableFuture 的 result 是回调方法执行的结果
 * @author: Yuqing
 * @create: 2023-10-26 19:09
 **/
public class HandleTest {

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

        CompletableFuture<String> future = orgFuture.handle((a, throwable) -> {
            System.out.println("上个任务执行完啦，还把" + a + "传过来");
            return "sumAll2";
        });

        System.out.println(future.get());
    }

}
