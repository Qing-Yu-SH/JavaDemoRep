package com.yq.pattern.chain.impl;

import com.yq.pattern.chain.AuthInfo;
import com.yq.pattern.chain.AuthLink;
import com.yq.pattern.chain.service.AuthService;

import java.util.Date;

/**
 * @program: JavaDemoRep
 * @description: 一级审批
 * @author: Yuqing
 * @create: 2023-08-15 13:47
 **/
public class Level01AuthLink extends AuthLink {

    public Level01AuthLink(String levelUserId, String levelUserName) {
        super(levelUserId, levelUserName);
    }

    @Override
    public AuthInfo doAuth(String uId, String orderId, Date authDate) {
        Date date = AuthService.queryAuthInfo(levelUserId, orderId);
        if(date == null){
            return new AuthInfo("201","单号：",orderId,"；状态：待一级审批，负责人 ",levelUserName);
        }
        AuthLink next = super.next();
        if(next == null){
            return new AuthInfo("200","单号：",orderId,"；状态：一级审批完成，负责人 ",levelUserName,"；时间：",sdf.format(date));
        }

        return next.doAuth(uId,orderId,authDate);
    }

}
