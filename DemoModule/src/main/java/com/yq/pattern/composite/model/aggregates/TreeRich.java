package com.yq.pattern.composite.model.aggregates;

import com.yq.pattern.composite.model.vo.TreeNode;
import com.yq.pattern.composite.model.vo.TreeRoot;

import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: 聚合对象，包含组织树信息
 * @author: Yuqing
 * @create: 2023-08-03 11:26
 **/
public class TreeRich {

    /** 树根信息 */
    private TreeRoot treeRoot;

    /** 树节点ID -> 子节点 */
    private Map<Long, TreeNode> treeNodeMap;

    public TreeRoot getTreeRoot() {
        return treeRoot;
    }

    public void setTreeRoot(TreeRoot treeRoot) {
        this.treeRoot = treeRoot;
    }

    public Map<Long, TreeNode> getTreeNodeMap() {
        return treeNodeMap;
    }

    public void setTreeNodeMap(Map<Long, TreeNode> treeNodeMap) {
        this.treeNodeMap = treeNodeMap;
    }
}
