package com.yq.structure.queue;

import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-05 21:54
 **/
public interface Delayed extends Comparable<Delayed>{

    long getDelay(TimeUnit unit);

}
