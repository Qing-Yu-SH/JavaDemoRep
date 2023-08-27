package com.yq.meituan;

import java.util.Scanner;

/**
 * @program: JavaDemoRep
 * @description: 美团笔试 8.12 第三题
 *               土地的大小为 n*m，每块土地都有自己的价值。对土地进行横切或竖切，得到两个土地，要求两块土地的价值尽可能接近
 *
 *               输入：第一行输入两个整数 n 和 m
 *                    输入 n 行，每行 m 个正整数；  1 <= n,m <=1000     1 <= ai <= 10000
 *               输出：两块土地价值的最小差值
 *
 *               输入：
 *                      5 5
 *                      1 1 1 1 1
 *                      1 1 1 1 1
 *                      1 1 1 1 1
 *                      1 1 1 1 1
 *                      1 1 1 1 1
 *               输出：5
 *
 *               参考：https://codefun2000.com/p/P1443
 * @author: Yuqing
 * @create: 2023-08-27 11:33
 **/
public class Main081203 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][] nums = new int[n][m];
        long sum = 0;
        long diff = Long.MAX_VALUE;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                nums[i][j] = in.nextInt();
                sum += nums[i][j];
            }
        }
        long[][] preSum = new long[n+1][m+1];
        // 二维数组前缀和
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                preSum[i][j] = preSum[i-1][j] + preSum[i][j-1] - preSum[i-1][j-1] + nums[i-1][j-1];
            }
        }
        // 横切
        for(int i=1;i<n;i++){
            long x = preSum[i][m];
            long y = sum - x;
            long d = Math.abs(y-x);
            diff = Math.min(diff,d);
        }
        // 竖切
        for(int j=1;j<m;j++){
            long x = preSum[n][j];
            long y = sum - x;
            long d = Math.abs(y-x);
            diff = Math.min(diff,d);
        }
        System.out.println(diff);
    }

}
