package com.yq.reflect;

import com.yq.reflect.pojo.TargetObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @program: JavaDemoRep
 * @description: 反射基本操作
 * @author: Yuqing
 * @create: 2023-08-02 12:38
 **/
public class Demo_01 {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, NoSuchFieldException {
        // 获取 TargetObject 类的 Class 对象并且创建 TargetObject 类实例
        Class<?> aClass = Class.forName("com.yq.reflect.pojo.TargetObject");
        TargetObject instance = (TargetObject) aClass.newInstance();

        // 获取 TargetObject 类中定义的所有方法
        Method[] methods = aClass.getDeclaredMethods();
        System.out.println(aClass.getSimpleName() + " 方法：");
        for(Method method: methods){
            System.out.println(method.getName());
        }

        System.out.println();

        // 获取指定方法并调用
        Method publicMethod = aClass.getDeclaredMethod("publicMethod", String.class);
        publicMethod.invoke(instance,"world");

        // 获取指定参数并对参数进行修改
        Field field = aClass.getDeclaredField("value");
        field.setAccessible(true);
        field.set(instance,"private");

        System.out.println();

        // 调用 private 方法
        Method privateMethod = aClass.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true);
        privateMethod.invoke(instance);

    }

}
