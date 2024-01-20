package com.yq.service;

import com.yq.entity.Energy;
import com.yq.vo.ReqVo;
import com.yq.vo.ResVo;

import java.util.List;


/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-01-19 21:04
 **/
public interface EnergyService {

    public ResVo<List<Energy>> getAllConsumption();

    public ResVo addConsumption(ReqVo reqVo);

}
