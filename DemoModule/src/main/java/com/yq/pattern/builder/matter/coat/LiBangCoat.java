package com.yq.pattern.builder.matter.coat;

import com.yq.pattern.builder.matter.Matter;

import java.math.BigDecimal;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-09 10:43
 **/
public class LiBangCoat implements Matter {

    @Override
    public String scene() {
        return "涂料";
    }

    @Override
    public String brand() {
        return "⽴邦";
    }

    @Override
    public String model() {
        return "默认级别";
    }

    @Override
    public BigDecimal price() {
        return new BigDecimal(650);
    }

    @Override
    public String desc() {
        return "⽴邦始终以开发绿⾊产品、注᯿⾼科技、⾼品质为⽬标，以技术⼒量不断推进科研和开发，满⾜消费者需求。";
    }

}
