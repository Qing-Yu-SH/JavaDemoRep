package com.yq.test.recycleDI;

import com.yq.spring.Autowired;
import com.yq.spring.Component;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 12:23
 **/
@Component("obj2")
public class Object2 {

    @Autowired
    private Object1 obj1;

}
