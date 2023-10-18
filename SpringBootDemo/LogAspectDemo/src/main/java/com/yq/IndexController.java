package com.yq;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-18 10:21
 **/
@RestController
public class IndexController {

    @GetMapping("/hello")
    @WebLog
    public String hello(String name) throws InterruptedException {
        Thread.sleep(100);
        return "Hello," + name;
    }

    @GetMapping("/select")
    @WebLog(desc = "查询用户名接口")
    public String select(String userId){
        return "用户名: sumAll";
    }


}
