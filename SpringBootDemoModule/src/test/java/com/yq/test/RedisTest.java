package com.yq.test;

import com.yq.util.RedisClient;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.Test;

import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-06 10:04
 **/
public class RedisTest extends BasicTest{



    @Test
    public void test_redis(){
        RedisClient.hIncr("web_statistic","user_count",1);
        System.out.println(RedisClient.hGet("web_statistic","user_count",Integer.class));
    }

    @Test
    public void test_topN(){
        RedisClient.zIncrBy("activity_rank_231006","user_1",20);
        RedisClient.zIncrBy("activity_rank_231006","user_2",200);
        RedisClient.zIncrBy("activity_rank_231006","user_3",16);
        List<ImmutablePair<String, Double>> rank = RedisClient.zTopNScore("activity_rank_231006", 2);
        int i = 1;
        for (ImmutablePair<String, Double> pair : rank) {
            System.out.println("用户：" + pair.getLeft() + "\t分数：" + pair.getRight() + "\t排名：" + (i++));
        }
    }

}
