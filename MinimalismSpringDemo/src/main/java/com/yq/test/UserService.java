package com.yq.test;

import com.yq.spring.Autowired;
import com.yq.spring.Component;
import com.yq.spring.InitializingBean;
import com.yq.spring.Scope;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 10:39
 **/
@Component("userService")
@Scope("prototype")
public class UserService implements InitializingBean {

    @Autowired
    private OrderService orderService;

    // 默认用户信息
    private User defaultUser;

    public void test(){
        System.out.println(orderService);
        System.out.println(defaultUser);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // 从 mysql 获取默认用户信息，并封装成 user
        defaultUser = new User();
    }

}
