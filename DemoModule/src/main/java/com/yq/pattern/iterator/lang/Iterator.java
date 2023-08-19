package com.yq.pattern.iterator.lang;

/**
 * @program: JavaDemoRep
 * @description: 迭代器定义
 * @author: Yuqing
 * @create: 2023-08-19 09:46
 **/
public interface Iterator<E> {

    boolean hasNext();

    E next();

}
