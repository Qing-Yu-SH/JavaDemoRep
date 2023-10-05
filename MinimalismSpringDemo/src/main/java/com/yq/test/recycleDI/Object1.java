package com.yq.test.recycleDI;

import com.yq.spring.Autowired;
import com.yq.spring.Component;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-05 12:22
 **/
@Component("obj1")
public class Object1 {

    @Autowired
    private Object2 obj2;

}
