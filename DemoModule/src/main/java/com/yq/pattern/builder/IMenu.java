package com.yq.pattern.builder;

import com.yq.pattern.builder.matter.Matter;

/**
 * @program: JavaDemoRep
 * @description: 装修包接⼝
 * @author: Yuqing
 * @create: 2023-08-09 10:49
 **/
public interface IMenu {

    /** 吊顶 */
    IMenu appendCeiling(Matter matter);

    /** 涂料 */
    IMenu appendCoat(Matter matter);

    /** 地板 */
    IMenu appendFloor(Matter matter);

    /** 地砖 */
    IMenu appendTile(Matter matter);

    /** 明细 */
    String getDetail();

}
