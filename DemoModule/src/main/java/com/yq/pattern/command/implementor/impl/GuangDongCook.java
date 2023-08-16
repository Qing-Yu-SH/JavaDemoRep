package com.yq.pattern.command.implementor.impl;

import com.yq.pattern.command.implementor.ICook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: JavaDemoRep
 * @description: 实现者具体实现 - 广东厨师
 * @author: Yuqing
 * @create: 2023-08-16 10:09
 **/
public class GuangDongCook implements ICook {

    private Logger logger = LoggerFactory.getLogger(GuangDongCook.class);

    @Override
    public void doCook() {
        logger.info("⼴东厨师，烹饪鲁菜，宫廷最⼤菜系，以孔府⻛味为⻰头");
    }

}
