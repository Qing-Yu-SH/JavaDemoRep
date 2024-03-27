package com.yq.service;

import com.yq.common.PriceResult;
import com.yq.util.LogHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @program: JavaDemoRep
 * @description: 比价服务
 * @author: Yuqing
 * @create: 2024-03-26 13:53
 **/
public class ComparePriceService {

    private ExecutorService threadPool = Executors.newFixedThreadPool(5);

    public PriceResult computeRealPrice(PriceResult priceResult, int disCounts) {
        priceResult.setRealPrice(priceResult.getPrice() - disCounts);
        LogHelper.printLog(priceResult.getPlatform() + "最终价格计算完成：" + priceResult.getRealPrice());
        return priceResult;
    }

    private String getResult(String result) {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }


}
