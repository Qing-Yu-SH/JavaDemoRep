package com.yq.structure;

import java.util.Arrays;

/**
 * @program: JavaDemoRep
 * @description: 数组
 * @author: Yuqing
 * @create: 2023-08-05 20:55
 **/
public class ArrayList<E> {

    /**
     * 默认初始化空间
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * 空元素
     */
    private static final Object[] DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA = {};

    /**
     * ArrayList 元素数组缓存区
     */
    transient Object[] elementData;

    /**
     * 元素个数
     */
    int size;

    public ArrayList() {
        elementData = DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA;
    }

    public ArrayList(int size){
        elementData = new Object[size];
    }

    /**
     * 添加元素
     */
    public boolean add(E e){
        int minCapacity = size + 1;
        // 确保内部容量
        if(elementData == DEFAULT_CAPACITY_EMPTY_ELEMENT_DATA){
            minCapacity = Math.max(minCapacity,DEFAULT_CAPACITY);
        }
        // 扩容操作
        if(minCapacity - elementData.length > 0){
            int oldCapacity = elementData.length;
            int newCapacity = oldCapacity + (oldCapacity>>1);
            if(newCapacity - minCapacity < 0){
                newCapacity = minCapacity;
            }
            // Arrays.copyOf 实际上是创建一个新的空间数组，之后调用的 System.arraycopy 迁移到新创建的数组上
            elementData = Arrays.copyOf(elementData,newCapacity);
        }
        // 添加元素
        elementData[size++] = e;
        return true;
    }

    /**
     * 删除元素
     */
    public E remove(int index){
        E oldValue = (E) elementData[index];
        int numMoved = size - index - 1;
        if(numMoved > 0){
            // 从原始数组的第 index+1 索引开始，将其拷贝到 index 索引开始的目标数组中，长度为 numMoved
            System.arraycopy(elementData,index+1,elementData,index,numMoved);
        }
        elementData[--size] = null;
        return oldValue;
    }

    /**
     * 获取元素
     */
    public E get(int index){
        if(index >= size){
            throw new IndexOutOfBoundsException();
        }
        return (E) elementData[index];
    }

    @Override
    public String toString() {
        return "ArrayList{" +
                "elementData=" + Arrays.toString(elementData) +
                ", size=" + size +
                '}';
    }

    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("01");
        list.add("02");
        list.add("03");
        list.add("04");
        list.add("05");
        list.add("06");

        System.out.println(list);

        System.out.println(list.get(2));

        list.remove(0);

        System.out.println(list);
    }

}
