package com.yq.pattern.template;

import com.yq.pattern.template.algorithm.EntiretyRateRandomDrawAlgorithm;
import com.yq.pattern.template.algorithm.IDrawAlgorithm;
import com.yq.pattern.template.strategy.Award;
import com.yq.pattern.template.strategy.Strategy;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: 配置类；将 抽奖算法和抽奖策略注入相应的容器中
 * @author: Yuqing
 * @create: 2023-08-02 16:32
 **/
public class DrawConfig {

    protected static Map<Integer, IDrawAlgorithm> drawAlgorithmMap = new HashMap<Integer, IDrawAlgorithm>(){{
        put(1,new EntiretyRateRandomDrawAlgorithm());
    }};

    protected static Map<Long, Strategy> strategyMap = new HashMap<Long, Strategy>(){{
        List<Award> awardList = new ArrayList<Award>(){{
           add(new Award(1,"笔记本",2,new BigDecimal("0.1")));
           add(new Award(2,"手机",1,new BigDecimal("0.2")));
           add(new Award(3,"书本",20,new BigDecimal("0.5")));
           add(new Award(4,"笔",0,new BigDecimal("0.7")));
           add(new Award(4,"桌子",0,new BigDecimal("0.2")));
        }};
        Strategy strategy = new Strategy(1L,awardList,1);
        put(1L,strategy);

        List<Award> awardList2 = new ArrayList<Award>(){{
            add(new Award(1,"飞机",1,new BigDecimal("0.01")));
            add(new Award(2,"汽车",1,new BigDecimal("0.02")));
            add(new Award(3,"试卷",200,new BigDecimal("0.9")));
            add(new Award(4,"自行车",10,new BigDecimal("0.2")));
            add(new Award(4,"鞋子",0,new BigDecimal("0.2")));
            add(new Award(4,"书籍",2,new BigDecimal("0.6")));
        }};
        Strategy strategy2 = new Strategy(2L, awardList2, 1);
        put(2L,strategy2);
    }};


}
