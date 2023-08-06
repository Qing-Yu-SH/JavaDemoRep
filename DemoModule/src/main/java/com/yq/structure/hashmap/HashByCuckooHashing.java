package com.yq.structure.hashmap;

import java.util.*;

/**
 * @program: JavaDemoRep
 * @description: 布谷鸟哈希(Cuckoo hashing)是一种开放寻址冲突解决技术
 *               它保证O(1)最坏情况下的查找复杂性和插入的恒定摊销时间
 *               通过使用两个散列函数而不是仅一个散列函数来解决冲突
 * @author: Yuqing
 * @create: 2023-08-06 16:37
 **/
public class HashByCuckooHashing<K,V> implements Map<K,V> {

    static final int DEFAULT_INITIAL_CAPACITY = 8;
    static final int MAXIMUM_CAPACITY = 1 << 30;
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    transient Node<K,V>[] tab;

    transient int size;

    int threshold;

    final float loadFactor;

    final transient HashFunction<K> hash1;

    final transient HashFunction<K> hash2;

    static final Object NULL_KEY = new Object();

    public HashByCuckooHashing() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        threshold = (int) (DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
        tab = new Node[DEFAULT_INITIAL_CAPACITY];
        hash1 = new DefaultHashFunction<>(2);
        hash2 = new DefaultHashFunction<>(3);
    }

    /** 添加键值对 */
    @Override
    public V put(K key, V value) {
        return put(key, value, false);
    }

    private V put(K key,V value,boolean isRehash){
        Object k = maskNull(key);

        if(containsKey(k)){
            return null;
        }

        if(insertEntry(new Node<>((K) k,value))){
            // 非扩容的情况下，需要将 size 加 1
            if(!isRehash){
                size++;
            }
            return null;
        }

        // 扩容
        rehash(2 * tab.length);
        // 扩容后重新添加
        return put(key,value);
    }

    private boolean insertEntry(Node<K,V> e){
        int count = 0;
        Node<K,V> current = e;
        int index = hash(hash1,current.key);
        while(current!=e || count<tab.length){
            Node<K,V> temp = tab[index];
            if(temp == null){
                tab[index] = current;
                return true;
            }

            // 将当前节点放入该位置；将原节点重新哈希
            tab[index] = current;
            current = temp;
            if(index == hash(hash1,current.key)){
                index = hash(hash2,current.key);
            }else {
                index = hash(hash1,current.key);
            }

            ++count;
        }
        return false;
    }

    /** 根据 key，获取 value */
    @Override
    public V get(K key) {
        Object k = maskNull(key);

        int hash = hash(hash1,k);
        Object k2;
        Node<K,V> e = tab[hash];
        if(e!=null && ((k2=e.key)==k || k.equals(k2))){
            return e.value;
        }

        hash = hash(hash2,k);
        e = tab[hash];
        if(e!=null && ((k2=e.key)==k || k.equals(k2))){
            return e.value;
        }

        return null;
    }

    static <T> T maskNull(T key) {
        return key == null ? (T) NULL_KEY : key;
    }

    static <T> T unmaskNull(T key) {
        return (key == NULL_KEY ? null : key);
    }

    /** 扩容 */
    private void rehash(int newCapacity){
        Node<K,V>[] oldTable = tab;
        int oldCapacity = tab.length;

        if(oldCapacity >= MAXIMUM_CAPACITY){
            threshold = Integer.MAX_VALUE;
            return;
        }

        Node<K,V>[] newTable = new Node[newCapacity];
        tab = newTable;
        for(Node<K,V> e: tab){
            if(e != null){
                put(e.key,e.value,true);
            }
        }

        threshold = (int) (newCapacity * loadFactor);
    }

    /** 将节点保存进 Set */
    public Set<Node<K,V>> entrySet(){
        Set<Node<K,V>> es = new HashSet<>();
        for(Node<K,V> e: tab){
            if(e != null){
                es.add(e);
            }
        }
        return es;
    }

    /** 是否包含 key */
    public boolean containsKey(Object key){
        Iterator<Node<K, V>> i = entrySet().iterator();
        if(key == null){
            while (i.hasNext()){
                Node<K, V> e = i.next();
                if(e.getKey() == null){
                    return true;
                }
            }
        }else{
            while (i.hasNext()){
                Node<K, V> e = i.next();
                if(key.equals(e.getKey())){
                    return true;
                }
            }
        }
        return false;
    }

    /** 哈希函数 */
    interface HashFunction<T> {
        int hash(Object key, int limit);
    }

    static class DefaultHashFunction<T> implements HashFunction<T> {

        private static final Random ENGINE = new Random();
        private int rounds;

        public DefaultHashFunction(int rounds) {
            this.rounds = rounds;
        }

        public int hash(Object key, int limit) {
            ENGINE.setSeed(key.hashCode());
            int h = ENGINE.nextInt(limit);
            for (int i = 1; i < this.rounds; i++) {
                h = ENGINE.nextInt(limit);
            }
            return h;
        }

    }

    private int hash(HashFunction<K> func, Object key) {
        return func.hash(key, tab.length);
    }


    /** 节点 */
    static class Node<K,V>{
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public final boolean equals(Object o){
            if(!(o instanceof Node)){
                return false;
            }

            Node e = (Node) o;
            Object k1 = getKey();
            Object k2 = e.getKey();
            if(Objects.equals(k1,k2)){
                Object v1 = getValue();
                Object v2 = e.getValue();
                return Objects.equals(v1,v2);
            }
            return false;
        }

        public final K getKey() {
            return HashByCuckooHashing.unmaskNull(key);
        }

        public final V getValue() {
            return value;
        }

        public final int hashCode() {
            return (key == null ? 0 : key.hashCode())
                    ^ (value == null ? 0 : value.hashCode());
        }

        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final String toString() {
            return getKey() + "=>" + getValue();
        }
    }

    @Override
    public String toString() {
        return "HashByCuckooHashing{" +
                "tab=" + Arrays.toString(tab) +
                '}';
    }
}
