package com.yq.pattern.state;

import com.alibaba.fastjson.JSON;
import com.yq.pattern.state.impl.StateHandlerImpl;
import com.yq.pattern.state.util.ActivityService;
import com.yq.pattern.state.util.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-04 12:36
 **/

/**
 * 状态模式
 * 状态模式描述的是⼀个⾏为下的多种状态变更，比如我们最常⻅的⼀个网站的页面，在你登录与不登录下展示的内容是略有差异的( 不登录不能展示个⼈信息 )，
 * ⽽这种 登录 与 不登录 就是我们通过改变状态，而让整个行为发生了变化。
 *
 * 优点：
 * 满足了 单⼀职责 和 开闭原则
 *
 * 缺点：
 * 在状态和各项流转较多的情况下，会产⽣较多的实现类，会让代码的实现上带来了时间成本
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        StateHandlerImpl stateHandler = new StateHandlerImpl();
        init();

        test_Editing2Arraignment(stateHandler);
        test_Editing2Open(stateHandler);
        logger.info("--------------------------------------------------------------------");

        test_Refuse2Doing(stateHandler);
        test_Refuse2Revoke(stateHandler);
        logger.info("--------------------------------------------------------------------");

        test_OPEN2Doing(stateHandler);
        logger.info("--------------------------------------------------------------------");

        test_DOING2Open(stateHandler);

    }

    private static void init(){
        ActivityService.create(100001L,"早起看书学习", Status.EDIT);
        ActivityService.create(100002L,"早起锻炼身体", Status.EDIT);
        ActivityService.create(100003L,"早起学习英语", Status.REFUSE);
        ActivityService.create(100004L,"早起吃早饭", Status.REFUSE);
        ActivityService.create(100005L,"早起去会客", Status.OPEN);
        ActivityService.create(100006L,"早起喝热水", Status.DOING);
    }

    private static void test_Editing2Arraignment(IStateHandler stateHandler){
        Result result = stateHandler.arraignment(100001L, Status.EDIT);
        logger.info("测试结果(编辑中To提审活动)：{}", JSON.toJSONString(result));
        logger.info("活动信息：{} 状态：{}",
                JSON.toJSONString(ActivityService.queryActivityInfo(100001L)),
                JSON.toJSONString(ActivityService.queryActivityInfo(100001L).getStatus()
                ));
    }

    private static void test_Editing2Open(IStateHandler stateHandler){
        Result result = stateHandler.open(100002L, Status.EDIT);
        logger.info("测试结果(编辑中To开启活动)：{}", JSON.toJSONString(result));
        logger.info("活动信息：{} 状态：{}",
                JSON.toJSONString(ActivityService.queryActivityInfo(100002L)),
                JSON.toJSONString(ActivityService.queryActivityInfo(100002L).getStatus()
                ));
    }

    private static void test_Refuse2Doing(IStateHandler stateHandler){
        Result result = stateHandler.doing(100003L, Status.REFUSE);
        logger.info("测试结果(拒绝To活动中)：{}", JSON.toJSONString(result));
        logger.info("活动信息：{} 状态：{}",
                JSON.toJSONString(ActivityService.queryActivityInfo(100003L)),
                JSON.toJSONString(ActivityService.queryActivityInfo(100003L).getStatus()
                ));
    }

    private static void test_Refuse2Revoke(IStateHandler stateHandler){
        Result result = stateHandler.checkRevoke(100004L, Status.REFUSE);
        logger.info("测试结果(拒绝To撤审)：{}", JSON.toJSONString(result));
        logger.info("活动信息：{} 状态：{}",
                JSON.toJSONString(ActivityService.queryActivityInfo(100004L)),
                JSON.toJSONString(ActivityService.queryActivityInfo(100004L).getStatus()
                ));
    }

    private static void test_OPEN2Doing(IStateHandler stateHandler){
        Result result = stateHandler.doing(100005L, Status.OPEN);
        logger.info("测试结果(打开To活动中)：{}", JSON.toJSONString(result));
        logger.info("活动信息：{} 状态：{}",
                JSON.toJSONString(ActivityService.queryActivityInfo(100005L)),
                JSON.toJSONString(ActivityService.queryActivityInfo(100005L).getStatus()
                ));
    }

    private static void test_DOING2Open(IStateHandler stateHandler){
        Result result = stateHandler.open(100006L, Status.DOING);
        logger.info("测试结果(活动中To打开)：{}", JSON.toJSONString(result));
        logger.info("活动信息：{} 状态：{}",
                JSON.toJSONString(ActivityService.queryActivityInfo(100006L)),
                JSON.toJSONString(ActivityService.queryActivityInfo(100006L).getStatus()
                ));
    }

}
