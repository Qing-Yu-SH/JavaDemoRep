package com.yq.or;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: JavaDemoRep
 * @description: applyToEither 方法接受两个 CompletableFuture 对象，并在其中任何一个完成时，将提供的函数应用于已经完成的那个 CompletableFuture 的结果，且有返回值
 *               允许你在多个并行计算中使用首先完成的结果，以提高并发性和性能
 * @author: Yuqing
 * @create: 2023-10-26 19:35
 **/
public class ApplyToEitherTest {

    public static void main(String[] args) {
        CompletableFuture<String> first = CompletableFuture.supplyAsync(()->{
            try{

                Thread.sleep(2000L);
                System.out.println("执行完第一个异步任务");}
            catch (Exception e){
                return "第一个任务异常";
            }
            return "第一个异步任务";
        });

        ExecutorService executor = Executors.newSingleThreadExecutor();

        CompletableFuture<Void> future = CompletableFuture
                //第二个异步任务
                .supplyAsync(() -> {
                            System.out.println("执行完第二个任务");
                            return "第二个任务";}
                        , executor)
                //第三个任务
                .acceptEitherAsync(first, System.out::println, executor);


        executor.shutdown();
    }

}
