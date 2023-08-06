package com.yq.pattern.decorator;

import com.yq.pattern.decorator.sso.HandlerInterceptor;

/**
 * @program: JavaDemoRep
 * @description: 抽象类装饰⻆⾊
 * @author: Yuqing
 * @create: 2023-08-06 13:56
 **/
public abstract class SsoDecorator implements HandlerInterceptor {

    private HandlerInterceptor handlerInterceptor;

    public SsoDecorator() {
    }

    public SsoDecorator(HandlerInterceptor handlerInterceptor) {
        this.handlerInterceptor = handlerInterceptor;
    }

    @Override
    public boolean preHandle(String request, String response, Object handler) {
        return handlerInterceptor.preHandle(request,response,handler);
    }

}
