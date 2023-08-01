package com.yq;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: HashMap 的安全性测试
 * @author: Yuqing
 * @create: 2023-08-01 16:26
 **/
public class HashMapSaveTest {


    public static void main(String[] args) {
        testCorrectStream();
    }

    /**
     * 迭代器中循环删除数据安全
     */
    private static void testIterator(){

        Map<Integer, String> map = new HashMap() {{
            // 添加数据
            for (int i = 0; i < 10; i++) {
                put(i, "val:" + i);
            }
        }};

        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            if (entry.getKey() == 1) {
                // 删除
                System.out.println("del:" + entry.getKey());
                iterator.remove();
            } else {
                System.out.println("show:" + entry.getKey());
            }
        }
    }


    /**
     * 报 Exception in thread "main" java.util.ConcurrentModificationException
     * For 循环中删除数据非安全
     */
    private static void testForEach(){

        Map<Integer, String> map = new HashMap() {{
            // 添加数据
            for (int i = 0; i < 10; i++) {
                put(i, "val:" + i);
            }
        }};

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (entry.getKey() == 1) {
                // 删除
                System.out.println("del:" + entry.getKey());
                map.remove(entry.getKey());
            } else {
                System.out.println("show:" + entry.getKey());
            }
        }
    }

    /**
     * 报 Exception in thread "main" java.util.ConcurrentModificationException
     * Lambda 循环中删除数据非安全
     */
    private static void testLambda(){

        Map<Integer, String> map = new HashMap() {{
            // 添加数据
            for (int i = 0; i < 10; i++) {
                put(i, "val:" + i);
            }
        }};

        map.forEach((key, value) -> {
            if (key == 1) {
                System.out.println("del:" + key);
                map.remove(key);
            } else {
                System.out.println("show:" + key);
            }
        });
    }

    private static void testCorrectLambda(){

        Map<Integer, String> map = new HashMap() {{
            // 添加数据
            for (int i = 0; i < 10; i++) {
                put(i, "val:" + i);
            }
        }};

        // 根据 map 中的 key 去判断删除
        map.keySet().removeIf(key -> key == 1);
        map.forEach((key, value) -> {
            System.out.println("show:" + key);
        });
    }


    /**
     * 报 Exception in thread "main" java.util.ConcurrentModificationException
     * Stream 循环中删除数据非安全
     */
    private static void testStream(){

        Map<Integer, String> map = new HashMap() {{
            // 添加数据
            for (int i = 0; i < 10; i++) {
                put(i, "val:" + i);
            }
        }};

        map.entrySet().stream().forEach((entry) -> {
            if (entry.getKey() == 1) {
                System.out.println("del:" + entry.getKey());
                map.remove(entry.getKey());
            } else {
                System.out.println("show:" + entry.getKey());
            }
        });
    }

    private static void testCorrectStream(){

        Map<Integer, String> map = new HashMap() {{
            // 添加数据
            for (int i = 0; i < 10; i++) {
                put(i, "val:" + i);
            }
        }};

        map.entrySet().stream().filter(m -> 1 != m.getKey()).forEach((entry) -> {
            if (entry.getKey() == 1) {
                System.out.println("del:" + entry.getKey());
            } else {
                System.out.println("show:" + entry.getKey());
            }
        });
    }


}
