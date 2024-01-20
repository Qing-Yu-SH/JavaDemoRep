package com.yq.controller;

import com.yq.entity.Energy;
import com.yq.service.EnergyService;
import com.yq.vo.ReqVo;
import com.yq.vo.ResVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-01-20 10:25
 **/
@Slf4j
@RequestMapping(path = "energy")
@RestController
public class EnergyController {

    @Autowired
    private EnergyService energyService;

    @GetMapping(path = "select")
    public ResVo<List<Energy>> getAllConsumption(){
        return energyService.getAllConsumption();
    }

    @PostMapping(path = "statistics")
    public ResVo addConsumption(@RequestBody ReqVo reqVo){
        return energyService.addConsumption(reqVo);
    }

}
