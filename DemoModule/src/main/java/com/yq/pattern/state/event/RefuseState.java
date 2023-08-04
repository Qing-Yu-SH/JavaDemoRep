package com.yq.pattern.state.event;

import com.yq.pattern.state.AbstractState;
import com.yq.pattern.state.Result;
import com.yq.pattern.state.util.ResponseCode;
import com.yq.pattern.state.util.Status;

/**
 * @program: JavaDemoRep
 * @description: 拒绝状态
 * @author: Yuqing
 * @create: 2023-08-04 13:03
 **/
public class RefuseState extends AbstractState {

    @Override
    public Result arraignment(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "已审核状态不可重复提审");
    }

    @Override
    public Result checkPass(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "已审核状态不可重复审核");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL,"活动不可重复拒绝");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Status> currentState) {
        boolean isSuccess = activityService.execStatus(activityId, currentState, Status.EDIT);
        return isSuccess ? Result.buildResult(ResponseCode.SUCCESS,"撤销审核完成"):Result.buildResult(ResponseCode.FAIL,"活动状态变更失败");
    }

    @Override
    public Result close(Long activityId, Enum<Status> currentState) {
        boolean isSuccess = activityService.execStatus(activityId, currentState, Status.CLOSE);
        return isSuccess ? Result.buildResult(ResponseCode.SUCCESS,"活动审核关闭完成"):Result.buildResult(ResponseCode.FAIL,"活动状态变更失败");
    }

    @Override
    public Result open(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL,"非关闭活动不可开启");
    }

    @Override
    public Result doing(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL,"审核拒绝不可执行活动为进行中");
    }

}
