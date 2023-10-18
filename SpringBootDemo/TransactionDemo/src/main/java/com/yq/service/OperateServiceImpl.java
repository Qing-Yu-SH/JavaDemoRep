package com.yq.service;

import com.yq.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-18 13:59
 **/
@Service
public class OperateServiceImpl {

    @Autowired
    private UserServiceImpl userService;

    public void insertUserWithNoPublic(User user){
        userService.insertUser(user);
    }

}
