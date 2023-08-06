package com.yq.structure.test;

import com.yq.structure.hashmap.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-06 15:45
 **/
public class HashMapTest {

    private Logger logger = LoggerFactory.getLogger(HashMapTest.class);

    @Test
    public void test_HashBySeparateChaining(){
        Map<String, String> map = new HashBySeparateChaining<>();
        map.put("01", "01");
        map.put("05", "05");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        // 下标碰撞
        map.put("09", "09");
        map.put("12", "12");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));
    }

    @Test
    public void test_HashByOpenAddressing(){
        Map<String, String> map = new HashByOpenAddressing<>();
        map.put("01", "01");
        map.put("05", "05");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        // 下标碰撞
        map.put("09", "09");
        map.put("12", "12");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));
    }

    @Test
    public void test_HashByCoalescedHashing(){
        Map<String, String> map = new HashByCoalescedHashing<>();
        map.put("01", "01");
        map.put("05", "05");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        // 下标碰撞
        map.put("09", "09");
        map.put("12", "12");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));
        logger.info(map.toString());
    }

    @Test
    public void test_HashByCuckooHashing(){
        Map<String, String> map = new HashByCuckooHashing();
        map.put("01", "01");
        map.put("05", "05");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        // 下标碰撞
        map.put("09", "09");
        map.put("12", "12");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));
        logger.info(map.toString());
    }

    @Test
    public void test_HashByRobinHoodHashing(){
        Map<String, String> map = new HashByRobinHoodHashing<>();
        map.put("01", "01");
        map.put("05", "05");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));

        // 下标碰撞
        map.put("09", "09");
        map.put("12", "12");
        logger.info("碰撞前 key：{} value：{}", "01", map.get("01"));
        logger.info(map.toString());
    }

}
