package com.yq.structure.test;

import com.yq.structure.LinkedList;
import org.junit.jupiter.api.Test;

/**
 * @program: JavaDemoRep
 * @description: 链表测试
 * @author: Yuqing
 * @create: 2023-08-06 10:47
 **/
public class LinkedListTest {

    @Test
    public void test_LinkedList(){
        LinkedList<String> list = new LinkedList<>();
        // 添加元素
        list.add("a");
        list.addFirst("b");
        list.addLast("c");
        // 打印列表
        list.printLinkList();
        // 头插元素
        list.addFirst("d");
        // 删除元素
        list.remove("b");
        // 打印列表
        list.printLinkList();
    }
}
