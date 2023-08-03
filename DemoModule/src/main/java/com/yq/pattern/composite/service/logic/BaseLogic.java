package com.yq.pattern.composite.service.logic;

import com.yq.pattern.composite.model.vo.TreeNodeLine;

import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-03 11:33
 **/
public abstract class BaseLogic implements LogicFilter{

    @Override
    public Long filter(String matterValue, List<TreeNodeLine> treeNodeLineInfoList) {
        for (TreeNodeLine treeNodeLine: treeNodeLineInfoList){
            if(decisionLogic(matterValue, treeNodeLine)){
                return treeNodeLine.getNodeIdTo();
            }
        }
        return 0L;
    }

    private boolean decisionLogic(String matterValue, TreeNodeLine treeNodeLine){
        switch (treeNodeLine.getRuleLimitType()){
            case 1: // 相等
                return matterValue.equals(treeNodeLine.getRuleLimitValue());
            case 2: // 大于
                return Double.parseDouble(matterValue) > Double.parseDouble(treeNodeLine.getRuleLimitValue());
            case 3: // 小于
                return Double.parseDouble(matterValue) < Double.parseDouble(treeNodeLine.getRuleLimitValue());
            case 4: // 大于等于
                return Double.parseDouble(matterValue) >= Double.parseDouble(treeNodeLine.getRuleLimitValue());
            case 5: // 小于等于
                return Double.parseDouble(matterValue) <= Double.parseDouble(treeNodeLine.getRuleLimitValue());
            default:
                return false;
        }
    }
}
