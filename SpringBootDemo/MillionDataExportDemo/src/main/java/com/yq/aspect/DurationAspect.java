package com.yq.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-09 22:31
 **/
@Slf4j
@Component
@Aspect
public class DurationAspect {

    @Around("execution(public void com.yq.controller.IndexController.exportExcel*(..))")
    public void exportExcel(ProceedingJoinPoint joinPoint) {
        long startTime = System.nanoTime();
        log.info("开始导出：" + joinPoint.getSignature().getName());
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            Duration time = Duration.ofNanos(System.nanoTime() - startTime);
            log.info("导出结束，消耗了：" + time.getSeconds() + "s");
        }
    }

    @Around("execution(public void com.yq.controller.IndexController.importExcel*(..))")
    public void importExcel(ProceedingJoinPoint joinPoint) {
        long startTime = System.nanoTime();
        log.info("开始导入：" + joinPoint.getSignature().getName());
        try {
            joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            Duration time = Duration.ofNanos(System.nanoTime() - startTime);
            log.info("导入结束，消耗了：" + time.getSeconds() + "s");
        }
    }

}
