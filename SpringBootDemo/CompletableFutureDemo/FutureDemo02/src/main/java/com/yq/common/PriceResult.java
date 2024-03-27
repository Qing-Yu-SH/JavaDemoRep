package com.yq.common;

import lombok.Data;

/**
 * @program: JavaDemoRep
 * @description: 价格结果
 * @author: Yuqing
 * @create: 2024-03-26 13:55
 **/
@Data
public class PriceResult {

    private int price;
    private int discounts;
    private int realPrice;
    private String platform;

    public PriceResult(String platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return
                "【平台：" + platform  +
                        ", 原价：" + price +
                        ", 折扣：" + discounts +
                        ", 实付价：" + realPrice +
                        "】";
    }


}
