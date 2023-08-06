package com.yq.pattern.decorator.sso.impl;

import com.yq.pattern.decorator.sso.HandlerInterceptor;

/**
 * @program: JavaDemoRep
 * @description: 模拟单点登录功能
 * @author: Yuqing
 * @create: 2023-08-06 13:38
 **/
public class SsoInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(String request, String response, Object handler) {
        // 模拟获取cookie
        String ticket = request.substring(1, 8);
        // 模拟校验
        return ticket.equals("success");
    }

}
