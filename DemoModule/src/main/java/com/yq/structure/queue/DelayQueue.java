package com.yq.structure.queue;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: JavaDemoRep
 * @description: 延迟队列
 * @author: Yuqing
 * @create: 2023-08-06 09:58
 **/
public class DelayQueue<E extends Delayed> implements BlockingQueue<E> {

    private final transient ReentrantLock lock = new ReentrantLock();
    private final PriorityQueue<E> pq = new PriorityQueue<>();
    private final Condition available = lock.newCondition();

    @Override
    public boolean add(E e) {
        return offer(e);
    }

    @Override
    public boolean offer(E e) {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            pq.offer(e);
            if(pq.peek() == e){
                available.signal();
            }
            return true;
        }finally {
            lock.unlock();
        }
    }

    @Override
    public E poll() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            E first = pq.peek();
            if(first==null || first.getDelay(TimeUnit.NANOSECONDS)>0){
                return null;
            }else{
                return pq.poll();
            }
        }finally {
            lock.unlock();
        }
    }

    @Override
    public E peek() {
        final ReentrantLock lock = this.lock;
        lock.lock();
        try {
            return pq.peek();
        }finally {
            lock.unlock();
        }
    }

    public boolean isEmpty(){
        return pq.isEmpty();
    }

}
