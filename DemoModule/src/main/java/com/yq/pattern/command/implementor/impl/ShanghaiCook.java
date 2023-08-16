package com.yq.pattern.command.implementor.impl;

import com.yq.pattern.command.implementor.ICook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-16 10:12
 **/
public class ShanghaiCook implements ICook {

    private Logger logger = LoggerFactory.getLogger(ShanghaiCook.class);

    @Override
    public void doCook() {
        logger.info("上海厨师，沪菜即上海菜，是中国的主要地方风味菜之一。本帮菜是上海菜的别称，是江南地区传统饮食文化的一个重要流派");
    }

}
