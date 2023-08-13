package com.yq.structure.test;

import com.yq.structure.bloomfilter.BloomFilter;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-12 15:41
 **/
public class BloomFilterTest {

    private Logger logger = LoggerFactory.getLogger(BloomFilterTest.class);

    @Test
    public void test_BloomFilter(){
        String val00 = "Java";
        String val01 = "https://bugstack.cn/md/algorithm/data-structures/2022-10-05-bloom-filter.html";
        String val02 = "https://github.com/Qing-Yu-SH/JavaDemoRep";
        String val03 = "https://github.com/Qing-Yu-SH";
        BloomFilter filter = new BloomFilter(Integer.MAX_VALUE, new int[]{2, 16, 52, 82});
        filter.add(val00);
        filter.add(val01);
        filter.add(val02);
        logger.info("测试结果 val00：{} 布隆过滤器：{}", val00, filter.contains(val00));
        logger.info("测试结果 val01：{} 布隆过滤器：{}", val01, filter.contains(val01));
        logger.info("测试结果 val02：{} 布隆过滤器：{}", val02, filter.contains(val02));
        logger.info("测试结果 val02：{} 布隆过滤器：{}", val03, filter.contains(val03));
    }

}
