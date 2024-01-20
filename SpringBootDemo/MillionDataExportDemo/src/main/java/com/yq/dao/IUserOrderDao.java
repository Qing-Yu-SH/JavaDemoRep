package com.yq.dao;

import com.github.pagehelper.Page;
import com.yq.pojo.UserOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-09 19:30
 **/
@Mapper
public interface IUserOrderDao {

    Page<UserOrder> selectByPage();

    Long selectCount();

}
