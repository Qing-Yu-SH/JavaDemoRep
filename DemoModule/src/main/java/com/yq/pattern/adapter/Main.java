package com.yq.pattern.adapter;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-05 14:55
 **/

import com.alibaba.fastjson.JSON;
import com.yq.pattern.adapter.impl.InsideOrderService;
import com.yq.pattern.adapter.impl.POPOrderAdapterServiceImpl;
import com.yq.pattern.adapter.mq.OrderMq;
import com.yq.pattern.adapter.mq.create_account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;

/**
 * 适配器模式
 * 适配器模式的主要作⽤就是把原本不兼容的接⼝，通过适配修改做到统⼀
 *
 * 在业务开发中我们会经常的需要做不同接⼝的兼容，尤其是中台服务，中台需要把各个业务线的各种类
 * 型服务做统⼀包装，再对外提供接⼝进⾏使⽤。⽽这在我们平常的开发中也是⾮常常⻅的
 *
 * 适配器模式要解决的主要问题就是多种差异化类型的接⼝做统⼀输出，这在我们学习⼯⼚⽅法模式中也
 * 有所提到不同种类的奖品处理，其实那也是适配器的应⽤
 *
 * 使⽤了适配器模式就可以让代码：⼲净整洁易于维护、减少⼤量重复的判断和使⽤、让代码更加易于维护和拓展
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        test_MQAdapter();
        test_itfAdapter();
    }

    /**
     * MQ消息适配 测试
     *
     * 注明 MQ消息 和 统一消息体 之间的映射关系，通过反射将属性注入 统一消息体
     */
    public static void test_MQAdapter() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        create_account create_account = new create_account();
        create_account.setNumber("100001");
        create_account.setAddress("河北省.廊坊市.⼴阳区.⼤学⾥职业技术学院");
        create_account.setAccountDate(new Date());
        create_account.setDesc("在校开户");

        HashMap<String, String> link01 = new HashMap<String, String>();
        link01.put("userId", "number");
        link01.put("bizId", "number");
        link01.put("bizTime", "accountDate");
        link01.put("desc", "desc");

        RebateInfo rebateInfo01 = MQAdapter.filter(create_account.toString(), link01);
        System.out.println("mq.create_account(适配前)" +
                create_account.toString());
        System.out.println("mq.create_account(适配后)" +
                JSON.toJSONString(rebateInfo01));

        OrderMq orderMq = new OrderMq();
        orderMq.setOrderId("100002");
        orderMq.setUid("100002");
        orderMq.setSku("2200000");
        orderMq.setCreateOrderTime(new Date());

        HashMap<String, String> link02 = new HashMap<String, String>();
        link02.put("userId", "uid");
        link02.put("bizId", "orderId");
        link02.put("bizTime", "createOrderTime");
        link02.put("desc", "sku");

        RebateInfo rebateInfo02 = MQAdapter.filter(orderMq.toString(), link02);
        System.out.println("mq.orderMq(适配前)" +
                orderMq.toString());
        System.out.println("mq.orderMq(适配后)" +
                JSON.toJSONString(rebateInfo02));
    }

    /**
     * 接口使用适配
     *
     * 定义统一的适配接口，实现两个适配实现类，在相同的方法中分别调用相应的 service 方法进行不同的逻辑处理，最后实现相同的方法功能
     */
    public static void test_itfAdapter(){
        OrderAdapterService popOrderAdapterService = new POPOrderAdapterServiceImpl();
        System.out.println("判断⾸单，接⼝适配(POP)：" +
                popOrderAdapterService.isFirst("100001"));

        OrderAdapterService insideOrderService = new InsideOrderService();
        System.out.println("判断⾸单，接⼝适配(⾃营)：" +
                insideOrderService.isFirst("100001"));
    }

}
