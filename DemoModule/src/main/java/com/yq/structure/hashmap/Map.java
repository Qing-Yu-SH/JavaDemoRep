package com.yq.structure.hashmap;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-06 15:36
 **/
public interface Map<K,V> {

    V put(K key, V value);

    V get(K key);

}
