package com.yq.structure.test;

import com.yq.structure.tree.AVLTree;
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

    @Test
    public void test_AVLTree(){
        AVLTree tree = new AVLTree();
        for (int i = 0; i < 30; i++) {
            tree.insert(new Random().nextInt(100));
        }
        System.out.println(tree);
    }

    @Test
    public void test_AVLTree2(){
        int[] nums = new int[]{61,3,34,82,1,75,56,65,87,18,3,96,53,50,42,24,69,11,95,69,1,1,84,22,5,70,28,55,38,92};
        AVLTree tree = new AVLTree();
        for(int num: nums){
            tree.insert(num);
        }
        System.out.println(tree);

        tree.delete(53);
        System.out.println(tree);
    }

}
