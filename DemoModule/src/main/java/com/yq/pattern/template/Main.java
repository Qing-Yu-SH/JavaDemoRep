package com.yq.pattern.template;

import com.alibaba.fastjson.JSON;
import com.yq.pattern.template.impl.DrawExecImpl;
import com.yq.pattern.template.res.DrawResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-02 18:50
 **/

/**
 * 模板模式
 * 核心设计思路是通过在，抽象类中定义抽象⽅法的执⾏顺序，并将抽象⽅法设定为只有⼦类实现，但不设计 独⽴访问 的⽅法。简单说也就是把你安排的明明白白的
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        DrawExecImpl drawExec = new DrawExecImpl();

        DrawResult drawResult = drawExec.doDrawExec("user01", 1L);
        logger.info(JSON.toJSON(drawResult).toString());

        DrawResult drawResult2 = drawExec.doDrawExec("user02", 2L);
        logger.info(JSON.toJSON(drawResult2).toString());
    }

}
