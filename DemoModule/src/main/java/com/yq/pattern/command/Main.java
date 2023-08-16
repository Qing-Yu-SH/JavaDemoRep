package com.yq.pattern.command;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-16 09:42
 **/

import com.yq.pattern.command.command.impl.GuangDoneCuisine;
import com.yq.pattern.command.command.impl.JiangSuCuisine;
import com.yq.pattern.command.command.impl.ShanghaiCuisine;
import com.yq.pattern.command.command.impl.ZhejiangCuisine;
import com.yq.pattern.command.implementor.impl.GuangDongCook;
import com.yq.pattern.command.implementor.impl.JiangSuCook;
import com.yq.pattern.command.implementor.impl.ShanghaiCook;
import com.yq.pattern.command.implementor.impl.ZheJiangCook;

/**
 * 命令模式
 * 命令模式是⾏为模式中的⼀种，以数据驱动的⽅式将 命令对象 ，可以使⽤构造函数的⽅式传递给调⽤者
 * 调⽤者再提供相应的实现为命令执⾏提供操作⽅法
 *
 * 重要的类：
 * 1.抽象命令类；声明执⾏命令的接⼝和⽅法
 * 2.具体的命令实现类；接⼝类的具体实现，可以是⼀组相似的⾏为逻辑
 * 3.实现者；也就是为命令做实现的具体实现类
 * 4.调⽤者；处理命令、实现的具体操作者，负责对外提供命令服务
 *
 * 场景：客户点单，小二根据菜单，通知厨师做菜
 * 菜品（命令）、小二（调用者）、厨师（实现者）；将实现者通过构造函数注入命令中，命令方法的内部调用实现者的方法实现
 */
public class Main {

    public static void main(String[] args) {
        GuangDoneCuisine guangDoneCuisine = new GuangDoneCuisine(new GuangDongCook());
        ZhejiangCuisine zhejiangCuisine = new ZhejiangCuisine(new ZheJiangCook());
        JiangSuCuisine jiangSuCuisine = new JiangSuCuisine(new JiangSuCook());
        ShanghaiCuisine shanghaiCuisine = new ShanghaiCuisine(new ShanghaiCook());

        XiaoEr xiaoEr = new XiaoEr();
        xiaoEr.order(guangDoneCuisine);
        xiaoEr.order(zhejiangCuisine);
        xiaoEr.order(jiangSuCuisine);
        xiaoEr.order(shanghaiCuisine);

        xiaoEr.placeOrder();
    }

}
