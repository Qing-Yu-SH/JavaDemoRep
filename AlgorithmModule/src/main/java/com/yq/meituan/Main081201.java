package com.yq.meituan;

import java.util.Scanner;

/**
 * @program: JavaDemoRep
 * @description: 美团笔试 8.12 第一题
 *               给定一个排列，判断里面的 x,y 是否相邻
 *               排列是指一个长度为 n 的数组，其中 1 到 n 的每个元素恰好出现一次
 *
 *               输入：第一行输入一个整数 n，代表排列长度；第二行输入 n 个整数，代表排列元素；第三行输入两个正整数 x 和 y
 *               输出：Yes 或 No
 *
 *               案例：
 *               5
 *               1 3 4 2 5
 *               5 2
 *               输出：Yes
 *
 *               参考：https://codefun2000.com/p/P1441
 * @create: 2023-08-27 11:05
 **/
public class Main081201 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i=0;i<n;i++){
            nums[i] = in.nextInt();
        }
        int x = in.nextInt();
        int y = in.nextInt();
        for(int i=0;i<n;i++){
            if(nums[i] == x){
                if((i>0 && nums[i-1]==y) || nums[i+1]==y){
                    System.out.println("Yes");
                    return;
                }
            }
            if(nums[i] == y){
                if((i>0 && nums[i-1]==x) || nums[i+1]==x){
                    System.out.println("Yes");
                    return;
                }
            }
        }
        System.out.println("No");
    }

}
