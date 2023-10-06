package com.yq.util;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

/**
 * @program: JavaDemoRep
 * @description: 异常邮件提醒
 * @author: Yuqing
 * @create: 2023-10-06 19:03
 **/
public class AlarmUtil extends AppenderBase<ILoggingEvent> {
    // 间隔为一分钟
    private static final long INTERVAL = 10 * 1000 * 60;
    private long lastAlarmTime = 0;

    @Override
    protected void append(ILoggingEvent iLoggingEvent) {
        if(canAlarm()){
            EmailUtil.sendMail(iLoggingEvent.getLoggerName(),
                    SpringUtil.getConfig("alarm.user","xx@qq.com"),
                    iLoggingEvent.getFormattedMessage());
        }
    }

    private boolean canAlarm(){
        // 简单频率过滤，一分钟内只允许发送一条报警
        long now = System.currentTimeMillis();
        if(now - lastAlarmTime >= INTERVAL){
            lastAlarmTime = now;
            return true;
        }
        return false;
    }
}
