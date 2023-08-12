package com.yq.pattern.bridge;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-12 13:00
 **/

import com.yq.pattern.bridge.channel.WxPay;
import com.yq.pattern.bridge.channel.ZfbPay;
import com.yq.pattern.bridge.model.PayFaceMode;
import com.yq.pattern.bridge.model.PayFingerprintMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * 桥接模式
 * 桥接模式的主要作⽤就是通过将抽象部分与实现部分分离，把多种可匹配的使⽤进⾏组合
 * 说⽩了核⼼实现也就是在A类中含有B类接⼝，通过构造函数传递B类的实现，这个B类就是设计的桥
 *
 * 使用场景：
 *      1.JDBC多种驱动程序的实现
 *      2.同品牌类型的台式机和笔记本平板
 *      3.多⽀付（支付宝、微信）与多模式（人脸识别、指纹、密码）的融合使⽤
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("模拟 支付宝+人脸 \n");
        ZfbPay zfbPay = new ZfbPay(new PayFaceMode());
        String result = zfbPay.transfer("1001", "T1001", new BigDecimal(200000));
        logger.info("用户{}，订单{}，交易{} \n","1001","T1001",result.equals("0000") ? "成功":"失败");
        logger.info("-----------------------------------------------\n");
        logger.info("模拟 微信+指纹 \n");
        WxPay wxPay = new WxPay(new PayFingerprintMode());
        String result2 = wxPay.transfer("1002", "T1002", new BigDecimal(200000));
        logger.info("用户{}，订单{}，交易{} \n","1002","T1002",result2.equals("0000") ? "成功":"失败");
    }

}
