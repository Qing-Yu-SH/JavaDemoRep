package com.yq;

import com.yq.entity.Store;
import com.yq.entity.User;
import com.yq.service.StoreService;
import com.yq.service.UserService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @program: JavaDemoRep
 * @description: 通过 CompletableFuture 执行，需要 647 ms
 *               CompletableFuture 提供了一种观察者模式类似的机制，可以让任务执行完成后通知监听的一方
 *               CompletableFuture 使用了默认线程池 ForkJoinPool.commonPool
 *
 * @author: Yuqing
 * @create: 2023-10-26 16:41
 **/
public class CompletableFutureTest {

    public static void main(String[] args) throws ExecutionException, TimeoutException, InterruptedException {

        UserService userService = new UserService();
        StoreService storeService = new StoreService();

        long startTime = System.currentTimeMillis();

        CompletableFuture<User> userCompletableFuture = CompletableFuture.supplyAsync(() -> userService.getUserInfo());

        Thread.sleep(100); // 模拟主线程其他操作耗时

        CompletableFuture<Store> storeCompletableFuture = CompletableFuture.supplyAsync(() -> storeService.getStoreInfo());

        User user = userCompletableFuture.get(2, TimeUnit.SECONDS);
        Store store = storeCompletableFuture.get();

        System.out.println("总共用时 " + (System.currentTimeMillis() - startTime) + " ms");

    }

}
