package com.yq.pattern.facade.config;


/**
 * @program: JavaDemoRep
 * @description: 配置服务类
 * @author: Yuqing
 * @create: 2023-08-13 19:05
 **/
public class StarterService {

    private String userStr;

    public StarterService(String userStr) {
        this.userStr = userStr;
    }
    public String[] split(String separatorChar) {
        return this.userStr.split(separatorChar);
    }

}
