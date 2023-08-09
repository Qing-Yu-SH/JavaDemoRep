package com.yq.pattern.builder;

import com.yq.pattern.builder.matter.ceilling.LevelOneCeiling;
import com.yq.pattern.builder.matter.ceilling.LevelTwoCeiling;
import com.yq.pattern.builder.matter.coat.DuluxCoat;
import com.yq.pattern.builder.matter.coat.LiBangCoat;
import com.yq.pattern.builder.matter.floor.ShengXiangFloor;
import com.yq.pattern.builder.matter.tile.DongPengTile;
import com.yq.pattern.builder.matter.tile.MarcoPoloTile;

/**
 * @program: JavaDemoRep
 * @description: 建造者方法
 * @author: Yuqing
 * @create: 2023-08-09 10:55
 **/
public class Builder {

    public IMenu levelOne(Double area) {
        return new DecorationPackageMenu(area, "豪华欧式")
                .appendCeiling(new LevelTwoCeiling()) // 吊顶，⼆级顶
                .appendCoat(new DuluxCoat()) // 涂料，多乐⼠
                .appendFloor(new ShengXiangFloor()); // 地板，圣象
    }

    public IMenu levelTwo(Double area){
        return new DecorationPackageMenu(area, "轻奢⽥园")
                .appendCeiling(new LevelTwoCeiling()) // 吊顶，⼆级顶
                .appendCoat(new LiBangCoat()) // 涂料，⽴邦
                .appendTile(new MarcoPoloTile()); // 地砖，⻢可波罗
    }

    public IMenu levelThree(Double area){
        return new DecorationPackageMenu(area, "现代简约")
                .appendCeiling(new LevelOneCeiling()) // 吊顶，⼆级顶
                .appendCoat(new LiBangCoat()) // 涂料，⽴邦
                .appendTile(new DongPengTile()); // 地砖，东鹏
    }

}
