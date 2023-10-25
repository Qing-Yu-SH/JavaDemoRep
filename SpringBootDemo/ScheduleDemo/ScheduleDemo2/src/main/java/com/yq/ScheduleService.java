package com.yq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-25 16:33
 **/
@Slf4j
@Component
public class ScheduleService {

    @Async
    @Scheduled(cron = "0/2 * * * * ?")
    public void scheduleTask01() throws InterruptedException {
        Thread.sleep(5000);
        log.info("当前任务01 - 当前执行线程：{}，ID：{}",Thread.currentThread().getName(),Thread.currentThread().getId());
    }

//    @Async(value = "asyncTaskExecutor")
    @Async
    @Scheduled(cron = "0/2 * * * * ?")
    public void scheduleTask02() throws InterruptedException {
        Thread.sleep(2000);
        log.info("当前任务02 - 当前执行线程：{}，ID：{}",Thread.currentThread().getName(),Thread.currentThread().getId());
    }

}
