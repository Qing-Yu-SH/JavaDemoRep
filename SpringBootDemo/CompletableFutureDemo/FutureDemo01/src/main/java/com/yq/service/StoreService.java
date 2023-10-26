package com.yq.service;

import com.yq.entity.Store;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-26 16:29
 **/
public class StoreService {

    public Store getStoreInfo(){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            return null;
        }
        Store store = new Store("饺子店", "shanghai");
        return store;
    }

}
