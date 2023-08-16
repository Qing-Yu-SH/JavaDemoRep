package com.yq.pattern.command.command.impl;

import com.yq.pattern.command.command.ICuisine;
import com.yq.pattern.command.implementor.ICook;

/**
 * @program: JavaDemoRep
 * @description: 具体命令实现 - 浙江
 * @author: Yuqing
 * @create: 2023-08-16 10:06
 **/
public class ZhejiangCuisine implements ICuisine {

    private ICook cook;

    public ZhejiangCuisine(ICook cook) {
        this.cook = cook;
    }

    @Override
    public void cook() {
        cook.doCook();
    }

}
