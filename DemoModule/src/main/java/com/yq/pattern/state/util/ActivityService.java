package com.yq.pattern.state.util;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: JavaDemoRep
 * @description: 活动服务接口
 * @author: Yuqing
 * @create: 2023-08-04 11:07
 **/
public class ActivityService {

    private static Map<Long, ActivityInfo> activityMap = new ConcurrentHashMap<Long, ActivityInfo>();

    /**
     * 创建活动
     */
    public static void create(Long activityId,String activityName, Enum<Status> status){
        // 模拟查询活动信息
        ActivityInfo activityInfo = new ActivityInfo();
        activityInfo.setActivityId(activityId);
        activityInfo.setActivityName(activityName);
        activityInfo.setStatus(status);
        activityInfo.setBeginTime(new Date());
        activityInfo.setEndTime(new Date());
        activityMap.put(activityId, activityInfo);
    }

    /**
     * 查询活动信息
     */
    public static ActivityInfo queryActivityInfo(Long activityId) {
        if(activityMap.containsKey(activityId)){
            return activityMap.get(activityId);
        }
        return null;
    }

    /**
     * 查询活动状态
     */
    public static Enum<Status> queryActivityStatus(Long activityId){
        if(!activityMap.containsKey(activityId)) return null;
        return activityMap.get(activityId).getStatus();
    }

    /**
     * 更改状态
     */
    public synchronized boolean execStatus(Long activityId, Enum<Status> beforeStatus, Enum<Status> afterStatus) {
        if(!beforeStatus.equals(activityMap.get(activityId).getStatus())) return false;
        activityMap.get(activityId).setStatus(afterStatus);
        return true;
    }

}
