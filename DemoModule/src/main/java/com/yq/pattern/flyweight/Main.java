package com.yq.pattern.flyweight;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-14 10:26
 **/

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 享元模式
 * 享元模式，主要在于共享通⽤对象，减少内存的使⽤，提升系统的访问效率
 * 这部分共享对象通常⽐较耗费内存或者需要查询⼤量接⼝或者使⽤数据库资源，因此统⼀抽离作为共享对象使⽤
 * 在享元模型的实现中需要使⽤到享元⼯⼚来进⾏管理这部分独⽴的对象和共享的对象，避免出现线程安全的问题
 *
 * 本案例中不变的信息是活动详情，会改变的信息是库存
 * 通过享元工厂去共享活动详情信息，库存则从 Redis 中获取
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    private static ActivityController activityController = new ActivityController();

    public static void main(String[] args) throws InterruptedException {
        for (int idx = 0; idx < 10; idx++) {
            Long req = 10001L;
            Activity activity = activityController.queryActivityInfo(req);
            logger.info("测试结果：{} {}", req, JSON.toJSONString(activity));
            Thread.sleep(1200);
        }
    }

}
