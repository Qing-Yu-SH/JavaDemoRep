package com.yq.jvm.heap;

/**
 * @program: JavaDemoRep
 * @description: 堆的初始容量和最大容量
 * @author: Yuqing
 * @create: 2023-08-17 15:24
 **/
public class HeapSpaceInitial {

    public static void main(String[] args) {

        long initialMemory = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 / 1024;

        System.out.println("-Xms：" + initialMemory + " M");
        System.out.println("-Xmx：" + maxMemory + " M");

        // 默认值：初始容量的 64 倍是系统内存；最大容量的 4 倍是系统内存
        System.out.println("系统内存大小：" + initialMemory*64.0/1024 + " G");
        System.out.println("系统内存大小：" + maxMemory*4.0/1024 + " G");
    }

}
