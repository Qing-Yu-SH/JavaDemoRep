package com.yq.pattern.abstractfactory.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: 单机 Redis 操作
 * @author: Yuqing
 * @create: 2023-08-08 14:18
 **/
public class RedisUtils {

    private Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    private Map<String, String> dataMap = new HashMap<>();

    public String get(String key){
        logger.info("Redis 获取数据：{}",key);
        return dataMap.get(key);
    }

    public void set(String key,String val){
        logger.info("Redis 写入数据 key：{} val：{}",key,val);
        dataMap.put(key,val);
    }

    public void del(String key){
        logger.info("Redis 删除数据：{}",key);
        dataMap.remove(key);
    }

}
