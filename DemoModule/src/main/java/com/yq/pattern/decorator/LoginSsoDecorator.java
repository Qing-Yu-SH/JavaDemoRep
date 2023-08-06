package com.yq.pattern.decorator;

import com.yq.pattern.decorator.sso.HandlerInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: JavaDemoRep
 * @description: 装饰⻆⾊逻辑实现
 * @author: Yuqing
 * @create: 2023-08-06 13:57
 **/
public class LoginSsoDecorator extends SsoDecorator{

    private Logger logger = LoggerFactory.getLogger(LoginSsoDecorator.class);

    private static Map<String, String> authMap = new ConcurrentHashMap<String, String>();

    static {
        authMap.put("huahua", "queryUserInfo");
        authMap.put("doudou", "queryUserInfo");
    }

    public LoginSsoDecorator(HandlerInterceptor handlerInterceptor) {
        super(handlerInterceptor);
    }

    /**
     * 在原有的基础上，扩展新功能；如果是继承的方式，则需要重写原有功能
     */
    @Override
    public boolean preHandle(String request, String response, Object handler) {
        boolean success = super.preHandle(request, response, handler);
        if (!success) return false;
        String userId = request.substring(8);
        String method = authMap.get(userId);
        logger.info("模拟单点登录⽅法访问拦截校验：{} {}", userId, method);
        // 模拟⽅法校验
        return "queryUserInfo".equals(method);
    }

}
