package com.yq.pattern.chain.impl;

import com.yq.pattern.chain.AuthInfo;
import com.yq.pattern.chain.AuthLink;
import com.yq.pattern.chain.service.AuthService;

import java.text.ParseException;
import java.util.Date;

/**
 * @program: JavaDemoRep
 * @description: 二级审批
 * @author: Yuqing
 * @create: 2023-08-15 13:54
 **/
public class Level02AuthLink extends AuthLink {

    private Date beginTime = sdf.parse("2023-08-15 13:50:00");
    private Date endTime = sdf.parse("2023-08-20 13:50:00");

    public Level02AuthLink(String levelUserId, String levelUserName) throws ParseException {
        super(levelUserId, levelUserName);
    }

    @Override
    public AuthInfo doAuth(String uId, String orderId, Date authDate) {
        Date date = AuthService.queryAuthInfo(levelUserId, orderId);
        if(date == null){
            return new AuthInfo("201","单号：",orderId,"；状态：待二级审批，负责人 ",levelUserName);
        }
        AuthLink next = super.next();
        if(next==null || (authDate.before(beginTime) || authDate.after(endTime))){
            return new AuthInfo("200","单号：",orderId,"；状态：二级审批完成，负责人 ",levelUserName);
        }

        return next.doAuth(uId,orderId,authDate);
    }

}
