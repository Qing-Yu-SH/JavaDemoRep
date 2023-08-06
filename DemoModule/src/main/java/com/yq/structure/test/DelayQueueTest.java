package com.yq.structure.test;

import com.yq.structure.queue.DelayQueue;
import com.yq.structure.queue.Delayed;
import com.yq.structure.queue.Queue;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-06 10:09
 **/
public class DelayQueueTest {

    private Logger logger = LoggerFactory.getLogger(DelayQueueTest.class);

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

    static class Job implements Delayed{

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
