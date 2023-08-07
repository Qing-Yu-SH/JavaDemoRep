package com.yq.pattern.observer;

import java.util.Date;

/**
 * @program: JavaDemoRep
 * @description: 业务接⼝实现类
 * @author: Yuqing
 * @create: 2023-08-07 20:21
 **/
public class LotteryServiceImpl extends LotteryService {

    private MinibusTargetService minibusTargetService = new MinibusTargetService();

    @Override
    protected LotteryResult doDraw(String uId) {
        String lottery = minibusTargetService.lottery(uId);
        return new LotteryResult(uId,lottery, new Date());
    }

}
