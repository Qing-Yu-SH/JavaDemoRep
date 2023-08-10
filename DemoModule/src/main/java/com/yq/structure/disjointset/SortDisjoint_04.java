package com.yq.structure.disjointset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: 并查集 - 排序合并：通过 rank 记录树高；将树低的树挂到树高的树上
 * @author: Yuqing
 * @create: 2023-08-10 12:54
 **/
public class SortDisjoint_04 extends AbstractDisjointSet implements IDisjointSet{

    /** 记录树高 */
    private int[] rank;

    public SortDisjoint_04(int size) {
        items = new int[size];
        rank = new int[size];
        count = size;
        for(int i=0;i<size;i++){
            items[i] = i;
            rank[i] = 1;
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
        // 将高度低的树挂到高度高的树上
        if(rank[parentVal] > rank[childVal]){
            items[childVal] = parentVal;
        }else if(rank[parentVal] < rank[childVal]){
            items[parentVal] = childVal;
        }else{
            // 两棵树相等时，合并后，树高度会加 1
            items[parentVal] = childVal;
            rank[childVal]++;
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
        builder.append("排序 | ");
        for (int i = 0; i < rank.length; i++) {
            builder.append(rank[i]);
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
