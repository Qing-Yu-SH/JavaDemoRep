package com.yq.structure.queue;

/**
 * @program: JavaDemoRep
 * @description: 单端队列接口
 * @author: Yuqing
 * @create: 2023-08-05 21:55
 **/
public interface Queue<E> {

    boolean add(E e);

    boolean offer(E e);

    E poll();

    E peek();

}
