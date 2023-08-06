package com.yq.structure.hashmap;

import java.util.Arrays;

/**
 * @program: JavaDemoRep
 * @description: 合并散列：开放寻址的基础上，将存在哈希碰撞的键值对通过 idxOfNext 链接
 * @author: Yuqing
 * @create: 2023-08-06 16:21
 **/
public class HashByCoalescedHashing<K,V> implements Map<K,V> {

    private final Node<K,V>[] tab = new Node[8];

    @Override
    public V put(K key, V value) {
        int idx = key.hashCode() & (tab.length-1);
        if(tab[idx] == null){
            tab[idx] = new Node<>(key, value);
            return null;
        }

        int cursor = tab.length - 1;
        while (tab[cursor]!=null && tab[cursor].key!=key){
            cursor--;
        }
        tab[cursor] = new Node<>(key, value);

        // 将碰撞节点指向这个新节点
        while (tab[idx].idxOfNext != -1){
            idx = tab[idx].idxOfNext;
        }

        tab[idx].idxOfNext = cursor;

        return null;
    }

    @Override
    public V get(K key) {
        int idx = key.hashCode() & (tab.length-1);
        if(tab[idx] == null){
            return null;
        }
        while(tab[idx].key != key){
            if(tab[idx].idxOfNext == -1) return null;
            idx = tab[idx].idxOfNext;
        }

        return tab[idx].value;
    }

    static class Node<K, V> {
        final K key;
        V value;
        int idxOfNext;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.idxOfNext = -1;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    ", idxOfNext=" + idxOfNext +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HashByCoalescedHashing{" +
                "tab=" + Arrays.toString(tab) +
                '}';
    }

}
