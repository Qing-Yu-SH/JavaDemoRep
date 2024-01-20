package com.yq.vo.constants;

import lombok.Getter;

@Getter
public enum StatusEnum {

    SUCCESS(200, "OK"),
    UNEXPECT_ERROR(100_500_001, "非预期异常:%s");

    private int code;

    private String msg;

    StatusEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
