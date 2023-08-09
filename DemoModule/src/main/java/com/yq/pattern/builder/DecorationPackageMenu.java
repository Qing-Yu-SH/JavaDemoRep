package com.yq.pattern.builder;

import com.yq.pattern.builder.matter.Matter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description: 装修包实现
 * @author: Yuqing
 * @create: 2023-08-09 10:50
 **/
public class DecorationPackageMenu implements IMenu{

    /**
     * 装修清单
     */
    private List<Matter> list = new ArrayList<Matter>();

    /**
     * 装修价格
     */
    private BigDecimal price = BigDecimal.ZERO;

    /**
     * 面积
     */
    private BigDecimal area;

    /**
     * 装修等级
     */
    private String grade;

    public DecorationPackageMenu() {
    }

    public DecorationPackageMenu(Double area, String grade){
        this.area = new BigDecimal(area);
        this.grade = grade;
    }

    @Override
    public IMenu appendCeiling(Matter matter) {
        list.add(matter);
        price = price.add(area.multiply(new BigDecimal("0.2")).multiply(matter.price()));
        return this;
    }

    @Override
    public IMenu appendCoat(Matter matter) {
        list.add(matter);
        price = price.add(area.multiply(new BigDecimal("1.4")).multiply(matter.price()));
        return this;
    }

    @Override
    public IMenu appendFloor(Matter matter) {
        list.add(matter);
        price = price.add(area.multiply(matter.price()));
        return this;
    }

    @Override
    public IMenu appendTile(Matter matter) {
        list.add(matter);
        price = price.add(area.multiply(matter.price()));
        return this;
    }

    @Override
    public String getDetail() {
        StringBuilder detail = new StringBuilder("\r\n-------------------------------------------------------\r\n" +
                "装修清单" + "\r\n" +
                        "套餐等级：" + grade + "\r\n" +
                        "套餐价格：" + price.setScale(2, BigDecimal.ROUND_HALF_UP) +
                        " 元\r\n" +
                        "房屋⾯积：" + area.doubleValue() + " 平⽶\r\n" +
                        "材料清单：\r\n");

        for (Matter matter: list){
            detail.append(matter.scene()).append("：")
                    .append(matter.brand()).append("、")
                    .append(matter.model()).append("、平⽶价 格：")
                    .append(matter.price()).append(" 元。\n");
        }

        return detail.toString();
    }

}
