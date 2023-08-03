package com.yq.pattern.composite.model.vo;

/**
 * @program: JavaDemoRep
 * @description: 规则树根配置
 * @author: Yuqing
 * @create: 2023-08-03 11:21
 **/
public class TreeRoot {

    /** 规则树ID */
    private Long treeId;
    /** 规则树根ID */
    private Long treeRootNodeId;
    /** 规则树名称 */
    private String treeName;

    public TreeRoot() {
    }

    public TreeRoot(Long treeId, Long treeRootNodeId, String treeName) {
        this.treeId = treeId;
        this.treeRootNodeId = treeRootNodeId;
        this.treeName = treeName;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getTreeRootNodeId() {
        return treeRootNodeId;
    }

    public void setTreeRootNodeId(Long treeRootNodeId) {
        this.treeRootNodeId = treeRootNodeId;
    }

    public String getTreeName() {
        return treeName;
    }

    public void setTreeName(String treeName) {
        this.treeName = treeName;
    }

    @Override
    public String toString() {
        return "TreeRoot{" +
                "treeId=" + treeId +
                ", treeRootNodeId=" + treeRootNodeId +
                ", treeName='" + treeName + '\'' +
                '}';
    }
}
