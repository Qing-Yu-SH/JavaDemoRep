package com.yq.multithreading.interviewQuestions;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description: 问题：有多个任务，一个执行错误，其他都应该取消
 *               解决方法：轮询每个任务的状态，当有任务失败时，取消其他任务的执行
 *               这种方式在业务中不可能采用，因为执行失败后，会直接退出虚拟机；主动轮询的过程，也会一直占用 CPU 的资源
 * @author: Yuqing
 * @create: 2023-09-03 15:16
 **/
public class TaskCancellationQuestion2 {

    public static void main(String[] args) {
        MyTask task01 = new MyTask("task-1", 5, true);
        MyTask task02 = new MyTask("task-2", 6, true);
        MyTask task03 = new MyTask("task-3", 1, false);

        List<MyTask> tasks = new ArrayList<>();
        tasks.add(task01);
        tasks.add(task02);
        tasks.add(task03);

        tasks.stream().forEach(t -> t.start());

        while(true){
            boolean isEnd = true;
            for (MyTask task: tasks){
                if(task.getResult() == Result.FAILED){
                    System.exit(0);
                }else if(task.getResult() == Result.NOTEND){
                    isEnd = false;
                }
            }
            if(isEnd){
                // 任务执行完成后，退出
                System.exit(0);
            }
        }
    }

    private static enum Result{
        NOTEND,
        SUCCESSED,
        FAILED
    }

    private static class MyTask extends Thread{
        private Result result = Result.NOTEND;
        private String name;
        private int timeSeconds;
        private boolean success;

        public MyTask(String name, int timeSeconds, boolean success) {
            this.name = name;
            this.timeSeconds = timeSeconds;
            this.success = success;
        }

        public Result getResult() {
            return result;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(timeSeconds* 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            result = success ? Result.SUCCESSED:Result.FAILED;

            System.out.println(name + " 任务" + (success ? "完成":"失败"));
        }

    }


}
