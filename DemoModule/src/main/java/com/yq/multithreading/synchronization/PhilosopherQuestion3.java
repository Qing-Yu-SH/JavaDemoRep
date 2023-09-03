package com.yq.multithreading.synchronization;

import java.util.Random;

/**
 * @program: JavaDemoRep
 * @description: 哲学家问题 —— 解决死锁问题
 *                           编号为偶数的先拿左边筷子，编号为奇数的先拿右边筷子
 * @create: 2023-09-03 14:45
 **/
public class PhilosopherQuestion3 {

    private static Random random = new Random();

    public static void main(String[] args) {
        Chopsticks cs0 = new Chopsticks();
        Chopsticks cs1 = new Chopsticks();
        Chopsticks cs2 = new Chopsticks();
        Chopsticks cs3 = new Chopsticks();
        Chopsticks cs4 = new Chopsticks();

        Philosopher ps0 = new Philosopher(0, cs0, cs1);
        Philosopher ps1 = new Philosopher(1, cs1, cs2);
        Philosopher ps2 = new Philosopher(2, cs2, cs3);
        Philosopher ps3 = new Philosopher(3, cs3, cs4);
        Philosopher ps4 = new Philosopher(4, cs4, cs0);

        ps0.start();
        ps1.start();
        ps2.start();
        ps3.start();
        ps4.start();
    }

    private static class Chopsticks{}

    private static class Philosopher extends Thread{
        private int index;
        private Chopsticks left;
        private Chopsticks right;

        public Philosopher(int index, Chopsticks left, Chopsticks right) {
            this.index = index;
            this.left = left;
            this.right = right;
        }

        @Override
        public void run() {

            try {

                if(index%2 == 0){
                    synchronized (left){
                        Thread.sleep(random.nextInt(1000));
                        synchronized (right){
                            Thread.sleep(random.nextInt(1000));
                            System.out.println(index + " 号哲学家完成 Eating");
                        }
                    }
                }else {
                    synchronized (right){
                        Thread.sleep(random.nextInt(1000));
                        synchronized (left){
                            Thread.sleep(random.nextInt(1000));
                            System.out.println(index + " 号哲学家完成 Eating");
                        }
                    }
                }

            }catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}
