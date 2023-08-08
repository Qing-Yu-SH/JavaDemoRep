package com.yq.pattern.abstractfactory.factory;

/**
 * @program: JavaDemoRep
 * @description: 适配接口
 * @author: Yuqing
 * @create: 2023-08-08 14:26
 **/
public interface ICacheAdapter {

    String get(String key);
    void set(String key, String value);
    void del(String key);

}
