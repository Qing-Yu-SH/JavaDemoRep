package com.yq;

import com.yq.util.bloom.UserNameBloomFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-23 18:51
 **/
@SpringBootTest
public class TestDemo {

    @Autowired
    private UserNameBloomFilter bloomFilter;

    @Test
    public void test_bloomFilter(){
        bloomFilter.add("sumAll");
        System.out.println(bloomFilter.contains("sumAll"));
        System.out.println(bloomFilter.contains("sumAll2"));
    }

}
