package com.yq.structure.hashmap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @program: JavaDemoRep
 * @description: 罗宾汉哈希(Robin Hood hashing)是一种基于开放寻址的冲突解决算法
 *               冲突是通过偏向从其“原始位置”（即项目被散列到的存储桶）最远或最长探测序列长度（PSL）的元素的位移来解决的
 * @author: Yuqing
 * @create: 2023-08-06 21:01
 **/
public class HashByRobinHoodHashing<K,V> implements Map<K,V> {

    private Logger logger = LoggerFactory.getLogger(HashByRobinHoodHashing.class);

    private static final int DEFAULT_CAPACITY = 8;
    private static final double DEFAULT_LOAD_FACTOR = 0.5;

    private Entry[] table;
    private int size;
    private final double loadFactor;

    public HashByRobinHoodHashing() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashByRobinHoodHashing(int initCap, double lf) {
        clear(initCap);
        loadFactor = lf;
    }

    /** 实例化数组 */
    @SuppressWarnings("unchecked")
    private void clear(int cap) {
        table = (Entry[]) Array.newInstance(Entry.class, cap);
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        Entry entry = new Entry(key, value);
        int idx = hash(key);
        logger.info("当前 key：{}，当前 hash：{}",key,idx);

        // 元素碰撞检测
        while (table[idx] != null) {
            if (entry.offset > table[idx].offset) {
                // 当前偏移量不止一个，则查看条目交换位置，entry 是正在查看的条目，增加现在搜索的事物的偏移量和 idx
                Entry garbage = table[idx];
                table[idx] = entry;
                entry = garbage;
                idx = increment(idx);
                entry.offset++;
            } else if (entry.offset == table[idx].offset) {
                // 当 offset 相等时才需要进行比较，因为键相等时，从相同位置为起点，会经过相同步数，到达同一个位置
                if (table[idx].key.equals(key)) {
                    // 发现相同值
                    V oldVal = table[idx].value;
                    table[idx].value = value;
                    return oldVal;
                } else {
                    idx = increment(idx);
                    entry.offset++;
                }
            } else {
                // 当前偏移量小于我们正在查看的我们增加 idx 和偏移量并继续
                idx = increment(idx);
                entry.offset++;
            }

        }

        // 已经到达了 null 所在的 idx，将新/移动的放在这里
        table[idx] = entry;
        size++;

        // 超过负载因子扩容
        if (size >= loadFactor * table.length) {
            rehash(table.length * 2);
        }

        return null;
    }

    @Override
    public V get(K key) {
        int offset = 0;
        int idx = hash(key);
        while (table[idx] != null) {
            if (offset > table[idx].offset) {
                return null;
            } else if (offset == table[idx].offset) {
                if (table[idx].key.equals(key)) {
                    return table[idx].value;
                } else {
                    offset++;
                    idx = increment(idx);
                }
            } else {
                offset++;
                idx = increment(idx);
            }
        }
        return null;
    }

    /** 删除 */
    public V remove(K key){
        int idx = hash(key);
        int offset = 0;
        while (table[idx] != null){
            if(offset > table[idx].offset){
                return null;
            }else if(offset == table[idx].offset){
                if(table[idx].key.equals(key)){
                    V itemVal = table[idx].value;
                    table[idx] = null;
                    size--;
                    idx = increment(idx);
                    // 调整偏移量
                    while(table[idx]!=null && table[idx].offset!=0){
                        int tempIdx = decrement(idx);
                        table[tempIdx] = table[idx];
                        table[tempIdx].offset = table[tempIdx].offset-1;
                        table[idx] = null;
                        idx = increment(idx);
                    }
                    return itemVal;
                }else {
                    offset++;
                    idx = increment(idx);
                }
            }else {
                offset++;
                idx = increment(idx);
            }
        }
        return null;
    }


    private int hash(K key) {
        return key.hashCode() & (table.length - 1);
    }

    private int increment(int idx) {
        idx++;
        return idx == table.length ? 0 : idx;
    }

    private int decrement(int idx) {
        idx--;
        return idx == -1 ? table.length - 1 : idx;
    }

    private void rehash(int newCap) {
        Entry[] oldTable = table;
        clear(newCap);
        for (Entry e : oldTable) {
            // skip nulls
            if (e != null) {
                this.put(e.key, e.value);
            }
        }
    }

    /** 节点 */
    private class Entry {
        K key;
        V value;
        int offset;

        Entry(K k, V v) {
            this.key = k;
            this.value = v;
            offset = 0;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public int getOffset() {
            return offset;
        }

        @Override
        public String toString() {
            return "{" +
                    "key=" + key +
                    ", value=" + value +
                    ", offset=" + offset +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HashByRobinHoodHashing{" +
                "table=" + Arrays.toString(table) +
                '}';
    }

}
