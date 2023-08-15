package com.yq.pattern.chain.service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: JavaDemoRep
 * @description: 审核服务
 * @author: Yuqing
 * @create: 2023-08-15 13:34
 **/
public class AuthService {

    // key：levelUserId+orderId；value：审批时间
    private static Map<String, Date> authMap = new ConcurrentHashMap<>();

    public static Date queryAuthInfo(String uId,String orderId){
        return authMap.get(uId.concat(orderId));
    }

    public static void auth(String uId,String orderId){
        authMap.put(uId.concat(orderId),new Date());
    }

}
