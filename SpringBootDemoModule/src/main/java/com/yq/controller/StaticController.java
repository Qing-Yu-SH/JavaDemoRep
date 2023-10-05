package com.yq.controller;

import com.yq.service.UserStatisticService;
import com.yq.util.SpringUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 20:19
 **/
@RestController
@RequestMapping("/queryStatic")
public class StaticController {

    @GetMapping("/userCount")
    public int getUserCount(){
        return SpringUtil.getBean(UserStatisticService.class).getOnlineUserCnt();
    }

}
