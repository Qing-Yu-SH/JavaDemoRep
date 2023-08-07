package com.yq.pattern.observer;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-07 19:59
 **/

/**
 * 观察者模式
 *
 * 此种设计模式从结构上是满⾜开闭原则的，当你需要新增其他的监听事件或者修改监听逻辑，是不需要改动事件处理类的
 * 但是可能你不能控制调⽤顺序以及需要做⼀些事件结果的返回继续操作，所以使⽤的过程时需要考虑场景的合理性
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LotteryService lotteryService = new LotteryServiceImpl();
        LotteryResult result = lotteryService.draw("2945803495835876");
        logger.info("测试结果：{}", JSON.toJSONString(result));
    }

}
