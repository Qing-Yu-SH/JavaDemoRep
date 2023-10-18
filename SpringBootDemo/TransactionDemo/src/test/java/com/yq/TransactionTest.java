package com.yq;


import com.yq.repository.dao.UserDao;
import com.yq.repository.entity.User;
import com.yq.service.OperateServiceImpl;
import com.yq.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-18 13:30
 **/
@Slf4j
@SpringBootTest
public class TransactionTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private OperateServiceImpl operateService;

    @Test
    public void test_insertUser(){
        User user = new User();
        user.setName("sumAll");
        user.setAge(26);
        user.setSex("male");
        user.setHeight(180);
        userDao.save(user);
    }

    @Test
    public void test_selectById(){
        User user = userDao.selectById(1);
        if(user != null){
            log.info(user.toString());
        }
    }

    @Test
    public void test_selectByName(){
        List<User> users = userService.getUserByName("noPublic");
        printUserList(users);
    }

    /* 事务失效场景一：注解定义在非 public 修饰的方法上 */
    @Test
    public void test_noPublicInsert(){
        String name = "noPublic";
        try {
            User user = new User(name, 22, "male", 180);
            operateService.insertUserWithNoPublic(user);
        } catch (RuntimeException e) {
            List<User> users = userService.getUserByName(name);
            printUserList(users);
            log.error("打印异常信息..");
        }
    }

    @Test
    public void test_withPublicInsert(){
        // 这里可以发现正常回滚了
        String name = "withPublic";
        User user = new User(name, 22, "male", 180);
        try {
            userService.insertUserWithPublic(user);
        } catch (Exception e) {
            List<User> users = userService.getUserByName(name);
            printUserList(users);
            log.error("打印异常信息..");
        }
    }

    /* 事务失效场景一：结束 */

    /* 事务失效场景二：注解属性 propagation 配置错误 */
    @Test
    public void test_insertUserWithWrongPropagation(){
        // 如果事务的传播机制设置为：PROPAFATION_SUPPORTS 可能导致事务回滚失效
        // 如果事务的传播机制设置为：PROPAFATION_NOT_SUPPORTED、PROPAFATION_NEVER 会以非事务的方式运行
        // 默认是 Propagation.REQUIRED
        String name = "withWrongPropagation";
        User user = new User(name, 22, "male", 180);
        try {
            userService.insertUserWithWrongPropagation(user);
        } catch (Exception e) {
            List<User> users = userService.getUserByName(name);
            printUserList(users);
            log.error("打印运行时异常..");
        }
    }

    /* 事务失效场景二：结束 */


    /* 事务失效场景三：注解属性 rollbackFor 配置错误或者返回非 RuntimeException 和 Error 异常*/
    @Test
    public void insertUserThrowFileNotFoundException(){
        // @Transactional 默认捕捉 RuntimeException 和 Error
        // 在方法中抛出了 FileNotFoundException，事务无法捕捉，导致事务失效
        String name = "withThrowFileNotFoundException";
        User user = new User(name, 22, "male", 180);
        try {
            userService.insertUserThrowFileNotFoundException(user);
        } catch (Exception e) {
            List<User> users = userService.getUserByName(name);
            printUserList(users);
            log.error("打印运行时异常..");
        }
    }

    @Test
    public void test_insertUserThrowRuntimeException(){
        // 此处抛出了 RuntimeException，发现事务能够正常回滚
        String name = "withThrowRuntimeException";
        User user = new User(name, 22, "male", 180);
        try {
            userService.insertUserThrowRuntimeException(user);
        } catch (Exception e) {
            List<User> users = userService.getUserByName(name);
            printUserList(users);
            log.error("打印运行时异常..");
        }
    }

    @Test
    public void test_insertUserFixByRollbackFor(){
        // 可以通过 @Transactional 的 rollbackFor 属性指定要捕获的异常
        String name = "FixByRollbackFor";
        User user = new User(name, 22, "male", 180);
        try {
            userService.insertUserFixByRollbackFor(user);
        } catch (Exception e) {
            List<User> users = userService.getUserByName(name);
            printUserList(users);
            log.error("打印运行时异常..");
        }
    }

    /* 事务失效场景三：结束*/


    /* 事务失效场景四：同一个类中方法调用*/
    @Test
    public void test_insertA(){
        String name = "insertA";
        User user = new User(name, 22, "male", 180);
        try {
            userService.insertA(user);
        } catch (Exception e) {
            List<User> users = userService.getUserByName(name);
            printUserList(users);
            log.error("打印运行时异常..");
        }
    }

    /* 事务失效场景四：结束*/


    private void printUserList(List<User> users){
        if(users!=null && !users.isEmpty()){
            for (User u: users){
                log.info(u.toString());
            }
        }
    }

}
