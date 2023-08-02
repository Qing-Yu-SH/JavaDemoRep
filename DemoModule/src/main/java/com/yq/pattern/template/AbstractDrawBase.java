package com.yq.pattern.template;

import com.yq.pattern.template.algorithm.IDrawAlgorithm;
import com.yq.pattern.template.res.DrawResult;
import com.yq.pattern.template.strategy.Award;
import com.yq.pattern.template.strategy.Strategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @program: JavaDemoRep
 * @description: 抽奖类；模板模式的核心
 * @author: Yuqing
 * @create: 2023-08-02 16:07
 **/
public abstract class AbstractDrawBase extends DrawConfig implements IDrawExec{

    protected Logger logger = LoggerFactory.getLogger(AbstractDrawBase.class);

    /**
     * 模板模式的核心方法
     * 定义了抽奖流程
     */
    @Override
    public DrawResult doDrawExec(String uid, Long strategyId) {
        // 1.根据入参策略ID获取抽奖策略配置
        Strategy strategy = this.queryStrategyById(strategyId);

        // 2.将抽奖策略的数据初始化到内存
        this.initRateData(strategy);

        // 3.获取不在抽奖范围内的列表，包括：奖品库存为空、风控策略、临时调整等
        List<Integer> excludeAwardIds = this.queryExcludeAwardIds(strategy);

        // 4.执行抽奖算法
        Award award = this.drawAlgorithm(strategy, excludeAwardIds);

        // 5.包装中奖结果
        return buildDrawResult(uid,strategyId,award);
    }

    protected abstract Strategy queryStrategyById(Long strategyId);


    protected abstract List<Integer> queryExcludeAwardIds(Strategy strategy);

    protected abstract Award drawAlgorithm(Strategy strategy, List<Integer> excludeAwardIds);

    private void initRateData(Strategy strategy){
        IDrawAlgorithm algorithm = drawAlgorithmMap.get(strategy.getAlgorithm());
        algorithm.initAward(strategy.getStrategyId(),strategy.getAwardList());
    }

    private DrawResult buildDrawResult(String uId,Long strategyId,Award award){
        if(award == null){
            logger.info("执行策略抽奖完成【未中奖】，用户：{} 策略ID：{}", uId, strategyId);
            return new DrawResult(uId,strategyId,0,null);
        }

        logger.info("执行策略抽奖完成【已中奖】，用户：{} 策略ID：{} 奖品ID：{} 奖品名称：{}", uId, strategyId, award.getAwardId(), award.getAwardName());

        return new DrawResult(uId,strategyId,1,award.getAwardName());
    }

}
