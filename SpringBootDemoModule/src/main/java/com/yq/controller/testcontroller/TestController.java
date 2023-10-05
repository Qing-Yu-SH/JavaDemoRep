package com.yq.controller.testcontroller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 13:03
 **/
@ApiOperation(value = "测试接口", notes = "测试类型接口", tags = "测试")
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/index")
    public String index(@ApiParam(name = "name", value = "姓名", required = true, defaultValue = "", allowableValues = "Yu;A") String name){
        return "hello," + name;
    }

}
