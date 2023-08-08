package com.yq.pattern.abstractfactory.factory.impl;

import com.yq.pattern.abstractfactory.factory.ICacheAdapter;
import com.yq.pattern.abstractfactory.util.matter.IIR;

/**
 * @program: JavaDemoRep
 * @description: IIR 适配类
 * @author: Yuqing
 * @create: 2023-08-08 14:28
 **/
public class IIRCacheAdapter implements ICacheAdapter {

    private IIR iir = new IIR();

    @Override
    public String get(String key) {
        return iir.get(key);
    }

    @Override
    public void set(String key, String value) {
        iir.setVal(key,value);
    }

    @Override
    public void del(String key) {
        iir.del(key);
    }

}
