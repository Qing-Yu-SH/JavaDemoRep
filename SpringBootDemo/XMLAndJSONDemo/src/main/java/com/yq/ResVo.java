package com.yq;

import lombok.Data;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-17 22:54
 **/
@Data
public class ResVo<T> {

    private int code;
    private String msg;
    private T data;

    public ResVo(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
