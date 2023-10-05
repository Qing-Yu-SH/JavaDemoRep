package com.yq.service;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 20:14
 **/
public interface UserStatisticService {

    int incrOnlineUserCnt(int add);

    int getOnlineUserCnt();

}
