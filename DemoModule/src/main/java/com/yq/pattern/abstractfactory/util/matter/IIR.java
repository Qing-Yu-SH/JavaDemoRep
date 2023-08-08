package com.yq.pattern.abstractfactory.util.matter;

import com.yq.pattern.abstractfactory.util.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: 企业级 Redis 集群 B
 * @author: Yuqing
 * @create: 2023-08-08 14:23
 **/
public class IIR {

    private Logger logger = LoggerFactory.getLogger(RedisUtils.class);

    private Map<String, String> dataMap = new HashMap<>();

    public String get(String key){
        logger.info("IIR 获取数据：{}",key);
        return dataMap.get(key);
    }

    public void setVal(String key,String val){
        logger.info("IIR 写入数据 key：{} val：{}",key,val);
        dataMap.put(key,val);
    }

    public void del(String key){
        logger.info("IIR 删除数据：{}",key);
        dataMap.remove(key);
    }

}
