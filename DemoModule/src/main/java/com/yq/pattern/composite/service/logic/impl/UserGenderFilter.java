package com.yq.pattern.composite.service.logic.impl;

import com.yq.pattern.composite.service.logic.BaseLogic;

import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: 性别过滤器
 * @author: Yuqing
 * @create: 2023-08-03 11:41
 **/
public class UserGenderFilter extends BaseLogic {

    @Override
    public String matterValue(Map<String, String> decisionMatter) {
        return decisionMatter.get("gender");
    }

}
