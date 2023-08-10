package com.yq.structure.disjointset;

/**
 * @program: JavaDemoRep
 * @description: 粗暴合并：合并节点 A 和 B，直接将节点 B 的父节点修改为节点 A 的父节点
 * @author: Yuqing
 * @create: 2023-08-10 12:29
 **/
public class RoughDisjoint_02 extends AbstractDisjointSet implements IDisjointSet{

    public RoughDisjoint_02(int size) {
        items = new int[size];
        count = size;
        for(int i=0;i<size;i++){
            items[i] = i;
        }
    }

    @Override
    public int size() {
        return items.length;
    }

    @Override
    public int find(int i) {
        if (i < 0 || i >= items.length)
            throw new IllegalArgumentException("Index out of range.");

        while (i != items[i]){
            i = items[i];
        }
        return i;
    }

    @Override
    public void union(int parent, int child) {
        int parentVal = find(parent);
        int childVal = find(child);
        if(parentVal == childVal) return;
        count--;
        items[childVal] = parentVal;
    }

}
