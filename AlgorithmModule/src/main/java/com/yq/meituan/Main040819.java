package com.yq.meituan;

import java.util.Scanner;

/**
 * @program: JavaDemoRep
 * @description: 美团笔试 8.19 第四题
 *               有一个长度为 n 的数组 a。重新构造一个长度为 n 的数组 b，要求满足以下条件
 *                  1.数组 a 的和 等于 数组 b 的和
 *                  2.a[i] 不等于 b[i] (0 <= i < n)
 *                  3.b[i] > 0
 *               要求出有多少种不同的构造方案，答案对 1000000007 求模
 *
 *               输入：第一行一个整数 n，表示数组长度；第二行输入 n 个正整数
 *               输出：一个正整数，表示方案数
 *
 *               案例：
 *               3
 *               1 2 3
 *               输出：3
 *
 *               参考：https://codefun2000.com/p/P1470
 * @create: 2023-08-26 13:34
 **/
public class Main040819 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        final int MOD = 1000000007;
        int n = in.nextInt();
        int[] nums = new int[n];
        int sum = 0;
        for(int i=0;i<n;i++){
            nums[i] = in.nextInt();
            sum += nums[i];
        }
        // dp[i][j]：表示前 i 个数构成和为 j 的方案数
        int[][] dp = new int[n+1][sum+1];
        dp[0][0] = 1;
        for(int i=1;i<=n;i++){
            // 由于数组中元素都为正整数，所以前 i 个数的最小和为 i
            for(int j=i;j<=sum;j++){
                for(int k=1;k<=j;k++){
                    if(nums[i-1] != k){
                        dp[i][j] = (dp[i][j] + dp[i-1][j-k])%MOD;
                    }
                }
            }
        }
        System.out.println(dp[n][sum]);
    }

}
