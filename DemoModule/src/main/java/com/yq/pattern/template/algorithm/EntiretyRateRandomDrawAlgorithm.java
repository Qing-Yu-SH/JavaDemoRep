package com.yq.pattern.template.algorithm;

import com.yq.pattern.template.strategy.Award;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description: 抽奖算法
 * @author: Yuqing
 * @create: 2023-08-02 18:23
 **/
public class EntiretyRateRandomDrawAlgorithm extends BaseAlgorithm{


    @Override
    public Award randomDraw(Long strategyId, List<Integer> excludeAwards) {

        BigDecimal differenceDenominator = BigDecimal.ZERO;

        // 排除掉不在抽奖范围的奖品ID集合
        List<Award> differenceAwardRateList = new ArrayList<>();

        // 获取对应策略的奖品区间概率值
        List<Award> awardRateIntervalValList = awardMap.get(strategyId);
        for (Award award: awardRateIntervalValList){
            Integer awardId = award.getAwardId();
            if(excludeAwards.contains(awardId)){
                continue;
            }
            differenceAwardRateList.add(award);
            differenceDenominator = differenceDenominator.add(award.getAwardRate());
        }

        // 前置判断：如果排除后剩下的奖品列表小于等于1，则可以直接返回对应信息
        if (differenceAwardRateList.size() == 0) return null;
        if (differenceAwardRateList.size() == 1) return differenceAwardRateList.get(0);

        // 获取随机概率值
        int randomVal = this.generateSecureRandomIntCode(100);

        Award award = null;
        int cursorVal = 0;
        for(Award aw: differenceAwardRateList){
            int rateVal = aw.getAwardRate().divide(differenceDenominator, 2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).intValue();
            if(randomVal <= (cursorVal+rateVal)){
                award = aw;
                break;
            }
            cursorVal += rateVal;
        }

        return award;
    }
}
