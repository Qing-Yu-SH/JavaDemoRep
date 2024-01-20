package com.yq.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.yq.vo.ReqVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import java.sql.Timestamp;
import java.util.Date;


/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-01-19 20:56
 **/
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Energy implements Serializable {

    private Long id;

    private String meterId;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

    private Double energyValue;

    private Double cumulativeValue;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public static Energy getInstance(ReqVo reqVo){
        Energy energy = new Energy();
        energy.setDate(new Date());
        energy.setCumulativeValue(reqVo.getValue());
        energy.setEnergyValue(reqVo.getValue());
        energy.setUpdateTime(reqVo.getTimestamp());
        energy.setMeterId(reqVo.getMeterId());
        return energy;
    }


}
