package com.yq.dao;

import com.yq.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: sumAll
 * @create: 2024-04-30 15:15
 **/
@Repository("userInfoDao")
@Mapper
public interface UserInfoDao {

    public UserInfo selectUserInfoById(Integer id);
    public List<UserInfo> selectAllUserInfo();
    public int addUserInfo(UserInfo userInfo);
    public int updateUserInfo(UserInfo userInfo);
    public int deleteUserInfo(UserInfo userInfo);

}
