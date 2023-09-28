package com.yq.thread;


import java.util.concurrent.*;

/**
 * @program: JavaDemoRep
 * @description: 线程池状态
 * @author: Yuqing
 * @create: 2023-09-28 15:56
 **/
public class ThreadPoolStatus {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

        // 1.RUNNING：表示线程池正常运行，既能接受新任务，也会正常处理队列中的任务

        // 2.SHUTDOWN：调用线程池的 shutdown() 方法，线程池就进入了 SHUTDOWN 状态，表示线程池正处于关闭状态
        //             此状态线程池不会接收新任务，但是会继续把队列中的任务处理完
        threadPoolExecutor.shutdown();

        // 3.STOP：调用线程池的 shutdownNow() 方法，线程池进入 STOP 状态，表示线程池正处于正在停止状态
        //         此状态线程池不会接收新任务，也不会处理队列中的任务，并中断正在运行的线程
        threadPoolExecutor.shutdownNow();

        // 4.TIDYING：线程池中没有线程运行后，就会自动进入 TIDYING 状态，并且会调用 terminated() 方法（该方法是空方法，可以被扩展）

        // 5.TERMINATED：执行完 terminated() 方法后，进入 TERMINATED 状态

    }

}
