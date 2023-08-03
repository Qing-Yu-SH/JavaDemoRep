package com.yq.pattern.composite.service.engine;

import com.yq.pattern.composite.model.aggregates.TreeRich;
import com.yq.pattern.composite.model.vo.TreeNode;
import com.yq.pattern.composite.model.vo.TreeRoot;
import com.yq.pattern.composite.service.logic.LogicFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: 基础决策引擎功能
 * @author: Yuqing
 * @create: 2023-08-03 11:46
 **/
public abstract class EngineBase extends EngineConfig implements IEngine {

    private Logger logger = LoggerFactory.getLogger(EngineBase.class);

    protected TreeNode engineDecisionMaker(TreeRich treeRich, Long treeId, String userId, Map<String,String> decisionMatter){
        TreeRoot treeRoot = treeRich.getTreeRoot();
        Map<Long, TreeNode> treeNodeMap = treeRich.getTreeNodeMap();
        // 规则树根ID
        Long rootTreeId = treeRoot.getTreeRootNodeId();
        TreeNode treeNode = treeNodeMap.get(rootTreeId);
        while (treeNode.getNodeType().equals(1)){
            String ruleKey = treeNode.getRuleKey();
            LogicFilter logicFilter = logicFilterMap.get(ruleKey);
            String matterValue = logicFilter.matterValue(decisionMatter);
            Long nextNode = logicFilter.filter(matterValue, treeNode.getTreeNodeLineInfoList());
            treeNode = treeNodeMap.get(nextNode);
            logger.info("决策树引擎=>{} userId：{} treeId：{} treeNode：{} ruleKey：{} matterValue：{}", treeRoot.getTreeName(), userId, treeId, treeNode.getTreeNodeId(), ruleKey, matterValue);
        }
        return treeNode;
    }

}
