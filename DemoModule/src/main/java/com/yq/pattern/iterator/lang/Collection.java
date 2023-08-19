package com.yq.pattern.iterator.lang;

/**
 * @program: JavaDemoRep
 * @description: 集合功能接⼝定义
 *               定义了两个泛型：E、L（JDK 中的 Collection 接口只有一个泛型 E）；E 代表树节点，L 代表链接
 * @author: Yuqing
 * @create: 2023-08-19 09:48
 **/
public interface Collection<E,L> extends Iterable<E> {

    boolean add(E e);

    boolean remove(E e);

    boolean addLink(String key, L l);

    boolean removeLink(String key);

    Iterator<E> iterator();

}
