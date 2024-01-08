package com.yq;

import java.lang.annotation.*;

/**
 * @program: JavaDemoRep
 * @description: 日志注解
 * @author: Yuqing
 * @create: 2024-01-08 10:27
 **/
// 用于支持可重复注解。这个注解允许在同一个元素上多次使用相同类型的注解
@Repeatable(LogRecords.class)
// 用于定义注解的作用范围
@Target({ElementType.METHOD, ElementType.TYPE})
// 用于定义注解的保留策略；表示该注解将在运行时保留，并可通过反射机制读取
@Retention(RetentionPolicy.RUNTIME)
// 用于定义注解的可继承性
@Inherited
// 用于定义注解的文档化
@Documented
public @interface LogRecord {
    String success();

    String fail() default "";

    String operator() default ""; // 业务操作场景人

    String type(); // 业务场景 模块范围

    String subType() default ""; // 业务子场景，主要是模块下的功能范围

    String extra() default "";// 一些操作的扩展操作

    String actionType(); // 业务操作类型，比如编辑、新增、删除

}
