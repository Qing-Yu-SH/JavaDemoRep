package com.yq.pattern.composite.model.vo;

/**
 * @program: JavaDemoRep
 * @description: 节点边信息
 * @author: Yuqing
 * @create: 2023-08-03 11:17
 **/
public class TreeNodeLine {

    /** 节点From */
    private Long nodeIdFrom;
    /** 节点To */
    private Long nodeIdTo;
    /** 限定类型；1:=;2:>;3:<;4:>=;5<=;6:enum[枚举范围] */
    private Integer ruleLimitType;
    /** 限定值 */
    private String ruleLimitValue;

    public TreeNodeLine() {
    }

    public TreeNodeLine(Long nodeIdFrom, Long nodeIdTo, Integer ruleLimitType, String ruleLimitValue) {
        this.nodeIdFrom = nodeIdFrom;
        this.nodeIdTo = nodeIdTo;
        this.ruleLimitType = ruleLimitType;
        this.ruleLimitValue = ruleLimitValue;
    }

    public Long getNodeIdFrom() {
        return nodeIdFrom;
    }

    public void setNodeIdFrom(Long nodeIdFrom) {
        this.nodeIdFrom = nodeIdFrom;
    }

    public Long getNodeIdTo() {
        return nodeIdTo;
    }

    public void setNodeIdTo(Long nodeIdTo) {
        this.nodeIdTo = nodeIdTo;
    }

    public Integer getRuleLimitType() {
        return ruleLimitType;
    }

    public void setRuleLimitType(Integer ruleLimitType) {
        this.ruleLimitType = ruleLimitType;
    }

    public String getRuleLimitValue() {
        return ruleLimitValue;
    }

    public void setRuleLimitValue(String ruleLimitValue) {
        this.ruleLimitValue = ruleLimitValue;
    }

    @Override
    public String toString() {
        return "TreeNodeLineVO{" +
                "nodeIdFrom=" + nodeIdFrom +
                ", nodeIdTo=" + nodeIdTo +
                ", ruleLimitType=" + ruleLimitType +
                ", ruleLimitValue='" + ruleLimitValue + '\'' +
                '}';
    }
}
