package com.yq.pattern.flyweight;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-14 10:42
 **/
public class ActivityFactory {

    static Map<Long,Activity> activityMap = new HashMap<>();

    public static Activity getActivity(Long id){
        Activity activity = activityMap.get(id);
        if(null == activity){
            // 模拟从实际业务应⽤从接⼝中获取活动信息
            activity = new Activity();
            activity.setId(10001L);
            activity.setName("图书嗨乐");
            activity.setDesc("图书优惠券分享激励分享活动第⼆期");
            activity.setStartTime(new Date());
            activity.setEndTime(new Date());
            activityMap.put(id, activity);
        }
        return activity;
    }


}
