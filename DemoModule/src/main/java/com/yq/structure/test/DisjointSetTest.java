package com.yq.structure.test;

import com.yq.structure.disjointset.*;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-10 12:22
 **/
public class DisjointSetTest {

    private static Logger logger = LoggerFactory.getLogger(DisjointSetTest.class);

    @Test
    public void test_DefaultDisjoint_01(){
        DefaultDisjoint_01 defaultDisjoint_01 = new DefaultDisjoint_01(10);
        defaultDisjoint_01.union(0,1);
        defaultDisjoint_01.union(1,2);
        defaultDisjoint_01.union(6,8);
        logger.info(defaultDisjoint_01.toString());
        logger.info("当前子集数量：{}", defaultDisjoint_01.getCount());
    }

    @Test
    public void test_RoughDisjoint_02(){
        RoughDisjoint_02 roughDisjoint_02 = new RoughDisjoint_02(10);
        roughDisjoint_02.union(0,1);
        roughDisjoint_02.union(1,2);
        roughDisjoint_02.union(6,8);
        logger.info(roughDisjoint_02.toString());
        logger.info("当前子集数量：{}", roughDisjoint_02.getCount());
    }

    @Test
    public void test_CountDisjoint_03(){
        CountDisjoint_03 countDisjoint_03 = new CountDisjoint_03(10);
        countDisjoint_03.union(0,1);
        countDisjoint_03.union(1,2);
        countDisjoint_03.union(6,8);
        logger.info(countDisjoint_03.toString());
        logger.info("当前子集数量：{}", countDisjoint_03.getCount());
    }

    @Test
    public void test_SortDisjoint_04(){
        SortDisjoint_04 sortDisjoint_04 = new SortDisjoint_04(10);
        sortDisjoint_04.union(0,1);
        sortDisjoint_04.union(1,2);
        sortDisjoint_04.union(6,8);
        logger.info(sortDisjoint_04.toString());
        logger.info("当前子集数量：{}", sortDisjoint_04.getCount());
    }

    @Test
    public void test_CompressDisjoint_05(){
        CompressDisjoint_05 compressDisjoint_05 = new CompressDisjoint_05(10);
        compressDisjoint_05.union(0,1);
        compressDisjoint_05.union(1,2);
        compressDisjoint_05.union(6,8);
        logger.info(compressDisjoint_05.toString());
        logger.info("当前子集数量：{}", compressDisjoint_05.getCount());
    }

}
