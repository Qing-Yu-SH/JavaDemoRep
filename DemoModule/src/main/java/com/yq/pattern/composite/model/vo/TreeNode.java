package com.yq.pattern.composite.model.vo;

import java.util.List;

/**
 * @program: JavaDemoRep
 * @description: 树节点信息
 * @author: Yuqing
 * @create: 2023-08-03 11:19
 **/
public class TreeNode {

    /** 规则树ID */
    private Long treeId;
    /** 规则树节点ID */
    private Long treeNodeId;
    /** 节点类型；1子叶、2果实 */
    private Integer nodeType;
    /** 节点值[nodeType=2]；果实值 */
    private String nodeValue;
    /** 规则Key */
    private String ruleKey;
    /** 规则描述 */
    private String ruleDesc;
    /** 节点链路 */
    private List<TreeNodeLine> treeNodeLineInfoList;

    public TreeNode() {
    }

    public TreeNode(Long treeId, Long treeNodeId, Integer nodeType, String nodeValue, String ruleKey, String ruleDesc, List<TreeNodeLine> treeNodeLineInfoList) {
        this.treeId = treeId;
        this.treeNodeId = treeNodeId;
        this.nodeType = nodeType;
        this.nodeValue = nodeValue;
        this.ruleKey = ruleKey;
        this.ruleDesc = ruleDesc;
        this.treeNodeLineInfoList = treeNodeLineInfoList;
    }

    public Long getTreeId() {
        return treeId;
    }

    public void setTreeId(Long treeId) {
        this.treeId = treeId;
    }

    public Long getTreeNodeId() {
        return treeNodeId;
    }

    public void setTreeNodeId(Long treeNodeId) {
        this.treeNodeId = treeNodeId;
    }

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeValue() {
        return nodeValue;
    }

    public void setNodeValue(String nodeValue) {
        this.nodeValue = nodeValue;
    }

    public String getRuleKey() {
        return ruleKey;
    }

    public void setRuleKey(String ruleKey) {
        this.ruleKey = ruleKey;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc;
    }

    public List<TreeNodeLine> getTreeNodeLineInfoList() {
        return treeNodeLineInfoList;
    }

    public void setTreeNodeLineInfoList(List<TreeNodeLine> treeNodeLineInfoList) {
        this.treeNodeLineInfoList = treeNodeLineInfoList;
    }

    @Override
    public String toString() {
        return "TreeNodeVO{" +
                "treeId=" + treeId +
                ", treeNodeId=" + treeNodeId +
                ", nodeType=" + nodeType +
                ", nodeValue='" + nodeValue + '\'' +
                ", ruleKey='" + ruleKey + '\'' +
                ", ruleDesc='" + ruleDesc + '\'' +
                ", treeNodeLineInfoList=" + treeNodeLineInfoList +
                '}';
    }
}
