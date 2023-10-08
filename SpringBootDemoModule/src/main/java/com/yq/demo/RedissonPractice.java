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
        // 读之前加读锁，等待 lockKey 的写锁释放
        RLock rLock = readWriteLock.readLock();
        try {
            // 开锁
            rLock.lock();
            System.out.println("readLock..");
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
        // 写之前加写锁，等待 lockKey 的读锁释放
        RLock wLock = readWriteLock.writeLock();
        try {
            // 开锁
            wLock.lock();
            System.out.println("writeLock..");
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

}
