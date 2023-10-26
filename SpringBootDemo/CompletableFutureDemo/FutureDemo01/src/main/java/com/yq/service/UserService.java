package com.yq.service;

import com.yq.entity.User;
import lombok.extern.slf4j.Slf4j;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-26 16:26
 **/
public class UserService {

    public User getUserInfo() {
        // 模拟查询数据库
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            return null;
        }
        User user = new User("sumAll",26,"男","6589231409840","shanghai");
        return user;
    }

}
