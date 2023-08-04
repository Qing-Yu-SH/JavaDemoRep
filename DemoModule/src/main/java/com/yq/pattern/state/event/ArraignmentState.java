package com.yq.pattern.state.event;

import com.yq.pattern.state.AbstractState;
import com.yq.pattern.state.Result;
import com.yq.pattern.state.util.ResponseCode;
import com.yq.pattern.state.util.Status;

/**
 * @program: JavaDemoRep
 * @description: 提审状态
 * @author: Yuqing
 * @create: 2023-08-04 12:21
 **/
public class ArraignmentState extends AbstractState {

    @Override
    public Result arraignment(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL,"待审核状态不可重复提审");
    }

    @Override
    public Result checkPass(Long activityId, Enum<Status> currentState) {
        boolean isSuccess = activityService.execStatus(activityId, currentState, Status.PASS);
        return isSuccess ? Result.buildResult(ResponseCode.SUCCESS,"活动审核通过"):Result.buildResult(ResponseCode.FAIL,"活动状态变更失败");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Status> currentState) {
        boolean isSuccess = activityService.execStatus(activityId, currentState, Status.REFUSE);
        return isSuccess ? Result.buildResult(ResponseCode.SUCCESS,"活动审核拒绝通过"):Result.buildResult(ResponseCode.FAIL,"活动状态变更失败");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Status> currentState) {
        boolean isSuccess = activityService.execStatus(activityId, currentState, Status.EDIT);
        return isSuccess ? Result.buildResult(ResponseCode.SUCCESS,"活动审核撤销通过"):Result.buildResult(ResponseCode.FAIL,"活动状态变更失败");
    }

    @Override
    public Result close(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL,"提审状态不可关闭活动");
    }

    @Override
    public Result open(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL,"非关闭活动不可开启");
    }

    @Override
    public Result doing(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL,"待审核活动不可执行活动中变更");
    }

}
