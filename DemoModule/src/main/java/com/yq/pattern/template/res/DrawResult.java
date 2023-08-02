package com.yq.pattern.template.res;

/**
 * @program: JavaDemoRep
 * @description: 结果
 * @author: Yuqing
 * @create: 2023-08-02 16:03
 **/
public class DrawResult {

    private String uId;

    private Long strategyId;

    private Integer drawState = 0;

    private String awardName;

    public DrawResult() {
    }

    public DrawResult(String uId, Long strategyId, Integer drawState, String awardName) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.drawState = drawState;
        this.awardName = awardName;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getDrawState() {
        return drawState;
    }

    public void setDrawState(Integer drawState) {
        this.drawState = drawState;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }
}
