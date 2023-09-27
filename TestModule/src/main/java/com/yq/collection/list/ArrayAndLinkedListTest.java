package com.yq.collection.list;

import com.yq.HashMapCycleTest;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-09-27 18:53
 **/
@BenchmarkMode(Mode.AverageTime)  // 测试完成时间
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 2, time = 1, timeUnit = TimeUnit.SECONDS) // 预热 2 轮，每次 1s
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS) // 测试 5 轮，每次 1s
@Fork(1) // fork 1 个线程
@State(Scope.Thread) // 每个测试线程一个实例
public class ArrayAndLinkedListTest {

    public static void main(String[] args) throws RunnerException {
        // 获取当前工作目录
        String currentWorkingDirectory = System.getProperty("user.dir");
        // 拼接上files文件夹的相对路径
        String filesFolderPath = currentWorkingDirectory + File.separator + "files" + File.separator + "TestModule";

        String logPath = filesFolderPath + File.separator + "jmh-list.json";

        // 启动基准测试
        Options opt = new OptionsBuilder()
                .include(ArrayAndLinkedListTest.class.getSimpleName()) // 要导入的测试类
                .result(logPath)
                .resultFormat(ResultFormatType.JSON)
                .build();
        new Runner(opt).run(); // 执行测试

    }

    @Benchmark
    public void test_ArrayList_addFirst() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            // add(i) 则是尾插
            list.add(0, i);
        }
        System.out.println("ArrayList 头插耗时：" + (System.currentTimeMillis() - startTime));
    }

    @Benchmark
    public void test_LinkedList_addFirst() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.addFirst(i);
        }
        System.out.println("LinkedList 头插耗时：" + (System.currentTimeMillis() - startTime));
    }

    @Benchmark
    public void test_ArrayList_addLast() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(i);
        }
        System.out.println("ArrayList 尾插耗时：" + (System.currentTimeMillis() - startTime));
    }

    @Benchmark
    public void test_LinkedList_addLast() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.addLast(i);
        }
        System.out.println("LinkedList 尾插耗时：" + (System.currentTimeMillis() - startTime));
    }

    @Benchmark
    public void test_ArrayList_addCenter() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(list.size() >> 1, i);
        }
        System.out.println("ArrayList 中间插入耗时：" + (System.currentTimeMillis() - startTime));
    }

    @Benchmark
    public void test_LinkedList_addCenter() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            list.add(list.size() >> 1, i);
        }
        System.out.println("LinkedList 中间插入耗时：" + (System.currentTimeMillis() - startTime));
    }



}
