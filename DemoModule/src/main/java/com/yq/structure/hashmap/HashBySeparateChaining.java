package com.yq.structure.hashmap;

import java.util.LinkedList;


/**
 * @program: JavaDemoRep
 * @description: 拉链寻址
 * @author: Yuqing
 * @create: 2023-08-06 15:34
 **/
public class HashBySeparateChaining<K,V> implements Map<K,V>{

    private final LinkedList<Node<K, V>>[] tab = new LinkedList[8];


    @Override
    public V put(K key, V value) {
        int idx = key.hashCode() & (tab.length-1);
        if(tab[idx] == null){
            tab[idx] = new LinkedList<>();
        }
        tab[idx].add(new Node<>(key, value));
        return null;
    }

    @Override
    public V get(K key) {
        int idx = key.hashCode() & (tab.length-1);
        for (Node<K, V> kvNode : tab[idx]) {
            if (key.equals(kvNode.getKey())) {
                return kvNode.value;
            }
        }
        return null;
    }

    static class Node<K, V> {
        final K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

    }
}
