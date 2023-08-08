package com.yq.pattern.abstractfactory;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-08 14:03
 **/

import com.yq.pattern.abstractfactory.factory.JDKProxy;
import com.yq.pattern.abstractfactory.factory.impl.EGMCacheAdapter;
import com.yq.pattern.abstractfactory.factory.impl.IIRCacheAdapter;
import com.yq.pattern.abstractfactory.service.CacheService;
import com.yq.pattern.abstractfactory.service.impl.CacheServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象工厂模式
 *
 * 抽象⼯⼚模式与⼯⼚⽅法模式虽然主要意图都是为了解决，接⼝选择问题。但在实现上，抽象⼯⼚是⼀个中⼼⼯⼚，创建其他⼯⼚的模式
 *
 * 当前场景：以前是单机 Redis，通过 CacheService 调用相应 API；随着业务的发展，需要扩展到集群，目前有两个集群 EGM 和 IIR，调用的方法不一样
 * 为了统一 EGM 和 IIR 调用方法，通过适配接口进行适配，分别实现这两个类的适配类
 *
 * 为了兼容其他地方的服务调用，我们仍需要通过 CacheService 来完成服务调用
 * 所以建立了抽象工厂，为 CacheService 实例化代理类，并在 InvocationHandler 中注入实际要调用的服务（EGM or IIR）
 * 通过代理类调用方法时，会被拦截，实际调用的是注入到 InvocationHandler 中的服务的方法
 */

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        CacheService proxy_EGM = JDKProxy.getProxy(CacheServiceImpl.class, new EGMCacheAdapter());
        proxy_EGM.set("user_name_01","yq");
        String name = proxy_EGM.get("user_name_01");
        logger.info("测试结果：{}",name);

        CacheService proxy_IIR = JDKProxy.getProxy(CacheServiceImpl.class, new IIRCacheAdapter());
        proxy_IIR.set("user_name_02","yq");
        String name2 = proxy_EGM.get("user_name_02");
        logger.info("测试结果：{}",name2);
    }

}