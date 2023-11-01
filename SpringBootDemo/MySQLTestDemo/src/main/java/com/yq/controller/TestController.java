package com.yq.controller;

import com.google.common.base.Stopwatch;
import com.yq.dao.UserDao;
import com.yq.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-01 15:08
 **/
@Slf4j
@RestController
@RequestMapping("/api/mysql")
public class TestController {

    @Resource
    private UserDao userDao;

    private Random random = new Random();

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private Date startDate = dateFormat.parse("2000-01-01");
    private Date endDate = dateFormat.parse("2022-12-31");

    public TestController() throws ParseException {
    }

    @GetMapping("/insert")
    public String insert(){
        log.info("性能测试，insert");

        User user = User.builder().userName("yq_" + random.nextInt(10000))
                .password("12345678")
                .phone("18892657821")
                .age(random.nextInt(99)+1)
                .createTime(randomDate())
                .build();

        Stopwatch stopwatch = Stopwatch.createStarted();
        userDao.insert(user);
        stopwatch.stop();

        return "耗时：" + stopwatch;
    }

    private Date randomDate(){
        // 生成随机日期
        long randomMillis = startDate.getTime() + (long) (random.nextDouble() * (endDate.getTime() - startDate.getTime()));
        return new Date(randomMillis);
    }

}
