package com.yq.util;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2024-03-26 13:57
 **/
public class LogHelper {

    public static void printLog(String logContent) {
        System.out.println(getCurrentTime() + currentThreadId()  + logContent);
    }

    private static String getCurrentTime() {
        LocalTime time = LocalTime.now();
        return time.format(DateTimeFormatter.ofPattern("[HH:mm:ss.SSS]"));
    }

    private static String currentThreadId() {
        return "[" + Thread.currentThread().getName() + "|" + Thread.currentThread().getId() + "]";
    }

}
