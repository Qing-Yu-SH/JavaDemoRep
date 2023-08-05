package com.yq.structure.queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

/**
 * @program: JavaDemoRep
 * @description: 优先队列
 *               通过二叉堆实现，保存在一个一位数组中；假设父节点为 n，则左孩子为 2*n+1，右孩子为 2*n+2
 * @author: Yuqing
 * @create: 2023-08-05 22:23
 **/
public class PriorityQueue<E> implements Queue<E>{

    private Logger logger = LoggerFactory.getLogger(PriorityQueue.class);

    private static final int DEFAULT_INITIAL_CAPACITY = 11;

    transient Object[] queue;

    private int size = 0;

    public PriorityQueue() {
        queue = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    @Override
    public boolean add(E e) {
        return offer(e);
    }

    @Override
    public boolean offer(E e) {
        if(e == null){
            throw new NullPointerException();
        }
        int i = size;
        if(i >= queue.length){
            grow(i+1);
        }
        size = i+1;
        if(i == 0){
            queue[0] = e;
        }else{
            siftUp(i,e);
        }
        return true;
    }

    @Override
    public E poll() {
        if(size == 0){
            return null;
        }
        int s = --size;
        E result = (E) queue[0];
        E x = (E) queue[s];
        queue[s] = null;
        if(s != 0){
            siftDown(0,x);
        }
        return result;
    }

    @Override
    public E peek() {
        if(size == 0){
            return null;
        }
        return (E) queue[0];
    }

    public boolean isEmpty(){
        return size == 0;
    }

    private void siftUp(int k, E x) {
        siftUpComparable(k, x);
    }

    private void siftDown(int k, E x) {
        siftDownComparable(k, x);
    }

    /**
     * 二叉堆元素上浮
     */
    @SuppressWarnings("unchecked")
    private void siftUpComparable(int k,E x){
        Comparable<? super E> key = (Comparable<? super E>) x;
        while(k > 0){
            // 获取父节点Idx，相当于除以2
            int parent = (k-1) >>> 1;
            Object e = queue[parent];
            // 元素已大于当前位置的元素，则无需再上浮
            if(key.compareTo((E) e) >= 0){
                break;
            }
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
    }

    /**
     * 二叉堆元素下沉
     */
    @SuppressWarnings("unchecked")
    private void siftDownComparable(int k,E x){
        Comparable<? super E> key = (Comparable<? super E>) x;
        // 找到中间节点
        int half = size >>> 1;
        while(k < half){
            int child = (k<<1) + 1;
            Object c = queue[child];
            int right = child + 1;
            // 左右节点比较，取节点值更小的
            if(right<size && ((Comparable<? super E>) c).compareTo((E) queue[right])>0){
                c = queue[child = right];
            }
            // 目标值小于其左右孩子的最小值，说明此时无需再下沉
            if(key.compareTo((E) c) <= 0){
                break;
            }
            // 目标值小于c值，位置替换，继续比较
            queue[k] = c;
            k = child;
        }
        // 将目标值置于指定位置
        queue[k] = key;
    }

    /**
     * 扩容
     */
    private void grow(int minCapacity){
        int oldCapacity = queue.length;
        int newCapacity = oldCapacity + (oldCapacity<64 ?
                                            oldCapacity+2 :
                                            oldCapacity>>1);
        if(newCapacity - (Integer.MAX_VALUE - 8) > 0){
            newCapacity = (minCapacity > Integer.MAX_VALUE-8) ? Integer.MAX_VALUE:Integer.MAX_VALUE-8;
        }
        queue = Arrays.copyOf(queue,newCapacity);
    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(1);
        queue.offer(5);
        queue.offer(6);
        queue.offer(12);
        queue.offer(16);
        queue.offer(2);
        System.out.println(queue.peek());

        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        while(!queue.isEmpty()){
            Integer x = queue.poll();
            sb.append(x);
            if(!queue.isEmpty()){
                sb.append(" , ");
            }
        }
        sb.append(" ]");
        System.out.println(sb.toString());
    }


}
