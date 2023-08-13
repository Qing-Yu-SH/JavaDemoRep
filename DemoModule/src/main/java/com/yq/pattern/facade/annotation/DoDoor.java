package com.yq.pattern.facade.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: JavaDemoRep
 * @description: 注解
 * @author: Yuqing
 * @create: 2023-08-13 19:12
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DoDoor {

    // 获取某个字段例如⽤户ID
    String key() default "";

    // 确定⽩名单拦截后返回的具体内容
    String returnJSON() default "";

}
