package com.yq.pattern.builder.matter;

import java.math.BigDecimal;

/**
 * @program: JavaDemoRep
 * @description: 物料接⼝
 * @author: Yuqing
 * @create: 2023-08-09 10:35
 **/
public interface Matter {

    /** 场景 */
    String scene();

    /** 品牌 */
    String brand();

    /** 型号 */
    String model();

    /** 价格 */
    BigDecimal price();

    /** 描述 */
    String desc();

}
