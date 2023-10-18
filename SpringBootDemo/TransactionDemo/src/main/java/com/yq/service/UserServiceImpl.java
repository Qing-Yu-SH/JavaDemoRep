package com.yq.service;

import com.yq.repository.dao.UserDao;
import com.yq.repository.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-18 13:29
 **/
@Service
public class UserServiceImpl{

    @Autowired
    private UserDao userDao;

    public List<User> getUserByName(String name){
        return userDao.lambdaQuery().select().eq(User::getName, name).list();
    }

    @Transactional
    void insertUser(User user) {
        userDao.save(user);
        throw new RuntimeException("故意的异常");
    }

    @Transactional
    public void insertUserWithPublic(User user) {
        userDao.save(user);
        throw new RuntimeException("故意的异常");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void insertUserWithWrongPropagation(User user){
        userDao.save(user);
        throw new RuntimeException("故意的异常");
    }

    @Transactional
    public void insertUserThrowFileNotFoundException(User user) throws FileNotFoundException {
        userDao.save(user);
        throw new FileNotFoundException("故意的 FileNotFoundException 异常");
    }

    @Transactional
    public void insertUserThrowRuntimeException(User user){
        userDao.save(user);
        throw new RuntimeException("故意的 RuntimeException 异常");
    }

    @Transactional(rollbackFor = {FileNotFoundException.class})
    public void insertUserFixByRollbackFor(User user) throws FileNotFoundException {
        userDao.save(user);
        throw new FileNotFoundException("故意的 FileNotFoundException 异常");
    }

    public void insertA(User user){
        insertB(user);
    }

    @Transactional
    public void insertB(User user){
        userDao.save(user);
        throw new RuntimeException("故意的异常");
    }

}
