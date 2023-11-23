package com.yq.util;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.BitSet;

/**
 * @program: JavaDemoRep
 * @description: 布隆过滤器；可通过 Redis 实现
 * @author: Yuqing
 * @create: 2023-11-23 17:05
 **/
@Component
public class MyBloomFilter {

    /**
     * 一个长度为10亿的比特位
     */
    private static final int DEFAULT_SIZE = 256 << 22;

    /**
     * 为了降低错误率，使用加法hash算法，所以定义一个8个元素的质数数组
     */
    private static final int[] seeds = {3, 5, 7, 11, 13, 31, 37, 61};

    /**
     * 相当于构建8个不同的hash算法
     */
    private static HashFunction[] functions = new HashFunction[seeds.length];

    /**
     * 初始化布隆过滤器的bitmap,即位数组
     */
    private static BitSet bitset = new BitSet(DEFAULT_SIZE);


    @PostConstruct
    public void init(){
        for(int i=0;i<seeds.length;i++){
            functions[i] = new HashFunction(DEFAULT_SIZE,seeds[i]);
        }
    }


    /**
     * 添加数据
     *
     * @param value 需要加入的值
     */
    public static void add(String value) {
        if (value != null) {
            for (HashFunction f : functions) {
                //计算 hash 值并修改 bitmap 中相应位置为 true
                bitset.set(f.hash(value), true);
            }
        }
    }

    /**
     * 判断相应元素是否存在
     * @param value 需要判断的元素
     * @return 结果
     */
    public static boolean contains(String value) {
        if (value == null) {
            return false;
        }
        boolean ret = true;
        for (HashFunction f : functions) {
            ret = bitset.get(f.hash(value));
            //一个 hash 函数返回 false 则跳出循环
            if (!ret) {
                break;
            }
        }
        return ret;
    }

    class HashFunction {

        private int size;
        private int seed;

        public HashFunction(int size, int seed) {
            this.size = size;
            this.seed = seed;
        }

        public int hash(String value) {
            int result = 0;
            int len = value.length();
            for (int i = 0; i < len; i++) {
                result = seed * result + value.charAt(i);
            }
            int r = (size - 1) & result;
            return (size - 1) & result;
        }
    }

}
