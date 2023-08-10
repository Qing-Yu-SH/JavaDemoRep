package com.yq.structure.disjointset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: 并查集 - 压缩合并
 * @author: Yuqing
 * @create: 2023-08-10 13:05
 **/
public class CompressDisjoint_05 extends AbstractDisjointSet implements IDisjointSet{

    public int[] rank;

    public CompressDisjoint_05(int size){
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

        if (i != items[i]){
            // 路径压缩
            items[i] = find(items[i]);
        }

        return items[i];
    }

    @Override
    public void union(int parent, int child) {
        int parentRoot = find(parent);
        int childRoot = find(child);
        if(parentRoot == childRoot) return;

        count--;
        if (rank[parentRoot] > rank[childRoot]) {
            items[childRoot] = items[parentRoot];
        } else if (rank[parentRoot] < rank[childRoot]) {
            items[parentRoot] = items[childRoot];
        } else {
            items[childRoot] = items[parentRoot];
            rank[parentRoot]++;
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
