package com.yq.pattern.iterator.lang;

/**
 * @program: JavaDemoRep
 * @description: 可迭代接⼝定义
 * @author: Yuqing
 * @create: 2023-08-19 09:47
 **/
public interface Iterable<E> {

    Iterator<E> iterator();

}
