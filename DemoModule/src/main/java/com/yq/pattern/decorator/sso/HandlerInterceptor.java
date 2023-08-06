package com.yq.pattern.decorator.sso;

/**
 * @program: JavaDemoRep
 * @description: 模拟 Spring 的 HandlerInterceptor
 *               实际的单点登录开发会基于； org.springframework.web.servlet.HandlerInterceptor 实现
 * @author: Yuqing
 * @create: 2023-08-06 13:36
 **/
public interface HandlerInterceptor {

    boolean preHandle(String request, String response, Object handler);

}
