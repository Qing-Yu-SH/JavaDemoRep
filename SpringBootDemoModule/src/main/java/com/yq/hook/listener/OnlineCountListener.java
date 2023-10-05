package com.yq.hook.listener;

import com.yq.service.UserStatisticService;
import com.yq.util.SpringUtil;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 15:01
 **/
@WebListener
public class OnlineCountListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se);
        SpringUtil.getBean(UserStatisticService.class).incrOnlineUserCnt(1);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
        SpringUtil.getBean(UserStatisticService.class).incrOnlineUserCnt(-1);
    }

}
