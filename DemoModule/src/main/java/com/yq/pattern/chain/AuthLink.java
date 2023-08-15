package com.yq.pattern.chain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @program: JavaDemoRep
 * @description: 链路抽象类
 * @author: Yuqing
 * @create: 2023-08-15 13:40
 **/
public abstract class AuthLink {

    protected Logger logger = LoggerFactory.getLogger(AuthLink.class);
    protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    protected String levelUserId;
    protected String levelUserName;
    private AuthLink next;
    private AuthLink tail;

    public AuthLink(String levelUserId, String levelUserName) {
        this.levelUserId = levelUserId;
        this.levelUserName = levelUserName;
    }

    public AuthLink next() {
        return next;
    }

    public AuthLink append(AuthLink next){
        this.next = next;
        return this;
    }

    public abstract AuthInfo doAuth(String uId, String orderId, Date authDate);

}
