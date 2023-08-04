package com.yq.pattern.state.event;

import com.yq.pattern.state.AbstractState;
import com.yq.pattern.state.Result;
import com.yq.pattern.state.util.ResponseCode;
import com.yq.pattern.state.util.Status;

/**
 * @program: JavaDemoRep
 * @description: 关闭状态
 * @author: Yuqing
 * @create: 2023-08-04 13:08
 **/
public class CloseState extends AbstractState {

    @Override
    public Result arraignment(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "活动关闭不可提审");
    }

    @Override
    public Result checkPass(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "活动关闭不可审核通过");
    }

    @Override
    public Result checkRefuse(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "活动关闭不可审核拒绝");
    }

    @Override
    public Result checkRevoke(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "活动关闭不可撤销审核");
    }

    @Override
    public Result close(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "活动关闭不可重复关闭");
    }

    @Override
    public Result open(Long activityId, Enum<Status> currentState) {
        boolean isSuccess = activityService.execStatus(activityId, currentState, Status.OPEN);
        return isSuccess ? Result.buildResult(ResponseCode.SUCCESS,"活动开启完成"):Result.buildResult(ResponseCode.FAIL,"活动状态变更失败");
    }

    @Override
    public Result doing(Long activityId, Enum<Status> currentState) {
        return Result.buildResult(ResponseCode.FAIL, "活动关闭不可变更活动中");
    }

}
