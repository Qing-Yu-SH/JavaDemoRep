package com.yq.demo;

import com.yq.repository.entity.User;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemoRep
 * @description: Redission 相关操作
 * @author: Yuqing
 * @create: 2023-10-08 10:03
 **/
@Component
public class RedissonPractice {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private RedissonClient redissonClient;


    /**
     * 缓存一致性有两种方案：
     * 1.同步的方案
     *            通过 Redisson 的读写锁实现，优点是强一致，缺点是性能差
     * 2.异步的方案
     *            通过 MQ 消息中间件实现，更新数据后发送消息，通知缓存删除
     *            通过 canal 中间件，不需要修改业务代码，伪装成一个 mysql 的从节点，通过读取 binlog 日志更新缓存
     */

    /*---------------------双写一致，通过使用读写锁达到强一致性----------------------*/

    /**
     * 添加读锁，读取 Redis 数据；数据不存在，读取 mysql 数据，并存入缓存
     */
    public String getNameById(Integer id){
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("USER_READ_WRITE_LOCK");
        System.out.println("getNameById()  -->  尝试获取读锁");
        // 读之前加读锁，等待 lockKey 的写锁释放
        RLock rLock = readWriteLock.readLock();
        try {
            // 开锁
            rLock.lock();
            System.out.println("getNameById()  -->  readLock 获取成功..");
            String name = redisTemplate.opsForValue().get("user_" + id);
            if(StringUtils.isNoneEmpty(name)){
                return name;
            }
            // 读取 mysql 数据，并存入缓存
            redisTemplate.opsForValue().set("user_"+id,"Yu");
            return "Yu";
        }finally {
            // 释放
            rLock.unlock();
        }
    }

    /**
     * 添加写锁，在更新 mysql 数据期间，禁止对 redis 中该数据的读取
     */
    public void updateNameById(Integer id){
        RReadWriteLock readWriteLock = redissonClient.getReadWriteLock("USER_READ_WRITE_LOCK");
        System.out.println("updateNameById()  -->  尝试获取写锁");
        // 写之前加写锁，等待 lockKey 的读锁释放
        RLock wLock = readWriteLock.writeLock();
        try {
            // 开锁
            wLock.lock();
            System.out.println("updateNameById()  -->  writeLock 获取成功..");
            // 更新 mysql 数据
            String name = "Yu";
            try {
                Thread.sleep(10000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            // mysql 更新完成，删除 redis 缓存
            redisTemplate.delete("user_"+id);
        }finally {
            // 释放
            wLock.unlock();
        }
    }

    /*---------------------双写一致，通过使用读写锁达到强一致性----------------------*/


    /*---------------------分布式锁----------------------*/

    public void redisLock(Integer id) throws InterruptedException {
        // 通过 hash 结构存储分布式锁，其中 key：REDIS_LOCK、filed：threadId、value：重入次数
        // 底层是 setnx 和 lua 脚本（保证原子性）
        RLock lock = redissonClient.getLock("REDIS_LOCK");
        System.out.println("user_" + id + " 尝试获取锁..");
        // 尝试获取锁，参数分别是：获取锁的最大等待时间（期间会重试）、锁自动释放时间、时间单位
        // boolean isLock = lock.tryLock(10,30, TimeUnit.SECONDS);
        // 添加锁自动释放时间，则 watch dog 会失效；watch dog 会对锁进行续期
        boolean isLock = lock.tryLock(20, TimeUnit.SECONDS);
        if(isLock){
            try {
                System.out.println("user_" + id + " 获取锁成功；执行业务..");
                Thread.sleep(10000);
            }finally {
                // 释放锁
                lock.unlock();
            }
        }
    }

    public void add(){
        RLock lock = redissonClient.getLock("REENTRY_LOCK");
        boolean isLock = lock.tryLock();
        if(isLock){
            System.out.println("add() 获取锁..");
        }
        add2();

        lock.unlock();
    }

    public void add2(){
        RLock lock = redissonClient.getLock("REENTRY_LOCK");
        boolean isLock = lock.tryLock();
        if(isLock){
            System.out.println("add2() 获取锁..");
        }

        lock.unlock();
    }

    /*---------------------分布式锁----------------------*/


}
