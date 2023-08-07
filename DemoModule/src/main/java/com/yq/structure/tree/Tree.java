package com.yq.structure.tree;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-07 23:01
 **/
public interface Tree {

    Node insert(int e);

    Node delete(int e);

    Node search(int e);

}
