package com.yq.pattern.prototype;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-10 10:34
 **/

/**
 * 原型模式
 * 原型模式主要解决的问题就是创建重复对象，⽽这部分对象内容本身⽐较复杂，⽣成过程可能从库或者RPC接⼝中获取数据，耗时较⻓，因此采⽤克隆的⽅式节省时间
 *
 */
public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        QuestionBankController controller = new QuestionBankController();
        QuestionBank paper = controller.createPaper("202115106", "yq");
        System.out.println(paper);

        QuestionBank paper2 = controller.createPaper("202115102", "xw");
        System.out.println(paper2);
    }

}
