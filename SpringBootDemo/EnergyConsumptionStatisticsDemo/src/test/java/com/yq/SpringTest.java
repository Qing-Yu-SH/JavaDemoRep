package com.yq;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.yq.dao.EnergyDao;
import com.yq.entity.Energy;
import com.yq.service.EnergyService;
import com.yq.vo.ReqVo;
import com.yq.vo.ResVo;
import com.yq.vo.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-01-19 21:35
 **/
@SpringBootTest
public class SpringTest {

    @Autowired
    private EnergyService energyService;

    @Autowired
    private EnergyDao energyDao;

    @Test
    public void test_selectAll() throws JsonProcessingException {
        ResVo<List<Energy>> result = energyService.getAllConsumption();
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(result);
        System.out.println(str);
    }

    @Test
    public void test_json() throws JsonProcessingException {
        ResVo<Object> resVo = new ResVo<>(Status.newStatus(200,"OK"));
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(resVo);
        System.out.println(str);
    }

    @Test
    public void test_selectByDate() throws JsonProcessingException {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Energy energy = energyDao.selectByDate(sdf.format(date));
        ObjectMapper objectMapper = new ObjectMapper();
        String str = objectMapper.writeValueAsString(energy);
        System.out.println(str);
    }

    @Test
    public void test_date(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date previousDay = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf.format(previousDay));
    }

    @Test
    public void test_insert(){
        ReqVo reqVo = new ReqVo();
        reqVo.setMeterId("A01");
        reqVo.setTimestamp(new Timestamp(new Date().getTime()));
        reqVo.setValue(100.0);
        Energy energy = Energy.getInstance(reqVo);
        energyDao.insert(energy);
    }

    @Test
    public void test_update(){
        Energy energy = energyDao.selectByDate("2024-01-20");
        energy.setEnergyValue(120D);
        energyDao.update(energy);
    }

    @Test
    public void test_timestamp(){
        Timestamp timestamp = new Timestamp(new Date().getTime());
        System.out.println(timestamp.getTime());
    }



}
