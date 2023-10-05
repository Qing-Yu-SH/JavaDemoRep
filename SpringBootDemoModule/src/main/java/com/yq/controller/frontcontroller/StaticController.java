package com.yq.controller.frontcontroller;

import com.yq.service.UserStatisticService;
import com.yq.util.SpringUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 20:19
 **/
@ApiOperation(value = "业务接口", notes = "查询统计数据接口", tags = "统计")
@RestController
@RequestMapping("/queryStatic")
public class StaticController {

    @GetMapping("/userCount")
    public int getUserCount(){
        return SpringUtil.getBean(UserStatisticService.class).getOnlineUserCnt();
    }

}
