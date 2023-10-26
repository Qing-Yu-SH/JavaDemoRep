package com.yq;

import com.yq.entity.Store;
import com.yq.entity.User;
import com.yq.service.StoreService;
import com.yq.service.UserService;

/**
 * @program: JavaDemoRep
 * @description: 顺序执行，大约需要 810 ms
 * @author: Yuqing
 * @create: 2023-10-26 16:33
 **/
public class SequentialTest {

    public static void main(String[] args) throws InterruptedException {
        UserService userService = new UserService();
        StoreService storeService = new StoreService();

        long startTime = System.currentTimeMillis();
        User userInfo = userService.getUserInfo();
        Thread.sleep(100); // 模拟主线程其它操作耗时
        Store storeInfo = storeService.getStoreInfo();

        System.out.println("总共用时 " + (System.currentTimeMillis() - startTime) + " ms");
    }

}
