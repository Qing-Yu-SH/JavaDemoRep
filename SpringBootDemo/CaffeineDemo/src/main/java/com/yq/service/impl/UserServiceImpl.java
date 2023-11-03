package com.yq.service.impl;

import com.yq.dao.UserDao;
import com.yq.entity.User;
import com.yq.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-03 16:24
 **/
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Cacheable(cacheNames = "userCache",key = "'user_' + #id",cacheManager = "caffeineCacheManager")
    @Override
    public User selectById(Integer id) {
        return userDao.selectUserById(id);
    }

    // #result 代表返回值
    @CachePut(cacheNames = "userCache",key = "'user_' + #result",cacheManager = "caffeineCacheManager")
    @Override
    public Integer insert(User user) {
        userDao.insertUser(user);
        return user.getId();
    }

    @CacheEvict(cacheNames = "userCache",key = "'user_' + #id",cacheManager = "caffeineCacheManager")
    @Override
    public Integer deleteById(Integer id) {
        return userDao.deleteById(id);
    }


    @Override
    public User updateAge(Integer id, Integer age) {
        Integer count = userDao.updateAgeById(id, age);
        log.info("更新条数：{}", count);
        return selectById(id);
    }

}
