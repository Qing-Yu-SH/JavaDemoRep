package com.yq.pattern.command.implementor.impl;

import com.yq.pattern.command.implementor.ICook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: JavaDemoRep
 * @description: 实现者具体实现 - 江苏厨师
 * @author: Yuqing
 * @create: 2023-08-16 10:11
 **/
public class JiangSuCook implements ICook {

    private Logger logger = LoggerFactory.getLogger(JiangSuCook.class);

    @Override
    public void doCook() {
        logger.info("江苏厨师，宫廷第⼆⼤菜系，古今国宴上最受⼈欢迎的菜系");
    }

}
