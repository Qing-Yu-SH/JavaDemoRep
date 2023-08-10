package com.yq.structure.disjointset;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: 抽象类
 * @author: Yuqing
 * @create: 2023-08-10 12:14
 **/
public abstract class AbstractDisjointSet {

    public int[] items;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("坐标 | ");
        for (int i = 0; i < items.length; i++) {
            builder.append(i);
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
