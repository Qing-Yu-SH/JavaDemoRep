package com.yq.pattern.composite.service.logic;

import com.yq.pattern.composite.model.vo.TreeNodeLine;

import java.util.List;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description: 树节点逻辑过滤器接⼝
 * @author: Yuqing
 * @create: 2023-08-03 11:29
 **/
public interface LogicFilter {

    /**
     * 逻辑决策器
     * @param matterValue 决策值
     * @param treeNodeLineInfoList 决策节点
     * @return 下一个节点 Id
     */
    Long filter(String matterValue, List<TreeNodeLine> treeNodeLineInfoList);

    /**
     * 获取决策值
     * @param decisionMatter 决策物料
     * @return 决策值
     */
    String matterValue(Map<String,String> decisionMatter);

}
