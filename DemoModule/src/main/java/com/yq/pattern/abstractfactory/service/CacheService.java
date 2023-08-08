package com.yq.pattern.abstractfactory.service;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-08 14:24
 **/
public interface CacheService {

    String get(final String key);
    void set(String key, String value);
    void del(String key);

}
