package com.yq.service;

import com.yq.dao.UserInfoDao;
import com.yq.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: sumAll
 * @create: 2024-04-30 15:28
 **/
@Service
public class UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    private static Random random = new Random(6L);

    public UserInfo selectById(Integer id){
        return userInfoDao.selectUserInfoById(id);
    }


    public void add(){
        UserInfo userInfo = new UserInfo();
        userInfo.setName("sumAll_" + random.nextInt(100));
        userInfo.setAge(random.nextInt(100));
        userInfo.setProfile("programmer");
        userInfo.setPhone("123456789");
        userInfoDao.addUserInfo(userInfo);
    }

    public void update(Integer id){
        UserInfo userInfo = new UserInfo();
        userInfo.setId(id);
        userInfo.setName("sumAll_" + id);
        userInfo.setAge(random.nextInt(100));
    }

    // 正常情况
    @Transactional(rollbackFor = Exception.class)
    public void testSuccess() throws Exception {
        Integer id = 1;
        UserInfo userInfo = selectById(id);
        System.out.println("原记录:" + userInfo);
        update(id);
        throw new Exception("事务生效");
    }



}
