package com.yq.pattern.state.event;

import com.yq.pattern.state.AbstractState;
import com.yq.pattern.state.Result;
import com.yq.pattern.state.util.ResponseCode;
import com.yq.pattern.state.util.Status;

/**
 * @program: JavaDemoRep
 * @description: 通过状态
 * @author: Yuqing
 * @create: 2023-08-04 12:57
 **/
public class PassState extends AbstractState {

    @Override
    public Result arraignment(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "通过状态不可重复提审");
    }

    @Override
    public Result checkPass(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "通过状态不可重复审核");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "通过状态不可拒绝");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "通过状态不可撤审");
    }

    @Override
    public Result close(Long activityId, Enum<Status> currentState) {
        boolean isSuccess = activityService.execStatus(activityId, currentState, Status.CLOSE);
        return isSuccess ? Result.buildResult(ResponseCode.SUCCESS,"活动关闭成功"):Result.buildResult(ResponseCode.FAIL,"活动状态变更失败");
    }

    @Override
    public Result open(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "非关闭活动不可开启");
    }

    @Override
    public Result doing(Long activityId, Enum<Status> currentState) {
        boolean isSuccess = activityService.execStatus(activityId, currentState, Status.DOING);
        return isSuccess ? Result.buildResult(ResponseCode.SUCCESS,"活动变更活动中成功"):Result.buildResult(ResponseCode.FAIL,"活动状态变更失败");
    }
}
