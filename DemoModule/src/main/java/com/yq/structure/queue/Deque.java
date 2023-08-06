package com.yq.structure.queue;

/**
 * @program: JavaDemoRep
 * @description: 双端队列接口
 * @author: Yuqing
 * @create: 2023-08-05 21:56
 **/
public interface Deque<E> extends Queue<E> {

    void addFirst(E e);

    void addLast(E e);

}
