package com.yq.repository.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yq.repository.entity.User;
import com.yq.repository.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @program: JavaDemoRep
 * @description:
 *                  @Repository  标识这个类是一个数据访问层（DAO） 组件
 *                  ServiceImpl<UserMapper, User>  Mybatis-Plus 提供的一个抽象类，提供了通用的 CRUD 方法
 *                                                 <UserMapper, User> 泛型参数意味着 UserDao 类主要用于处理 User 数据对象的数据库操作，并使用 UserMapper 接口定义的方法进行操作
 * @author: Yuqing
 * @create: 2023-10-18 13:17
 **/
@Repository      // 标识这个类是一个数据访问层（DAO） 组件
public class UserDao extends ServiceImpl<UserMapper, User> {

    @Autowired
    private UserMapper userMapper;

    public User selectById(Integer id){
        return userMapper.selectById(id);
    }

}
