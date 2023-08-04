package com.yq.pattern.state.event;

import com.yq.pattern.state.AbstractState;
import com.yq.pattern.state.Result;
import com.yq.pattern.state.util.ResponseCode;
import com.yq.pattern.state.util.Status;

/**
 * @program: JavaDemoRep
 * @description: 运行状态
 * @author: Yuqing
 * @create: 2023-08-04 13:15
 **/
public class DoingState extends AbstractState {

    @Override
    public Result arraignment(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "活动中不可提审");
    }

    @Override
    public Result checkPass(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "活动中不可审核通过");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "活动中不可审核拒绝");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "活动中不可撤销审核");
    }

    @Override
    public Result close(Long activityId, Enum<Status> currentState) {
        boolean isSuccess = activityService.execStatus(activityId, currentState, Status.CLOSE);
        return isSuccess ? Result.buildResult(ResponseCode.SUCCESS,"活动关闭成功"):Result.buildResult(ResponseCode.FAIL,"活动状态变更失败");
    }

    @Override
    public Result open(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "活动中不可开启");
    }

    @Override
    public Result doing(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "活动中不可重复执行");
    }
}
