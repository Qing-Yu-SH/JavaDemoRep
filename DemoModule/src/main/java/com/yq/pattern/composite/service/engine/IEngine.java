package com.yq.pattern.composite.service.engine;

import com.yq.pattern.composite.model.aggregates.TreeRich;
import com.yq.pattern.composite.model.vo.EngineResult;

import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: 决策引擎接⼝定义
 * @author: Yuqing
 * @create: 2023-08-03 11:42
 **/
public interface IEngine {

    /**
     * 执行决策过程
     * @param treeId 决策树 Id
     * @param userId 用户 Id
     * @param treeRich 决策树
     * @param decisionMatter 决策物料
     * @return 决策结果
     */
    EngineResult process(final Long treeId, final String userId, TreeRich treeRich, Map<String, String> decisionMatter);

}
