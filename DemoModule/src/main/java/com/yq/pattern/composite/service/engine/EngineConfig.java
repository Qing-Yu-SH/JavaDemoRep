package com.yq.pattern.composite.service.engine;

import com.yq.pattern.composite.service.logic.LogicFilter;
import com.yq.pattern.composite.service.logic.impl.UserAgeFilter;
import com.yq.pattern.composite.service.logic.impl.UserGenderFilter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: JavaDemoRep
 * @description: 决策树节点配置
 * @author: Yuqing
 * @create: 2023-08-03 11:45
 **/
public class EngineConfig {

    static Map<String, LogicFilter> logicFilterMap;

    static {
        logicFilterMap = new ConcurrentHashMap<>();
        logicFilterMap.put("userAge",new UserAgeFilter());
        logicFilterMap.put("userGender",new UserGenderFilter());
    }

    public static Map<String, LogicFilter> getLogicFilterMap() {
        return logicFilterMap;
    }

    public static void setLogicFilterMap(Map<String, LogicFilter> logicFilterMap) {
        EngineConfig.logicFilterMap = logicFilterMap;
    }

}
