package com.yq.pattern.template.strategy;

import java.math.BigDecimal;

/**
 * @program: JavaDemoRep
 * @description: 奖品
 * @author: Yuqing
 * @create: 2023-08-02 16:09
 **/
public class Award {

    // 奖品 Id
    private Integer awardId;
    // 奖品名称
    private String awardName;
    // 奖品库存
    private Integer stock;
    // 中奖概率
    private BigDecimal awardRate;

    public Award() {
    }

    public Award(Integer awardId, String awardName, Integer stock, BigDecimal awardRate) {
        this.awardId = awardId;
        this.awardName = awardName;
        this.stock = stock;
        this.awardRate = awardRate;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public BigDecimal getAwardRate() {
        return awardRate;
    }

    public void setAwardRate(BigDecimal awardRate) {
        this.awardRate = awardRate;
    }
}
