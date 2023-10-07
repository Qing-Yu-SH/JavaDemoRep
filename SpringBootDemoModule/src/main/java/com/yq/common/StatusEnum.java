package com.yq.common;

import lombok.Getter;

/**
 * @program: JavaDemoRep
 * @description: 状态枚举类
 * @author: Yuqing
 * @create: 2023-10-06 14:44
 **/
@Getter
public enum StatusEnum {

    SUCCESS(0, "OK"),

    ILLEGAL_ARGUMENTS(100_400_001, "参数异常"),

    FORBID_ERROR(100_403_001, "无权限"),
    FORBID_NOTLOGIN(100_403_003, "未登录"),

    LOGIN_FAILED_MIXED(400_403_001, "登录失败:%s"),

    UNKNOWN_ERROR(400_404_001,"未知错误");


    private int code;

    private String msg;

    StatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
