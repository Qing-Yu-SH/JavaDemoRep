package com.yq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 13:03
 **/
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/index")
    public String index(String name){
        return "hello," + name;
    }

}
