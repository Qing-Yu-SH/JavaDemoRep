package com.yq.structure.queue;

import java.util.NoSuchElementException;

/**
 * @program: JavaDemoRep
 * @description: ArrayDeque
 *               参考：https://juejin.cn/post/7239715294229561401#heading-1
 * @author: Yuqing
 * @create: 2023-08-06 12:47
 **/
public class ArrayDeque<E> implements Deque<E> {

    /**
     * 环形数组
     */
    transient Object[] elements;

    /**
     * 头指针：指向第一个元素
     */
    transient int head;

    /**
     * 尾指针：指向最后一个元素的前一个位置
     */
    transient int tail;

    /**
     * 最小容量
     */
    private static final int MIN_INITIAL_CAPACITY = 8;

    public ArrayDeque() {
        elements = new Object[16];
    }

    public ArrayDeque(int numElements) {
        allocateElements(numElements);
    }

    private void allocateElements(int numElements) {
        elements = new Object[calculateSize(numElements)];
    }

    /**
     * 计算数组容量
     * 在初始化的过程中，它需要找到一个大于等于当前传输值的最小的 2 的倍数的一个容量
     */
    private static int calculateSize(int numElements){
        int initialCapacity = MIN_INITIAL_CAPACITY;

        if(numElements >= initialCapacity){
            initialCapacity = numElements;

            // 将 initialCapacity 的最高位 1 之后的所有位都置为 1
            initialCapacity |= (initialCapacity >>> 1);
            initialCapacity |= (initialCapacity >>> 2);
            initialCapacity |= (initialCapacity >>> 4);
            initialCapacity |= (initialCapacity >>> 8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;

            // 如果容量变为负数，则说明发生了溢出，将其缩小为一半
            if(initialCapacity < 0){
                initialCapacity >>>= 1;
            }
        }
        return initialCapacity;
    }

    /**
     * 头插
     */
    @Override
    public void addFirst(E e) {
        if(e == null)
            throw new NullPointerException();
        // 头插
        elements[head = (head-1) & (elements.length-1)] = e;
        if(head == tail)
            doubleCapacity();
    }

    /**
     * 尾插
     */
    @Override
    public void addLast(E e) {
        if(e == null)
            throw new NullPointerException();
        elements[tail] = e;
        tail = (tail+1) & (elements.length-1);
        if(head == tail)
            doubleCapacity();
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean offer(E e) {
        addLast(e);
        return true;
    }

    @Override
    public E poll() {
        return pollFirst();
    }

    @Override
    public E peek() {
        return (E) elements[head];
    }

    /**
     * 入栈
     */
    public void push(E e) {
        addFirst(e);
    }

    /**
     * 出栈
     */
    public E pop() {
        return removeFirst();
    }

    public E removeFirst() {
        E x = pollFirst();
        if (x == null)
            throw new NoSuchElementException();
        return x;
    }

    public E pollFirst(){
        int h = head;
        E result = (E) elements[h];
        if(result == null){
            return null;
        }
        elements[h] = null;
        head = (head+1) & (elements.length-1);
        return result;
    }


    /**
     * 扩容
     */
    private void doubleCapacity(){
        assert head == tail;
        int p = head;
        int n = elements.length;
        int r = n - p;
        // 扩容为原来 2 倍
        int newCapacity = n << 1;
        if (newCapacity < 0)
            throw new IllegalStateException("Sorry, deque too big");
        Object[] a = new Object[newCapacity];
        // 第一个阶段，将原数组中索引位置为 [head,n) 的元素复制到新数组的 [0,r) 位置
        System.arraycopy(elements,p,a,0,r);
        // 第二个阶段，将原数组中索引位置为 [0,head) 的元素复制到新数组的 [r,r+head) 位置
        System.arraycopy(elements,0,a,r,p);
        elements = a;
        head = 0;
        tail = n;
    }

    public String print(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for(int i=head;i!=tail;){
            sb.append(elements[i]);
            i = (i+1) & (elements.length-1);
            if(i != tail){
                sb.append(" , ");
            }
        }
        sb.append(" ]");
        return sb.toString();
    }

}
