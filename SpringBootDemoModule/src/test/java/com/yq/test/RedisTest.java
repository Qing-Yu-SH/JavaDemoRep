package com.yq.test;

import com.yq.demo.RedissonPractice;
import com.yq.hook.servlet.SelfServletConfig;
import com.yq.util.RedisClient;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-06 10:04
 **/
public class RedisTest extends BasicTest{

    @Autowired
    private RedissonPractice redissonPractice;

    private Long startTime;
    private Long endTime;

    @Before
    public void before(){
        startTime = System.currentTimeMillis();
    }

    @After
    public void after(){
        endTime = System.currentTimeMillis();
        System.out.println("共耗时：" + (endTime-startTime)/1000.0 + "s");
    }

    @Test
    public void test_redis(){
        RedisClient.hIncr("web_statistic","user_count",1);
        System.out.println(RedisClient.hGet("web_statistic","user_count",Integer.class));
    }

    @Test
    public void test_topN(){
        RedisClient.zIncrBy("activity_rank_231006","user_1",20);
        RedisClient.zIncrBy("activity_rank_231006","user_2",200);
        RedisClient.zIncrBy("activity_rank_231006","user_3",16);
        List<ImmutablePair<String, Double>> rank = RedisClient.zTopNScore("activity_rank_231006", 2);
        int i = 1;
        for (ImmutablePair<String, Double> pair : rank) {
            System.out.println("用户：" + pair.getLeft() + "\t分数：" + pair.getRight() + "\t排名：" + (i++));
        }
    }

    // 测试读写锁
    @Test
    public void test_readWriteLock(){
        Thread updateThread = new Thread(new Runnable() {
            @Override
            public void run() {
                redissonPractice.updateNameById(2);
            }
        });
        Thread readThread = new Thread(new Runnable() {
            @Override
            public void run() {
                redissonPractice.getNameById(2);
            }
        });
        updateThread.start();
        readThread.start();

        // 当一个线程调用另一个线程的 join() 方法时，它会被阻塞，直到被调用的线程执行完毕
        try {
            updateThread.join();
            readThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 测试分布式锁
    @Test
    public void test_redisLock() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    redissonPractice.redisLock(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    redissonPractice.redisLock(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        thread2.start();

        try {
            thread.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 测试可重入锁
    @Test
    public void test_reentryLock(){
        redissonPractice.add();
    }

}
