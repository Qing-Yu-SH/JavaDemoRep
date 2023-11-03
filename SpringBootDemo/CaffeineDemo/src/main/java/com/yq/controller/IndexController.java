package com.yq.controller;


import com.yq.entity.User;
import com.yq.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-03 14:56
 **/
@Slf4j
@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private UserService userService;


    @GetMapping("/selectUser/{id}")
    public User selectUser(@PathVariable("id") Integer id){
        return userService.selectById(id);
    }

    @GetMapping("/insert")
    public String insert(){
        User user = User.builder()
                .userName("yq")
                .password("123456")
                .age(20)
                .phone("1357868678")
                .createTime(new Date()).build();
        Integer id = userService.insert(user);
        log.info("插入用户的 id：{}", id);
        return id!=null && id>0 ? "插入成功！":"插入失败！";
    }

    @GetMapping("/update/{id}/{age}")
    public User update(@PathVariable("id")Integer id,@PathVariable("age")Integer age){
        log.info("id：{}；age：{}",id,age);
        return userService.updateAge(id, age);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id")Integer id){
        Integer count = userService.deleteById(id);
        return count ==1 ? "删除成功！":"删除失败！";
    }

}
