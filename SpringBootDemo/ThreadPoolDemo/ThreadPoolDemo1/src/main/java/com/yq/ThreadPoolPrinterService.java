package com.yq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-24 19:36
 **/
@Service
public class ThreadPoolPrinterService {

    @Autowired
    private ApplicationContext applicationContext;

    public void printThreadPools() {
        String[] beanNames = applicationContext.getBeanNamesForType(ThreadPoolTaskExecutor.class);

        for (String beanName : beanNames) {
            ThreadPoolTaskExecutor threadPool = applicationContext.getBean(beanName, ThreadPoolTaskExecutor.class);
            System.out.println("Thread pool bean name: " + beanName);
            System.out.println("Core pool size: " + threadPool.getCorePoolSize());
            System.out.println("Max pool size: " + threadPool.getMaxPoolSize());
            System.out.println("Queue capacity: " + threadPool.getQueueCapacity());
            System.out.println("Thread name prefix: " + threadPool.getThreadNamePrefix());
        }
    }

}
