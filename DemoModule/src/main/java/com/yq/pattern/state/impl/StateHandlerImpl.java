package com.yq.pattern.state.impl;

import com.yq.pattern.state.IStateHandler;
import com.yq.pattern.state.Result;
import com.yq.pattern.state.StateConfig;
import com.yq.pattern.state.util.Status;

/**
 * @program: JavaDemoRep
 * @description: 状态处理服务
 * @author: Yuqing
 * @create: 2023-08-04 13:27
 **/
public class StateHandlerImpl extends StateConfig implements IStateHandler {

    @Override
    public Result arraignment(Long activityId, Enum<Status> currentState) {
        return stateGroup.get(currentState).arraignment(activityId, currentState);
    }

    @Override
    public Result checkPass(Long activityId, Enum<Status> currentState) {
        return stateGroup.get(currentState).checkPass(activityId, currentState);
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Status> currentState) {
        return stateGroup.get(currentState).checkRefuse(activityId, currentState);
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Status> currentState) {
        return stateGroup.get(currentState).checkRevoke(activityId, currentState);
    }

    @Override
    public Result close(Long activityId, Enum<Status> currentState) {
        return stateGroup.get(currentState).close(activityId, currentState);
    }

    @Override
    public Result open(Long activityId, Enum<Status> currentState) {
        return stateGroup.get(currentState).open(activityId, currentState);
    }

    @Override
    public Result doing(Long activityId, Enum<Status> currentState) {
        return stateGroup.get(currentState).doing(activityId, currentState);
    }
}
