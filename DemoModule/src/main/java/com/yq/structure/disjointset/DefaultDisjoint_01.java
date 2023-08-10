package com.yq.structure.disjointset;

/**
 * @program: JavaDemoRep
 * @description: 默认合并：在合并节点 A 和 B 时，将与 B 为同一父节点的节点的父节点修改为 A 的父节点
 * @author: Yuqing
 * @create: 2023-08-10 12:17
 **/
public class DefaultDisjoint_01 extends AbstractDisjointSet implements IDisjointSet{

    public DefaultDisjoint_01(int size){
        items = new int[size];
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
        if(i<0 || i>=items.length) throw new IllegalArgumentException("Index out of range.");
        return items[i];
    }

    @Override
    public void union(int parent, int child) {
        int parentVal = find(parent);
        int childVal = find(child);
        if(parentVal == childVal) return;
        for(int i=0;i<items.length;i++){
            if(items[i] == childVal){
                items[i] = parentVal;
            }
        }
    }

}
