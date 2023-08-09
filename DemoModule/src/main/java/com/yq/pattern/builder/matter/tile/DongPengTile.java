package com.yq.pattern.builder.matter.tile;

import com.yq.pattern.builder.matter.Matter;

import java.math.BigDecimal;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-09 10:46
 **/
public class DongPengTile implements Matter {

    @Override
    public String scene() {
        return "地砖";
    }

    @Override
    public String brand() {
        return "东鹏瓷砖";
    }

    @Override
    public String model() {
        return "10001";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(102);
    }

    @Override
    public String desc() {
        return "东鹏瓷砖以品质铸就品牌，科技推动品牌，⼝碑传播品牌为宗旨，2014年品牌价值132.35亿元，位列建陶⾏业榜⾸。";
    }

}
