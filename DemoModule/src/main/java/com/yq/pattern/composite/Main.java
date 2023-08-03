package com.yq.pattern.composite;

import com.alibaba.fastjson.JSON;
import com.yq.pattern.composite.model.aggregates.TreeRich;
import com.yq.pattern.composite.model.vo.EngineResult;
import com.yq.pattern.composite.model.vo.TreeNode;
import com.yq.pattern.composite.model.vo.TreeNodeLine;
import com.yq.pattern.composite.model.vo.TreeRoot;
import com.yq.pattern.composite.service.engine.TreeEngineHandle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-03 12:50
 **/

/**
 * 组合模式
 * 这种设计⽅式可以让你的服务组节点进⾏⾃由组合对外提供服务。
 * 这部分设计模式保证了开闭原则，⽆需更改模型结构你就可以提供新的逻辑节点的使⽤并配合组织出新的关系树。
 * 但如果是⼀些功能差异化⾮常⼤的接⼝进⾏包装就会变得⽐较困难，但也不是不能很好的处理，只不过需要做⼀些适配和特定化的开发。
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        TreeEngineHandle treeEngineHandle = new TreeEngineHandle();
        TreeRich treeRich = init();

        Map<String, String> decisionMatter = new HashMap<String, String>(){{
            put("age","16");
            put("gender","男");
        }};
        EngineResult engineResult = treeEngineHandle.process(200L, "1001", treeRich, decisionMatter);
        logger.info(JSON.toJSON(engineResult).toString());

        Map<String, String> decisionMatter2 = new HashMap<String, String>(){{
            put("age","52");
            put("gender","男");
        }};
        EngineResult engineResult2 = treeEngineHandle.process(200L, "1002", treeRich, decisionMatter2);
        logger.info(JSON.toJSON(engineResult2).toString());

        Map<String, String> decisionMatter3 = new HashMap<String, String>(){{
            put("age","16");
            put("gender","女");
        }};
        EngineResult engineResult3 = treeEngineHandle.process(200L, "1002", treeRich, decisionMatter3);
        logger.info(JSON.toJSON(engineResult3).toString());
    }

    private static TreeRich init(){
        // 节点 1
        TreeNode node_1 = new TreeNode();
        node_1.setTreeId(200L);
        node_1.setTreeNodeId(1L);
        node_1.setRuleKey("userGender");
        node_1.setRuleDesc("⽤户性别[男/⼥]");
        node_1.setNodeType(1);
        node_1.setNodeValue(null);
        // 链路 1 -> 11
        TreeNodeLine line_11 = new TreeNodeLine();
        line_11.setNodeIdFrom(1L);
        line_11.setNodeIdTo(11L);
        line_11.setRuleLimitType(1);
        line_11.setRuleLimitValue("男");
        // 链路 1 -> 12
        TreeNodeLine line_12 = new TreeNodeLine();
        line_12.setNodeIdFrom(1L);
        line_12.setNodeIdTo(12L);
        line_12.setRuleLimitType(1);
        line_12.setRuleLimitValue("女");
        // 节点 1 边列表
        List<TreeNodeLine> list_1 = new ArrayList<>();
        list_1.add(line_11);
        list_1.add(line_12);
        node_1.setTreeNodeLineInfoList(list_1);

        // 节点 11
        TreeNode node_11 = new TreeNode();
        node_11.setTreeId(200L);
        node_11.setTreeNodeId(11L);
        node_11.setRuleKey("userAge");
        node_11.setRuleDesc("⽤户年龄");
        node_11.setNodeType(1);
        node_11.setNodeValue(null);
        // 链路 11 -> 111
        TreeNodeLine line_111 = new TreeNodeLine();
        line_111.setNodeIdFrom(11L);
        line_111.setNodeIdTo(111L);
        line_111.setRuleLimitType(3);
        line_111.setRuleLimitValue("18");
        // 链路 11 -> 112
        TreeNodeLine line_112 = new TreeNodeLine();
        line_112.setNodeIdFrom(11L);
        line_112.setNodeIdTo(112L);
        line_112.setRuleLimitType(5);
        line_112.setRuleLimitValue("35");
        // 链路 11 -> 113
        TreeNodeLine line_113 = new TreeNodeLine();
        line_113.setNodeIdFrom(11L);
        line_113.setNodeIdTo(113L);
        line_113.setRuleLimitType(5);
        line_113.setRuleLimitValue("60");
        // 链路 11 -> 114
        TreeNodeLine line_114 = new TreeNodeLine();
        line_114.setNodeIdFrom(11L);
        line_114.setNodeIdTo(114L);
        line_114.setRuleLimitType(2);
        line_114.setRuleLimitValue("60");
        // 节点 11 边列表
        List<TreeNodeLine> list_11 = new ArrayList<>();
        list_11.add(line_111);
        list_11.add(line_112);
        list_11.add(line_113);
        list_11.add(line_114);
        node_11.setTreeNodeLineInfoList(list_11);

        // 节点 12
        TreeNode node_12 = new TreeNode();
        node_12.setTreeId(200L);
        node_12.setTreeNodeId(12L);
        node_12.setRuleKey("userAge");
        node_12.setRuleDesc("⽤户年龄");
        node_12.setNodeType(1);
        node_12.setNodeValue(null);
        // 链路 12 -> 121
        TreeNodeLine line_121 = new TreeNodeLine();
        line_121.setNodeIdFrom(12L);
        line_121.setNodeIdTo(121L);
        line_121.setRuleLimitType(3);
        line_121.setRuleLimitValue("18");
        // 链路 12 -> 122
        TreeNodeLine line_122 = new TreeNodeLine();
        line_122.setNodeIdFrom(12L);
        line_122.setNodeIdTo(122L);
        line_122.setRuleLimitType(5);
        line_122.setRuleLimitValue("35");
        // 链路 12 -> 123
        TreeNodeLine line_123 = new TreeNodeLine();
        line_123.setNodeIdFrom(12L);
        line_123.setNodeIdTo(123L);
        line_123.setRuleLimitType(5);
        line_123.setRuleLimitValue("60");
        // 链路 12 -> 124
        TreeNodeLine line_124 = new TreeNodeLine();
        line_124.setNodeIdFrom(12L);
        line_124.setNodeIdTo(124L);
        line_124.setRuleLimitType(2);
        line_124.setRuleLimitValue("60");
        // 节点 12 边列表
        List<TreeNodeLine> list_12 = new ArrayList<>();
        list_12.add(line_121);
        list_12.add(line_122);
        list_12.add(line_123);
        list_12.add(line_124);
        node_12.setTreeNodeLineInfoList(list_12);

        // 节点 111
        TreeNode node_111 = new TreeNode();
        node_111.setTreeId(200L);
        node_111.setTreeNodeId(111L);
        node_111.setNodeType(2);
        node_111.setNodeValue("未成年男子");
        // 节点 112
        TreeNode node_112 = new TreeNode();
        node_112.setTreeId(200L);
        node_112.setTreeNodeId(112L);
        node_112.setNodeType(2);
        node_112.setNodeValue("青年男子");
        // 节点 113
        TreeNode node_113 = new TreeNode();
        node_113.setTreeId(200L);
        node_113.setTreeNodeId(113L);
        node_113.setNodeType(2);
        node_113.setNodeValue("中年男子");
        // 节点 114
        TreeNode node_114 = new TreeNode();
        node_114.setTreeId(200L);
        node_114.setTreeNodeId(114L);
        node_114.setNodeType(2);
        node_114.setNodeValue("老年男子");

        // 节点 121
        TreeNode node_121 = new TreeNode();
        node_121.setTreeId(200L);
        node_121.setTreeNodeId(121L);
        node_121.setNodeType(2);
        node_121.setNodeValue("未成年女子");
        // 节点 122
        TreeNode node_122 = new TreeNode();
        node_122.setTreeId(200L);
        node_122.setTreeNodeId(122L);
        node_122.setNodeType(2);
        node_122.setNodeValue("青年女子");
        // 节点 123
        TreeNode node_123 = new TreeNode();
        node_123.setTreeId(200L);
        node_123.setTreeNodeId(123L);
        node_123.setNodeType(2);
        node_123.setNodeValue("中年女子");
        // 节点 124
        TreeNode node_124 = new TreeNode();
        node_124.setTreeId(200L);
        node_124.setTreeNodeId(124L);
        node_124.setNodeType(2);
        node_124.setNodeValue("老年女子");

        TreeRoot treeRoot = new TreeRoot();
        treeRoot.setTreeId(200L);
        treeRoot.setTreeName("人群划分决策");
        treeRoot.setTreeRootNodeId(1L);

        Map<Long, TreeNode> treeNodeMap = new HashMap<>();
        treeNodeMap.put(1L, node_1);
        treeNodeMap.put(11L, node_11);
        treeNodeMap.put(111L, node_111);
        treeNodeMap.put(112L, node_112);
        treeNodeMap.put(113L, node_113);
        treeNodeMap.put(114L, node_114);
        treeNodeMap.put(12L, node_12);
        treeNodeMap.put(121L, node_121);
        treeNodeMap.put(122L, node_122);
        treeNodeMap.put(123L, node_123);
        treeNodeMap.put(124L, node_124);

        TreeRich treeRich = new TreeRich();
        treeRich.setTreeRoot(treeRoot);
        treeRich.setTreeNodeMap(treeNodeMap);

        return treeRich;
    }
}
