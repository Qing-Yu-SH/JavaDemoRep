package com.yq;


import com.github.benmanes.caffeine.cache.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-03 12:41
 **/
@Slf4j
@SpringBootTest
public class CaffeineTest {

    @Test
    public void test_caffeineByManual(){
        // 手动创建缓存
        Cache<Object, Object> cache = Caffeine.newBuilder()
                // 初始数量
                .initialCapacity(10)
                // 最大条数
                .maximumSize(10)
                // PS：expireAfterWrite 和 expireAfterAccess 同时存在时，以 expireAfterWrite 为准
                // 最后一次写操作后经过指定时间过期
                .expireAfterWrite(1, TimeUnit.SECONDS)
                // 最后一次读或写操作后经过指定时间过期
                .expireAfterAccess(1, TimeUnit.SECONDS)
                // 监听缓存被移除
                .removalListener((key, val, removalCause) -> { })
                // 记录命中
                .recordStats()
                .build();

        cache.put("1","张三");
        System.out.println(cache.getIfPresent("1"));
        System.out.println(cache.get("2",o -> "默认值"));
    }

    @Test
    public void test_caffeineByAuto() throws InterruptedException {
        // 自动创建缓存
        LoadingCache<String, String> loadingCache = Caffeine.newBuilder()
                //创建缓存或者最近一次更新缓存后经过指定时间间隔，刷新缓存；refreshAfterWrite仅支持LoadingCache
                .refreshAfterWrite(10, TimeUnit.SECONDS)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .maximumSize(10)
                //根据 key 查询数据库里面的值
                .build(key -> new Date().toString());

        // 缓存中不存在时，根据 build 获取
        System.out.println("cur：" + loadingCache.get("cur"));
        Thread.sleep(1000);
        System.out.println("nxt：" + loadingCache.get("nxt"));
        System.out.println("cur：" + loadingCache.get("cur"));
        Thread.sleep(10000);
        System.out.println("cur：" + loadingCache.get("cur"));
    }

    @Test
    public void test_caffeineByAsync() throws ExecutionException, InterruptedException {
        AsyncLoadingCache<String, String> asyncLoadingCache = Caffeine.newBuilder()
                // 创建缓存或者最近一次更新缓存后经过指定时间间隔刷新缓存；仅支持 LoadingCache
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .maximumSize(10)
                // 根据 key 查询数据库里面的值
                .buildAsync(key -> {
                    Thread.sleep(10000);
                    return new Date().toString();
                });

        // 异步缓存返回的是 CompletableFuture
        // CompletableFuture 所执行的任务都被设定为守护线程，这意味着主线程执行完毕时，它即使没有执行完毕，程序也会关闭
        CompletableFuture<String> future = asyncLoadingCache.get("1");
        future.thenAccept(System.out::println);
        System.out.println("done!");
        // 等线程执行完毕后，再获取值
        future.join();
    }

    @Test
    public void test_caffeineStat(){
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                //创建缓存或者最近一次更新缓存后经过指定时间间隔，刷新缓存；refreshAfterWrite仅支持LoadingCache
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .maximumSize(10)
                //开启记录缓存命中率等信息
                .recordStats()
                //根据key查询数据库里面的值
                .build(key -> {
                    Thread.sleep(1000);
                    return new Date().toString();
                });

        cache.put("1", "小明");
        cache.get("1");

        /*
         * hitCount :命中的次数
         * missCount:未命中次数
         * requestCount:请求次数
         * hitRate:命中率
         * missRate:丢失率
         * loadSuccessCount:成功加载新值的次数
         * loadExceptionCount:失败加载新值的次数
         * totalLoadCount:总条数
         * loadExceptionRate:失败加载新值的比率
         * totalLoadTime:全部加载时间
         * evictionCount:丢失的条数
         */
        System.out.println(cache.stats());
    }

    /**
     * 缓存大小淘汰
     * todo：不太理解这块淘汰策略
     */
    @Test
    public void test_maximumSize() throws InterruptedException {
        Cache<Integer, String> cache = Caffeine.newBuilder()
                // 超过10个后会使用 W-TinyLFU 算法进行淘汰
                .maximumSize(10)
                .removalListener((key, val, removalCause) -> {
                    log.info("淘汰缓存：key:{} val:{}", key, val);
                })
                .build();

        for (int i = 1; i < 20; i++) {
            cache.put(i, generateStr(i));
            Thread.sleep(200);
        }
        Thread.sleep(500);//缓存淘汰是异步的

        // 打印还没被淘汰的缓存
        System.out.println(cache.asMap());
    }

    private static String generateStr(int length){
        StringBuilder builder = new StringBuilder();
        for(int i=0;i<length;i++){
            builder.append((char)('a'+i));
        }
        return builder.toString();
    }

    /**
     * 权重淘汰
     * @throws InterruptedException
     */
    @Test
    public void test_maximumWeight() throws InterruptedException {
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                //限制总权重，若所有缓存的权重加起来 > 总权重就会淘汰权重小的缓存
                .maximumWeight(100)
                .weigher((Weigher<Integer, Integer>) (key, value) -> key)
                .removalListener((key, val, removalCause) -> {
                    log.info("淘汰缓存：key:{} val:{}", key, val);
                })
                .build();

        //总权重其实是=所有缓存的权重加起来
        int maximumWeight = 0;
        for (int i = 1; i < 20; i++) {
            cache.put(i, i);
            maximumWeight += i;
            System.out.println("当前权重=" + maximumWeight);
            Thread.sleep(200);
        }
        System.out.println("总权重=" + maximumWeight);
        Thread.sleep(500);//缓存淘汰是异步的

        // 打印还没被淘汰的缓存
        System.out.println(cache.asMap());
    }

    /**
     * 访问后到期（每次访问都会重置时间，也就是说如果一直被访问就不会被淘汰）
     * @throws InterruptedException
     */
    @Test
    public void test_expireAfterAccess() throws InterruptedException {
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                .expireAfterAccess(1, TimeUnit.SECONDS)
                // 可以指定调度程序来及时删除过期缓存项，而不是等待 Caffeine 触发定期维护
                // 若不设置 scheduler，则缓存会在下一次调用 get 的时候才会被动删除
                .scheduler(Scheduler.systemScheduler())
                .removalListener((key, val, removalCause) -> {
                    log.info("淘汰缓存：key:{} val:{}", key, val);

                })
                .build();
        cache.put(1, 2);
        System.out.println(cache.getIfPresent(1));
        Thread.sleep(2000);
        System.out.println(cache.getIfPresent(1)); // null
    }

    /**
     * 写入后到期
     * @throws InterruptedException
     */
    @Test
    public void test_expireAfterWrite() throws InterruptedException {
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.SECONDS)
                // 可以指定调度程序来及时删除过期缓存项，而不是等待 Caffeine 触发定期维护
                // 若不设置 scheduler，则缓存会在下一次调用 get 的时候才会被动删除
                .scheduler(Scheduler.systemScheduler())
                .removalListener((key, val, removalCause) -> {
                    log.info("淘汰缓存：key:{} val:{}", key, val);
                })
                .build();
        cache.put(1, 2);
        Thread.sleep(3000);
        System.out.println(cache.getIfPresent(1));//null
    }

    private static int NUM = 0;
    @Test
    public void test_refreshAfterWrite() throws InterruptedException {
        LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                // 模拟获取数据，每次获取就自增 1
                .build(integer -> ++NUM);

        //获取 ID=1 的值，由于缓存里还没有，所以会自动放入缓存
        System.out.println(cache.get(1));// 1

        // 延迟 2 秒后，理论上自动刷新缓存后取到的值是 2
        // 但其实不是，值还是 1，因为 refreshAfterWrite 并不是设置了 n 秒后重新获取就会自动刷新
        // 而是 x 秒后 && 第二次调用 getIfPresent 的时候才会被动刷新
        Thread.sleep(2000);
        System.out.println(cache.getIfPresent(1));// 1

        // 此时才会刷新缓存，而第一次拿到的还是旧值
        System.out.println(cache.getIfPresent(1));// 2
    }






}
