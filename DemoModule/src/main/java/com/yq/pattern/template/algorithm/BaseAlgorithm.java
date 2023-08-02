package com.yq.pattern.template.algorithm;

import com.yq.pattern.template.strategy.Award;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-02 18:25
 **/
public abstract class BaseAlgorithm implements IDrawAlgorithm{

    protected Map<Long, List<Award>> awardMap = new HashMap<>();

    @Override
    public void initAward(Long strategyId, List<Award> awardList) {
        awardMap.put(strategyId, awardList);
    }

    protected int generateSecureRandomIntCode(int bound){
        return new SecureRandom().nextInt(bound) + 1;
    }

}
