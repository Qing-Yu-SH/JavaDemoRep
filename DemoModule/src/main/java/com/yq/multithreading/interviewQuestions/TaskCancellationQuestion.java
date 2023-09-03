package com.yq.multithreading.interviewQuestions;

/**
 * @program: JavaDemoRep
 * @description: 问题：有多个任务，一个执行错误，其他都应该取消
 *               该方案并未实现一个任务执行错误，其他任务取消的效果
 * @author: Yuqing
 * @create: 2023-09-03 15:08
 **/
public class TaskCancellationQuestion {

    public static void main(String[] args) {
        MyTask task01 = new MyTask("task-1", 5, true);
        MyTask task02 = new MyTask("task-2", 6, true);
        MyTask task03 = new MyTask("task-3", 1, false);

        task01.start();
        task02.start();
        task03.start();
    }

    private static class MyTask extends Thread{
        private String name;
        private int timeSeconds;
        private boolean success;

        public MyTask(String name, int timeSeconds, boolean success) {
            this.name = name;
            this.timeSeconds = timeSeconds;
            this.success = success;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(timeSeconds*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(name + " 任务" + (success ? "完成":"失败"));
        }

    }

}
