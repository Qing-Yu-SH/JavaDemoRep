package com.yq.dao;

import com.yq.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-01 14:59
 **/
@Mapper
public interface UserDao {

    Integer insertUser(User user);

    User selectUserById(Integer id);

    Integer deleteById(Integer id);

    Integer updateAgeById(Integer id,Integer age);


}
