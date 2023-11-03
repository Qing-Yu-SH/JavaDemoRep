package com.yq.service;

import com.yq.entity.User;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-03 16:24
 **/
public interface UserService {

    User selectById(Integer id);

    Integer insert(User user);

    Integer deleteById(Integer id);

    User updateAge(Integer id,Integer age);

}
