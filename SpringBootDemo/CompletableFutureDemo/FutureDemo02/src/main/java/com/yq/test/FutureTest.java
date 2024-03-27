package com.yq.test;

import com.yq.common.PriceResult;
import com.yq.mock.HttpRequestMock;
import com.yq.service.ComparePriceService;

import java.util.Comparator;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-03-26 14:02
 **/
public class FutureTest {

    private static ComparePriceService service = new ComparePriceService();
    private static ExecutorService threadPool = Executors.newFixedThreadPool(5);


    /**
     * 【串行】获取多个平台比价信息得到最低价格平台
     * @param product
     * @return
     */
    public static PriceResult getCheapestPlatAndPrice(String product) {
        // 获取某宝的价格以及优惠
        PriceResult mouBaoPrice = service.computeRealPrice(HttpRequestMock.getMouBaoPrice(product),
                HttpRequestMock.getMouBaoDiscounts(product));
        // 获取某东的价格以及优惠
        PriceResult mouDongPrice = service.computeRealPrice(HttpRequestMock.getMouDongPrice(product),
                HttpRequestMock.getMouDongDiscounts(product));
        // 获取某夕夕的价格以及优惠
        PriceResult mouXiXiPrice = service.computeRealPrice(HttpRequestMock.getMouXiXiPrice(product),
                HttpRequestMock.getMouXiXiDiscounts(product));

        return Stream.of(mouBaoPrice,mouDongPrice,mouXiXiPrice)
                .min(Comparator.comparingInt(PriceResult::getRealPrice))
                .get();
    }


    /**
     * 【线程池】通过线程池增加并发
     * @param product
     * @return
     */
    public static PriceResult getCheapestPlatAndPrice2(String product) {
        Future<PriceResult> mouBaoFuture =
                threadPool.submit(() -> service.computeRealPrice(HttpRequestMock.getMouBaoPrice(product),
                        HttpRequestMock.getMouBaoDiscounts(product)));
        Future<PriceResult> mouDongFuture =
                threadPool.submit(() -> service.computeRealPrice(HttpRequestMock.getMouDongPrice(product),
                        HttpRequestMock.getMouDongDiscounts(product)));
        Future<PriceResult> mouXiXiFuture =
                threadPool.submit(() -> service.computeRealPrice(HttpRequestMock.getMouXiXiPrice(product),
                        HttpRequestMock.getMouXiXiDiscounts(product)));

        return Stream.of(mouBaoFuture,mouDongFuture,mouXiXiFuture)
                .map(priceResultFuture -> {
                    try {
                        return priceResultFuture.get(5L, TimeUnit.SECONDS);
                    }catch (Exception e){
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .min(Comparator.comparingInt(PriceResult::getRealPrice))
                .get();
    }


    public static PriceResult getCheapestPlatAndPrice3(String product){
        // 1.异步执行 HttpRequestMock.getMouBaoPrice(product) 和 HttpRequestMock.getMouBaoDiscounts(product)
        // 2.thenCombine() 方法将这两个异步操作进行组合，以便在两个异步操作都完成后，执行 service::computeRealPrice 方法对价格和折扣信息进行计算，得到最终的 PriceResult 对象

        // 未指定线程池，使用默认的 ForkJoinPool 线程池

        // 获取并计算某宝的最终价格
        CompletableFuture<PriceResult> mouBao =
                CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouBaoPrice(product))
                        .thenCombine(CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouBaoDiscounts(product)),
                                service::computeRealPrice);

        // 获取并计算某宝的最终价格
        CompletableFuture<PriceResult> mouDong =
                CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouDongPrice(product))
                        .thenCombine(CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouDongDiscounts(product)),
                                service::computeRealPrice);
        // 获取并计算某宝的最终价格
        CompletableFuture<PriceResult> mouXiXi =
                CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouXiXiPrice(product))
                        .thenCombine(CompletableFuture.supplyAsync(() -> HttpRequestMock.getMouXiXiDiscounts(product)),
                                service::computeRealPrice);

        return Stream.of(mouBao,mouDong,mouXiXi)
                .map(CompletableFuture::join)
                .sorted(Comparator.comparingInt(PriceResult::getRealPrice))
                .findFirst()
                .get();
    }



    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        String product = "iphone";

        // 串行获取最低价格                     耗时：6128ms
//        PriceResult result = getCheapestPlatAndPrice(product);

        // 并行通过线程池获取最低价格             耗时：2078ms
//        PriceResult result = getCheapestPlatAndPrice2(product);

        // 并行通过CompletableFuture获取最低价格 耗时：1060ms
        PriceResult result = getCheapestPlatAndPrice3(product);

        System.out.println("获取最优价格：" + result);
        System.out.println("-----执行耗时： " + (System.currentTimeMillis() - startTime) + "ms  ------");
        threadPool.shutdown();
    }

}
