package com.yq.test;

import com.yq.util.SpringUtil;
import org.junit.Test;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-10-06 19:08
 **/
public class SysTest extends BasicTest{

    @Test
    public void test_springUtil(){
        String config = SpringUtil.getConfig("alarm.user", "xx@qq.com");
        System.out.println(config);
    }
}
