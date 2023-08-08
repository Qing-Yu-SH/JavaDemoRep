package com.yq.pattern.abstractfactory.factory.impl;

import com.yq.pattern.abstractfactory.factory.ICacheAdapter;
import com.yq.pattern.abstractfactory.util.matter.EGM;

/**
 * @program: JavaDemoRep
 * @description: EGM 适配类
 * @author: Yuqing
 * @create: 2023-08-08 14:27
 **/
public class EGMCacheAdapter implements ICacheAdapter {

    private EGM egm = new EGM();

    @Override
    public String get(String key) {
        return egm.gain(key);
    }

    @Override
    public void set(String key, String value) {
        egm.set(key,value);
    }

    @Override
    public void del(String key) {
        egm.delete(key);
    }

}
