package com.yq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-25 12:00
 **/

@Slf4j
@Component
public class ScheduleService {

//    @Scheduled(cron = "0/2 * * * * ?")
    public void scheduleTask(){
        log.info("当前执行线程：{}，ID：{}",Thread.currentThread().getName(),Thread.currentThread().getId());
    }


//    @Scheduled(fixedDelay = 2000)
    public void fixedDelay() throws InterruptedException {
        log.info("start FixedDelay -- 当前执行线程：{}，ID：{}",Thread.currentThread().getName(),Thread.currentThread().getId());
        Thread.sleep(5000);
        log.info("end  FixedDelay -- 当前执行线程：{}，ID：{}",Thread.currentThread().getName(),Thread.currentThread().getId());
    }


//    @Scheduled(fixedRate = 2000)
    public void fixedRate() throws InterruptedException {
        log.info("start -- 当前执行线程：{}，ID：{}",Thread.currentThread().getName(),Thread.currentThread().getId());
        Thread.sleep(5000);
        log.info("end FixedRate -- 当前执行线程：{}，ID：{}",Thread.currentThread().getName(),Thread.currentThread().getId());
    }

//    @Scheduled(initialDelay = 6000, fixedDelay = 2000)
    public void initialDelay() throws InterruptedException {
        log.info("start -- 当前执行线程：{}，ID：{}",Thread.currentThread().getName(),Thread.currentThread().getId());
        Thread.sleep(5000);
        log.info("end InitialDelay -- 当前执行线程：{}，ID：{}",Thread.currentThread().getName(),Thread.currentThread().getId());
    }


    @Scheduled(cron = "0/2 * * * * ?")
    public void scheduleTask01() throws InterruptedException {
        Thread.sleep(5000);
        log.info("当前任务01 - 当前执行线程：{}，ID：{}",Thread.currentThread().getName(),Thread.currentThread().getId());
    }

    @Scheduled(cron = "0/2 * * * * ?")
    public void scheduleTask02() throws InterruptedException {
        Thread.sleep(2000);
        log.info("当前任务02 - 当前执行线程：{}，ID：{}",Thread.currentThread().getName(),Thread.currentThread().getId());
    }

}



