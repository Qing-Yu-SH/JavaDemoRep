package com.yq;

import com.yq.entity.Store;
import com.yq.entity.User;
import com.yq.service.StoreService;
import com.yq.service.UserService;

import java.util.concurrent.*;

/**
 * @program: JavaDemoRep
 * @description: 通过 Future 执行，需要 613 ms
 *               Future 对于结果的获取，不是很友好，只能通过阻塞或者轮询的方式得到任务的结果
 *               Future.get() 就是阻塞调用，在线程获取结果之前 get 方法会一直阻塞
 *               Future 提供了一个 isDone 方法，可以在程序中轮询这个方法查询执行结果
 * @author: Yuqing
 * @create: 2023-10-26 16:25
 **/
public class FutureTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        UserService userService = new UserService();
        StoreService storeService = new StoreService();

        long startTime = System.currentTimeMillis();

        FutureTask<User> userFutureTask = new FutureTask<>(new Callable<User>() {

            @Override
            public User call() throws Exception {
                return userService.getUserInfo();
            }
        });
        executorService.submit(userFutureTask);

        Thread.sleep(100); // 模拟主线程其他操作耗时

        FutureTask<Store> storeFutureTask = new FutureTask<>(new Callable<Store>() {

            @Override
            public Store call() throws Exception {
                return storeService.getStoreInfo();
            }
        });
        executorService.submit(storeFutureTask);

        User user = userFutureTask.get();
        Store store = storeFutureTask.get();

        System.out.println("总共用时 " + (System.currentTimeMillis() - startTime) + " ms");

        executorService.shutdown();
    }

}
