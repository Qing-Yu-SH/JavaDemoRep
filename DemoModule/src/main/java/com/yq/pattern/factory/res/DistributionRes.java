package com.yq.pattern.factory.res;

/**
 * @program: JavaDemoRep
 * @description: 配送结果
 * @author: Yuqing
 * @create: 2023-08-02 15:13
 **/
public class DistributionRes {

    /** 用户ID */
    private String uId;

    /** 编码 */
    private Integer code;

    /** 描述 */
    private String info;

    public DistributionRes() {
    }

    public DistributionRes(String uId, Integer code, String info) {
        this.uId = uId;
        this.code = code;
        this.info = info;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
