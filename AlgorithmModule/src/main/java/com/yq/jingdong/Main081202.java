package com.yq.jingdong;

import java.util.Scanner;

/**
 * @program: JavaDemoRep
 * @description: 京东笔试 8.12 第二题
 *               有一个特异数组。每次操作可以取出最后两位进行加或乘，然后将运算结果放到数组末尾。
 *               求当特异数组只剩下一位后，这个数为 0、1、2、..、9 的各个方案数为多少
 *               例如：[1,2,5] -> 做加法 [1,7] -> 做乘法 [7]
 *
 *               输入：第一行正整数 n，表示数组长度
 *                    第二行 n 个正整数，表述数组元素
 *               输出：10 个整数，第 i 个数代表结果为 i 的方案数
 *
 *               案例：
 *                    4
 *                    1 3 1 4
 *               输出：0 0 1 1 0 1 1 1 2 1
 *
 *               参考：https://codefun2000.com/p/P1447
 *
 *               核心思想：动态规划
 *                       定义数组：dp[i][j] 表示从第 i 个数到第 n 个数，进行加法或乘法运算后，运算结果为 j 的方案数
 *                       状态转移：假设 0~9 表示为 x，循环遍历
 *                               加法：dp[i][(x+nums[i])%10] += dp[i+1][x]
 *                               乘法：dp[i][(x*nums[i])%10] += dp[i+1][x]
 * @create: 2023-08-28 11:11
 **/
public class Main081202 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for(int i=0;i<n;i++){
            nums[i] = in.nextInt();
        }
        final int MOD = 1000000007;
        if(n == 1){
            int res = nums[0] % 10;
            for(int i=0;i<10;i++){
                if(res == i){
                    System.out.print("1");
                }else{
                    System.out.print("0");
                }
                if(i != 9) System.out.print(" ");
            }
        }

        // 方式1：使用二维数组
        // dp[i][j]：从第 i 个数到第 n 个数，进行加法或乘法运算后，运算结果为 j 的方案数
//        int[][] dp = new int[n+1][10];
//        dp[n-1][nums[n-1]%10] = 1;
//        for(int i=n-2;i>=0;i--){
//            int num = nums[i]%10;
//            for(int j=0;j<10;j++){
//                dp[i][(num+j)%10] += dp[i+1][j];
//                dp[i][(num+j)%10] %= MOD;
//                dp[i][(num*j)%10] += dp[i+1][j];
//                dp[i][(num*j)%10] %= MOD;
//            }
//        }
//        for(int j=0;j<10;j++){
//            System.out.print(dp[0][j]);
//            if(j != 9) System.out.print(" ");
//        }

        // 方式2：使用滚动数组
        int[] dp_0 = new int[10];
        int[] dp_1 = new int[10];
        dp_0[nums[n-1]%10] = 1;
        for(int i=n-2;i>=0;i--){
            int num = nums[i]%10;
            for(int x=0;x<10;x++){
                dp_1[(num+x)%10] += dp_0[x];
                dp_1[(num+x)%10] %= MOD;
                dp_1[(num*x)%10] += dp_0[x];
                dp_1[(num*x)%10] %= MOD;
            }
            swap(dp_0,dp_1);
        }
        for(int j=0;j<10;j++){
            System.out.print(dp_0[j]);
            if(j != 9) System.out.print(" ");
        }
    }

    private static void swap(int[] dp_0,int[] dp_1){
        for(int i=0;i<10;i++){
            dp_0[i] = dp_1[i];
            dp_1[i] = 0;
        }
    }

}
