package com.yq.structure.disjointset;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-10 12:18
 **/
public interface IDisjointSet {

    int size();

    int find(int i);

    void union(int parent, int child);

}
