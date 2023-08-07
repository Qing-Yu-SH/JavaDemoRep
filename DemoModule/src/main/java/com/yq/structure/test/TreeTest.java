package com.yq.structure.test;

import com.yq.structure.tree.BinarySearchTree;
import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-07 13:09
 **/
public class TreeTest {

    @Test
    public void test_BinarySearchTree(){
        BinarySearchTree tree = new BinarySearchTree();
        for (int i = 0; i < 10; i++) {
            tree.insert(new Random().nextInt(100));
        }
        System.out.println(tree);
        Integer del = tree.elementList.get(0);
        tree.delete(del);
        System.out.println("删除节点：" + del);
        System.out.println(tree);
        Integer del2 = tree.elementList.get(2);
        tree.delete(del2);
        System.out.println("删除节点：" + del2);
        System.out.println(tree);
    }

}
