package com.yq.pattern.observer;

import java.util.Date;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-07 20:00
 **/
public class LotteryResult {

    private String uId;

    private String msg;

    private Date dateTime;

    public LotteryResult() {
    }

    public LotteryResult(String uId, String msg, Date dateTime) {
        this.uId = uId;
        this.msg = msg;
        this.dateTime = dateTime;
    }

    public String getUId() {
        return uId;
    }

    public void setUId(String uId) {
        this.uId = uId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "LotteryResult{" +
                "uId='" + uId + '\'' +
                ", msg='" + msg + '\'' +
                ", dateTime=" + dateTime +
                '}';
    }

}
