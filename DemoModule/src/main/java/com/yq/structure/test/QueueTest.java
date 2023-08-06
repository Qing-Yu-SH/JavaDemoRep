package com.yq.structure.test;

import com.yq.structure.queue.DelayQueue;
import com.yq.structure.queue.Delayed;
import com.yq.structure.queue.PriorityQueue;
import com.yq.structure.queue.Queue;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemoRep
 * @description: 队列测试
 * @author: Yuqing
 * @create: 2023-08-06 10:42
 **/
public class QueueTest {

    private Logger logger = LoggerFactory.getLogger(QueueTest.class);


    @Test
    public void test_PriorityQueue(){
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.offer(1);
        queue.offer(5);
        queue.offer(6);
        queue.offer(12);
        queue.offer(16);
        queue.offer(2);
        System.out.println(queue.peek());

        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        while(!queue.isEmpty()){
            Integer x = queue.poll();
            sb.append(x);
            if(!queue.isEmpty()){
                sb.append(" , ");
            }
        }
        sb.append(" ]");
        System.out.println(sb.toString());
    }



    @Test
    public void test_DelayQueue() throws InterruptedException {
        Queue<Job> queue = new DelayQueue<>();

        queue.add(new Job("任务 1",2000L));
        queue.add(new Job("任务 2",2200L));
        queue.add(new Job("任务 3",2600L));
        queue.add(new Job("任务 4",5000L));
        queue.add(new Job("任务 5",1200L));
        queue.add(new Job("任务 6",22L));

        while (true){
            Job job = queue.poll();
            if(job == null){
                Thread.sleep(10);
                continue;
            }
            logger.info(job.getName());
        }
    }

    static class Job implements Delayed {

        private final String name;
        private final Long beginTime;
        private final Long delayedTime;

        public Job(String name, Long delayedTime) {
            this.name = name;
            this.beginTime = System.currentTimeMillis();
            this.delayedTime = delayedTime;
        }

        public String getName() {
            return name;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(beginTime+delayedTime - System.currentTimeMillis(),TimeUnit.MICROSECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            Job job = (Job) o;
            return (int) (this.getDelay(TimeUnit.MICROSECONDS) - job.getDelay(TimeUnit.MICROSECONDS));
        }
    }

}
