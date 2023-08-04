package com.yq.pattern.state;

import com.yq.pattern.state.IStateHandler;
import com.yq.pattern.state.util.ActivityService;

/**
 * @program: JavaDemoRep
 * @description: 活动状态抽象类
 * @author: Yuqing
 * @create: 2023-08-04 12:19
 **/
public abstract class AbstractState implements IStateHandler {

    /**
     * 这里可以注入操作数据库的类
     */
    protected ActivityService activityService = new ActivityService();


}
