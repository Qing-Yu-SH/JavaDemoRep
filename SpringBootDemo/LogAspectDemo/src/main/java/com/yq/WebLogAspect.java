package com.yq;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-18 10:10
 **/
@Slf4j
@Aspect
@Component
public class WebLogAspect {

    @Pointcut("@annotation(WebLog) || @within(WebLog)")
    public void getLogAnnotation(){
    }


    @Around("getLogAnnotation()")
    public Object handleLog(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = requestAttributes.getRequest();
        log.info("--------------------------------------------------------------------------------");
        log.info("URL\t {}",request.getRequestURL().toString());
        log.info("HTTP Method\t {}",request.getMethod());
        getDesc(joinPoint);
        log.info("--------------------------------------------------------------------------------");
        long startTime = System.currentTimeMillis();
        try {
            Object ans = joinPoint.proceed();
            return ans;
        } finally {
            log.info("执行耗时\t {}ms",System.currentTimeMillis() - startTime);
        }
    }

    private void getDesc(ProceedingJoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        WebLog webLog = method.getAnnotation(WebLog.class);
        if(webLog == null){
            webLog = (WebLog) joinPoint.getSignature().getDeclaringType().getAnnotation(WebLog.class);
        }
        if(webLog!=null && StrUtil.isNotEmpty(webLog.desc())){
            log.info("描述\t {}", webLog.desc());
        }
    }

}
