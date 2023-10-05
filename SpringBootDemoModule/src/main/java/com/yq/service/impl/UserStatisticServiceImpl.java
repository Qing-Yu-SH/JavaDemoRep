package com.yq.service.impl;

import com.yq.service.UserStatisticService;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @program: JavaDemoRep
 * @description: 统计在线人数，适用于单机的场景
 * @author: Yuqing
 * @create: 2023-10-05 20:15
 **/
@Service
public class UserStatisticServiceImpl implements UserStatisticService {

    private AtomicInteger onlineUserCnt = new AtomicInteger(0);

    @Override
    public int incrOnlineUserCnt(int add) {
        return onlineUserCnt.addAndGet(add);
    }

    @Override
    public int getOnlineUserCnt() {
        return onlineUserCnt.get();
    }

}
