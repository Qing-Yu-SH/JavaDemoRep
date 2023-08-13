package com.yq.pattern.facade.config;

/**
 * @program: JavaDemoRep
 * @description: 配置类注解定义
 * @author: Yuqing
 * @create: 2023-08-13 19:09
 **/
// @ConfigurationProperties("yq.door")
public class StarterServiceProperties {

    private String userStr;

    public String getUserStr() {
        return userStr;
    }

    public void setUserStr(String userStr) {
        this.userStr = userStr;
    }

}
