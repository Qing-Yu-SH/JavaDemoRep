package com.yq.structure.queue;

/**
 * @program: JavaDemoRep
 * @description: 阻塞队列接口
 * @author: Yuqing
 * @create: 2023-08-06 09:56
 **/
public interface BlockingQueue<E> extends Queue<E>{

    boolean add(E e);

    boolean offer(E e);

}
