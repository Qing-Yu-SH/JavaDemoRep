package com.yq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-24 19:18
 **/
@RestController
public class TestController {

    @Autowired
    private AsyncService asyncService;

    @Autowired
    private ThreadPoolPrinterService threadPoolPrinterService;

    @GetMapping("/test")
    public void test(){
        asyncService.asyncTask();
    }

    @GetMapping("/printThreadPool")
    public void printThreadPool(){
        threadPoolPrinterService.printThreadPools();
    }

}
