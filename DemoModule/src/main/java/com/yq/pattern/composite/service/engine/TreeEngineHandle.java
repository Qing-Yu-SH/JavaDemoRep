package com.yq.pattern.composite.service.engine;

import com.yq.pattern.composite.model.aggregates.TreeRich;
import com.yq.pattern.composite.model.vo.EngineResult;
import com.yq.pattern.composite.model.vo.TreeNode;

import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description:  决策引擎
 * @author: Yuqing
 * @create: 2023-08-03 12:45
 **/
public class TreeEngineHandle extends EngineBase{


    @Override
    public EngineResult process(Long treeId, String userId, TreeRich treeRich, Map<String, String> decisionMatter) {
        TreeNode treeNode = engineDecisionMaker(treeRich, treeId, userId, decisionMatter);
        return new EngineResult(true,userId,treeId,treeNode.getTreeNodeId(),treeNode.getNodeValue());
    }

}
