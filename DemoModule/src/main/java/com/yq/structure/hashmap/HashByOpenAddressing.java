package com.yq.structure.hashmap;

/**
 * @program: JavaDemoRep
 * @description: 开放寻址
 * @author: Yuqing
 * @create: 2023-08-06 15:47
 **/
public class HashByOpenAddressing<K,V> implements Map<K,V> {

    private final Node<K,V>[] tab = new Node[8];

    @Override
    public V put(K key, V value) {
        int idx = key.hashCode() & (tab.length-1);
        if(tab[idx] == null){
            tab[idx] = new Node<>(key, value);
        }else{
            for(int i=idx;i < tab.length;i++){
                if(tab[i] == null){
                    tab[i] = new Node<>(key, value);
                    break;
                }
            }
        }
        return null;
    }

    @Override
    public V get(K key) {
        int idx = key.hashCode() & (tab.length-1);
        for(int i=idx;i < tab.length;i++){
            if(tab[i]!=null && tab[idx].key==key){
                return tab[idx].value;
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
    }

}
