package com.yq.pattern.template.strategy;

import java.util.List;

/**
 * @program: JavaDemoRep
 * @description: 策略
 * @author: Yuqing
 * @create: 2023-08-02 16:08
 **/
public class Strategy {

    // 策略 id
    private Long strategyId;

    // 奖品列表
    private List<Award> awardList;

    // 抽奖算法 id
    private Integer algorithm;

    public Strategy() {
    }

    public Strategy(Long strategyId, List<Award> awardList, Integer algorithm) {
        this.strategyId = strategyId;
        this.awardList = awardList;
        this.algorithm = algorithm;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public List<Award> getAwardList() {
        return awardList;
    }

    public void setAwardList(List<Award> awardList) {
        this.awardList = awardList;
    }

    public Integer getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(Integer algorithm) {
        this.algorithm = algorithm;
    }
}
