package com.yq.structure.disjointset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: 并查集 - 数量合并：合并 A 和 B 两个节点，先找到两个节点的父节点，在查看这两个子集中的元素个数，将元素少的移动到元素多的树上
 * @author: Yuqing
 * @create: 2023-08-10 12:42
 **/
public class CountDisjoint_03 extends AbstractDisjointSet implements IDisjointSet {

    private int[] countChild;

    public CountDisjoint_03(int size) {
        items = new int[size];
        countChild = new int[size];
        count = size;
        for(int i=0;i<size;i++){
            items[i] = i;
            countChild[i] = 1;
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
        if(countChild[parentVal] >= countChild[childVal]){
            items[childVal] = items[parentVal];
            countChild[parentVal] += countChild[childVal];
        }else{
            items[parentVal] = items[childVal];
            countChild[childVal] += countChild[parentVal];
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n坐标 | ");
        for (int i = 0; i < items.length; i++) {
            builder.append(i);
            builder.append(" | ");
        }
        builder.append("\n");
        builder.append("-----------------------------------------");
        builder.append("\n");
        builder.append("数量 | ");
        for (int i = 0; i < countChild.length; i++) {
            builder.append(countChild[i]);
            builder.append(" | ");
        }
        builder.append("\n");
        builder.append("-----------------------------------------");
        builder.append("\n");
        builder.append("指向 | ");
        for (int i = 0; i < items.length; i++) {
            builder.append(items[i]);
            builder.append(" | ");
        }
        builder.append("\n");
        builder.append("\n");
        builder.append("-----------------------------------------");
        builder.append("\n");
        builder.append("\n");

        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i=0;i<items.length;i++){
            int parent = items[i];
            if(!map.containsKey(parent)){
                map.put(parent,new ArrayList<>());
            }
            map.get(parent).add(i);
        }

        for(Integer parent: map.keySet()){
            builder.append(parent).append("\t").append("：");
            builder.append(map.get(parent).toString());
            builder.append("\n");
        }

        return builder.toString();
    }


}
