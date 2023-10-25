package com.yq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-24 19:14
 **/
@Slf4j
@Component
public class AsyncTask {

    @Async
    public void task() throws InterruptedException {
        Thread.sleep(1000);
        log.info("当前线程：{} - {}", Thread.currentThread().getName(), Thread.currentThread().getId());
    }

}


