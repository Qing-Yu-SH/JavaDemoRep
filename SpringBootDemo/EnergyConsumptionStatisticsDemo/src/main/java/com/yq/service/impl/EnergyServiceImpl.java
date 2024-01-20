package com.yq.service.impl;

import com.yq.dao.EnergyDao;
import com.yq.entity.Energy;
import com.yq.service.EnergyService;
import com.yq.vo.ReqVo;
import com.yq.vo.ResVo;
import com.yq.vo.constants.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-01-19 21:04
 **/
@Service
public class EnergyServiceImpl implements EnergyService {

    @Autowired
    private EnergyDao energyDao;

    @Override
    public ResVo<List<Energy>> getAllConsumption() {
        List<Energy> energyList = energyDao.selectAll();
        return new ResVo<>(energyList);
    }

    @Override
    public ResVo addConsumption(ReqVo req) {
        // 采集失败，则返回错误
        if(req==null || req.getValue()==null || req.getValue()==0) return ResVo.fail(StatusEnum.UNEXPECT_ERROR, "采集数据失败");
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Energy energy = energyDao.selectByDate(sdf.format(date));
        Energy newEnergy = Energy.getInstance(req);
        if(energy == null){
            // 当天数据未采集
            Date previousDate = getPreviousDate(date);
            Energy energyPrevious = energyDao.selectByDate(sdf.format(previousDate));

            if(energyPrevious!=null && energyPrevious.getMeterId().equals(req.getMeterId())){
                // 情况1：此时说明数据库中已有记录，当前记录还没有，需要根据前天记录插入
                // 计算当天的实际消耗量
                Double value = req.getValue() - energyPrevious.getCumulativeValue();
                newEnergy.setEnergyValue(value);
            }

            // 情况2：数据库中还未有记录，新增；情况3：数据库中存在前天记录，但电表更换，value 值为实际值，插入
            energyDao.insert(newEnergy);
            return ResVo.ok("插入成功");
        }else{
            // 当前请求记录的统计时间早于数据库中记录的时间，说明是无效记录
            if(newEnergy.getUpdateTime().before(energy.getUpdateTime())){
                return ResVo.fail(StatusEnum.UNEXPECT_ERROR, "当前请求记录的统计时间早于数据库中记录的时间");
            }
            newEnergy.setId(energy.getId());
            // 当天数据已采集
            if(energy.getMeterId().equals(req.getMeterId())){
                // 电表未更换
                // 计算此次新增消耗量：累计值相减
                Double value = req.getValue() - energy.getCumulativeValue();
                newEnergy.setEnergyValue(value + energy.getEnergyValue());
            }else{
                // 电表更换
                // 此次统计是当天的新增值
                newEnergy.setEnergyValue(req.getValue() + energy.getEnergyValue());
            }
            // 更新当天值
            energyDao.update(newEnergy);
            return ResVo.ok("更新成功");
        }
    }

    private Date getPreviousDate(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        return calendar.getTime();
    }

}
