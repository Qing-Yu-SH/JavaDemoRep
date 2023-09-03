package com.yq.multithreading.interviewQuestions;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description: 问题：有多个任务，一个执行错误，其他都应该取消
 *               当任务失败时，调用所有还在执行的任务线程的 cancel() 方法
 *               cancel() 方法由任务线程自己实现，因为不同的任务线程取消的处理方式不同
 * @create: 2023-09-03 15:37
 **/
public class TaskCancellationQuestion4 {

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
        FAILED,
        CANCELED
    }

    private static class MyTask extends Thread{
        private Controller controller;
        private Result result = Result.NOTEND;
        private String name;
        private int timeSeconds;
        private boolean success;
        private boolean canceling;

        public MyTask(Controller controller,String name, int timeSeconds, boolean success) {
            this.controller = controller;
            this.name = name;
            this.timeSeconds = timeSeconds;
            this.success = success;
            this.canceling = false;
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

            int interval = 100;
            long total = 0;
            // 每隔 100 毫秒检查一下 canceling 标志位
            while(true){

                try {
                    // 进行业务处理
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                total += 100;
                if(total/1000 >= timeSeconds){
                    System.out.println(name + " 任务" + (success ? "完成":"失败"));
                    result = success ? Result.SUCCESSED:Result.FAILED;
                    break;
                }

                // 执行取消操作
                if (canceling){
                    rollback();
                    result = Result.CANCELED;
                    canceling = false;
                    System.out.println(name + " 任务取消");
                    break;
                }
            }

            controller.end(this);
        }

        public void cancel(){
            // 设置标志位
            canceling = true;
        }

        // 回滚
        private void rollback() {
            System.out.println(name + " 任务 rollback start");
            try {
                // 执行具体的回滚操作
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name + " 任务 rollback end");
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
                cancel(task);
            }
        }

        // 取消正在执行的任务线程
        private void cancel(MyTask t){
            for(MyTask task: tasks){
                if(task != t){
                    task.cancel();
                }
            }
        }
    }


}
