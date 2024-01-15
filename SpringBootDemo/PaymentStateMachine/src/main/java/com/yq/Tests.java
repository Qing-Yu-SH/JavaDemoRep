package com.yq;

/**
 * @program: JavaDemoRep
 * @description: 测试
 *               原文：https://juejin.cn/post/7321569896453521419
 * @author: Yuqing
 * @create: 2024-01-15 10:25
 **/
public class Tests {

    public static void main(String[] args) {
        PaymentStatus targetStatus = PaymentStatus.getTargetStatus(PaymentStatus.PAYING, PaymentEvent.PAY_SUCCESS);
        System.out.println(targetStatus);

        PaymentModel model = new PaymentModel(PaymentStatus.INIT, PaymentStatus.PAYING);
        System.out.println(model);
        model.transferStatusByEvent(PaymentEvent.PAY_SUCCESS);
        System.out.println(model);
    }

}
