package com.yq.pattern.builder;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-09 10:21
 **/

/**
 * 建造者模式
 * 建造者模式所完成的内容就是通过将多个简单对象通过⼀步步的组装构建出⼀个复杂对象的过程
 *
 * ⼀些基本物料不会变，⽽其组合经常变化的时候 ，就可以选择这样的设计模式来构建代码
 *
 * 1.定义各类物料：吊顶、涂料、地板、地砖等
 * 2.定义装修清单，定义加入这些物料的相应方法
 * 3.定义 Builder 类，定义各个装修套餐；在使用时，直接调用相应的方法
 */
public class Main {

    public static void main(String[] args) {
        Builder builder = new Builder();

        System.out.println(builder.levelOne(120D).getDetail());

        System.out.println(builder.levelTwo(200D).getDetail());

        System.out.println(builder.levelThree(90D).getDetail());
    }

}
