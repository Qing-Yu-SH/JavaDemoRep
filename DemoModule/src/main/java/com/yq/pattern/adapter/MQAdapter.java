package com.yq.pattern.adapter;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: MQ 消息体适配类
 * @author: Yuqing
 * @create: 2023-08-05 15:11
 **/
public class MQAdapter {

    public static RebateInfo filter(String strJson, Map<String, String> link) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        return filter(JSON.parseObject(strJson,Map.class),link);
    }

    /**
     *
     * @param obj  由 MQ 消息的 JSON 格式转化来的 Map
     * @param link key：消息体属性名称；value：MQ 消息属性名称
     * @return 统⼀的 MQ 消息体
     */
    public static RebateInfo filter(Map obj,Map<String,String> link) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        RebateInfo rebateInfo = new RebateInfo();
        for(String key: link.keySet()){
            Object val = obj.get(link.get(key));
            RebateInfo.class.getMethod("set" + key.substring(0,1).toUpperCase() + key.substring(1),String.class).invoke(rebateInfo,val.toString());
        }
        return rebateInfo;
    }

}
