package com.yq.pattern.chain;

/**
 * @program: JavaDemoRep
 * @description: 审核结果
 * @author: Yuqing
 * @create: 2023-08-15 13:38
 **/
public class AuthInfo {

    private String code;
    private String info = "";

    public AuthInfo(String code,String... infos){
        this.code = code;
        for(String msg: infos){
            info = info.concat(msg);
        }
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
