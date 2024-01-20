package com.yq.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.yq.dao.IUserOrderDao;
import com.yq.pojo.UserOrder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-09 21:59
 **/
@Service
public class UserOrderService {

    @Autowired
    private IUserOrderDao userOrderDao;

    public Page<UserOrder> getUserOrderList(Integer page, Integer limit){
        PageHelper.startPage(page,limit);
        return userOrderDao.selectByPage();
    }

}
