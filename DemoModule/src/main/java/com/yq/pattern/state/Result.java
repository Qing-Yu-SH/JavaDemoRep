package com.yq.pattern.state;

import com.yq.pattern.state.util.ResponseCode;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-04 11:19
 **/
public class Result {

    private String code;
    private String info;

    public Result() {
    }

    public Result(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public static Result buildResult(ResponseCode code) {
        return new Result(code.getCode(), code.getInfo());
    }

    public static Result buildResult(ResponseCode code,String info) {
        return new Result(code.getCode(), info);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
