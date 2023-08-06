package com.yq.structure.test;

import com.yq.structure.ArrayList;
import org.junit.jupiter.api.Test;

/**
 * @program: JavaDemoRep
 * @description: 数组测试
 * @author: Yuqing
 * @create: 2023-08-06 10:46
 **/
public class ArrayTest {

    @Test
    public void test_ArrayList(){
        ArrayList<String> list = new ArrayList<>();
        list.add("01");
        list.add("02");
        list.add("03");
        list.add("04");
        list.add("05");
        list.add("06");

        System.out.println(list);

        System.out.println(list.get(2));

        list.remove(0);

        System.out.println(list);
    }
}
