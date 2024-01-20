package com.yq.dao;

import com.yq.entity.Energy;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-01-19 20:59
 **/
@Mapper
public interface EnergyDao {

    List<Energy> selectAll();

    Energy selectByDate(String date);

    void insert(Energy energy);

    void update(Energy energy);

}
