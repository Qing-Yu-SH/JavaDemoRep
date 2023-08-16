package com.yq.pattern.command.implementor.impl;

import com.yq.pattern.command.implementor.ICook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: JavaDemoRep
 * @description: 实现者具体实现 - 浙江厨师
 * @author: Yuqing
 * @create: 2023-08-16 10:10
 **/
public class ZheJiangCook implements ICook {

    private Logger logger = LoggerFactory.getLogger(ZheJiangCook.class);

    @Override
    public void doCook() {
        logger.info("浙江厨师，中国最古⽼的菜系之⼀，宫廷第三⼤菜系");
    }

}
