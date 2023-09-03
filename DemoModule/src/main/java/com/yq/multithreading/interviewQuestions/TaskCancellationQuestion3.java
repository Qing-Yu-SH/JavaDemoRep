package com.yq.multithreading.interviewQuestions;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description: 问题：有多个任务，一个执行错误，其他都应该取消
 *               为每一个任务线程配置了相同的控制线程 Controller，每当任务线程执行完操作后，主动调用 Controller 的 end() 方法
 *               检测该任务线程是否执行完成，当执行失败时，做一些取消其他线程的任务
 *               问题：检测到任务失败时，直接退出虚拟机
 * @create: 2023-09-03 15:28
 **/
public class TaskCancellationQuestion3 {

    public static void main(String[] args) {
        Controller controller = new Controller();

        MyTask task01 = new MyTask(controller,"task-1", 5, true);
        MyTask task02 = new MyTask(controller,"task-2", 6, true);
        MyTask task03 = new MyTask(controller,"task-3", 1, false);

        controller.addTask(task01);
        controller.addTask(task02);
        controller.addTask(task03);

        controller.start();
    }

    private static enum Result{
        NOTEND,
        SUCCESSED,
        FAILED
    }

    private static class MyTask extends Thread{
        private Controller controller;
        private Result result = Result.NOTEND;
        private String name;
        private int timeSeconds;
        private boolean success;

        public MyTask(Controller controller,String name, int timeSeconds, boolean success) {
            this.controller = controller;
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

            controller.end(this);
        }

    }

    private static class Controller extends Thread{
        private List<MyTask> tasks;

        public Controller() {
            tasks = new ArrayList<>();
        }

        public void addTask(MyTask task){
            tasks.add(task);
        }

        @Override
        public void run() {
            tasks.stream().forEach(t -> t.start());
        }

        public void end(MyTask task){
            if(task.getResult() == Result.FAILED){
                // 直接退出虚拟机
                System.exit(0);
            }
        }
    }

}
