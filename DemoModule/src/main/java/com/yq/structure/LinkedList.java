package com.yq.structure;


/**
 * @program: JavaDemoRep
 * @description: 链表
 *               参考：https://bugstack.cn/md/algorithm/data-structures/2022-07-22-linked-list.html
 * @author: Yuqing
 * @create: 2023-08-05 16:45
 **/
public class LinkedList<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    public void add(E e){
        linkLast(e);
    }

    public void addFirst(E e){
        linkFirst(e);
    }

    public void addLast(E e){
        linkLast(e);
    }

    /**
     * 头插法
     */
    void linkFirst(E e){
        final Node<E> f = first;
        Node<E> newNode = new Node<>(e,null,f);
        first = newNode;
        if(f == null){
            last = newNode;
        }else{
            f.prev = newNode;
        }
        size++;
    }

    /**
     * 尾插法
     */
    void linkLast(E e){
        final Node<E> l = last;
        Node<E> newNode = new Node<>(e,l,null);
        last = newNode;
        if(l == null){
            first = newNode;
        }else{
            l.next = newNode;
        }
        size++;
    }

    /**
     * 拆链
     */
    E unlink(Node<E> x){
        final E element = x.item;
        final Node<E> prev = x.prev;
        final Node<E> next = x.next;

        if(prev == null){
            first = next;
            next.prev = null;
        }else if(next == null){
            last = prev;
            prev.next = null;
        }else{
            prev.next = next;
            next.prev = prev;
        }
        x.prev = null;
        x.next = null;
        x.item = null;
        size--;
        return element;
    }

    /**
     * 删除节点
     */
    public boolean remove(Object o){
        if(o == null){
            for(Node<E> x=first;x!=null;x=x.next){
                if(x.item == null){
                    unlink(x);
                    return true;
                }
            }
        }else{
            for(Node<E> x=first;x!=null;x=x.next){
                if(o.equals(x.item)){
                    unlink(x);
                    return true;
                }
            }
        }
        return false;
    }

    public void printLinkList(){
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        for(Node<E> x=first;x!=null;x=x.next){
            sb.append(x.item);
            if(x.next != null){
                sb.append(" , ");
            }
        }
        sb.append(" ]");
        System.out.println(sb.toString());
    }

    /**
     * 链表节点
     */
    private static class Node<E>{
        E item;
        Node<E> prev;
        Node<E> next;

        public Node(E item, Node<E> prev, Node<E> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }


}
