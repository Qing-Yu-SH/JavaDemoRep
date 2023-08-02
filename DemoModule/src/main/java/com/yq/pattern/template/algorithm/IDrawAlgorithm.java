package com.yq.pattern.template.algorithm;

import com.yq.pattern.template.strategy.Award;

import java.util.List;

public interface IDrawAlgorithm {

    Award randomDraw(Long strategyId, List<Integer> excludeAwards);

    void initAward(Long strategyId,List<Award> awardList);

}
