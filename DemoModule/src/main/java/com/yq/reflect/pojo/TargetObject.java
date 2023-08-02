package com.yq.reflect.pojo;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-08-02 12:37
 **/
public class TargetObject {

    private String value;

    public TargetObject() {
        this.value = "Hello";
    }

    public void publicMethod(String s) {
        System.out.println("I love " + s);
    }

    private void privateMethod() {
        System.out.println("value is " + value);
    }

}
