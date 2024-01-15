package com.yq.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-01-12 09:18
 **/
@RestController
public class IndexController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }

}
