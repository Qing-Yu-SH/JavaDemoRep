package com.yq.pattern.template.impl;

import com.yq.pattern.template.AbstractDrawBase;
import com.yq.pattern.template.algorithm.IDrawAlgorithm;
import com.yq.pattern.template.strategy.Award;
import com.yq.pattern.template.strategy.Strategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description: 实现类
 * @author: Yuqing
 * @create: 2023-08-02 18:46
 **/
public class DrawExecImpl extends AbstractDrawBase {

    @Override
    protected Strategy queryStrategyById(Long strategyId) {
        return strategyMap.get(strategyId);
    }

    @Override
    protected List<Integer> queryExcludeAwardIds(Strategy strategy) {
        List<Award> awardList = strategy.getAwardList();
        List<Integer> exclude = new ArrayList<>();
        for(Award award: awardList){
            if(award.getStock() == 0){
                exclude.add(award.getAwardId());
            }
        }
        return exclude;
    }

    @Override
    protected Award drawAlgorithm(Strategy strategy, List<Integer> excludeAwardIds) {
        IDrawAlgorithm iDrawAlgorithm = drawAlgorithmMap.get(strategy.getAlgorithm());
        return iDrawAlgorithm.randomDraw(strategy.getStrategyId(), excludeAwardIds);
    }

}
