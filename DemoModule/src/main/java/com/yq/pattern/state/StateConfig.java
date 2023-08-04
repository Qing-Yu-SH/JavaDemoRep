package com.yq.pattern.state;

import com.yq.pattern.state.event.*;
import com.yq.pattern.state.util.Status;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-04 13:19
 **/
public class StateConfig {

    protected static Map<Enum<Status>,AbstractState> stateGroup = new ConcurrentHashMap<Enum<Status>,AbstractState>(){{
        put(Status.EDIT,new EditingState());
        put(Status.ARRAIGNMENT,new ArraignmentState());
        put(Status.PASS,new PassState());
        put(Status.REFUSE,new RefuseState());
        put(Status.CLOSE,new CloseState());
        put(Status.OPEN,new OpenState());
        put(Status.DOING,new DoingState());
    }};

}
