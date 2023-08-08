package com.yq.pattern.abstractfactory.service.impl;

import com.yq.pattern.abstractfactory.service.CacheService;
import com.yq.pattern.abstractfactory.util.RedisUtils;

/**
 * @program: JavaDemoRep
 * @description: 单机 Redis 下的服务
 * @author: Yuqing
 * @create: 2023-08-08 14:55
 **/
public class CacheServiceImpl implements CacheService {

    private RedisUtils redisUtils = new RedisUtils();

    @Override
    public String get(String key) {
        return redisUtils.get(key);
    }

    @Override
    public void set(String key, String value) {
        redisUtils.set(key, value);
    }

    @Override
    public void del(String key) {
        redisUtils.del(key);
    }

}
