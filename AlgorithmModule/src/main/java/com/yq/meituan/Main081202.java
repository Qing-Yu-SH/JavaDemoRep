package com.yq.meituan;

import java.util.Scanner;

/**
 * @program: JavaDemoRep
 * @description: 美团笔试 8.12 第二题
 *               存在一个环形仓储，共有 n 个仓储点。如果要将货物从 i 点搬运到 j，求搬运的最短距离；表示可以顺时针走，也可以逆时针走
 *
 *               输入： 第一行一个整数 n，代表仓储点的数量；  1 < n < 10^5
 *                     第二行 n 个正整数，代表 前 n-1 个数，指从 i 到达 i+1 的距离，第 n 个数代表从 n 到达 1 的距离；  1 <= ai <= 10^9
 *                     第三行两个正整数 x 和 y，代表将货物从 x 点搬运到 y 点；   1 <= x,y <=n
 *
 *               输出：需要走的最短距离
 *
 *               案例：
 *                   3
 *                   1 2 3
 *                   1 2
 *               输出：1
 *
 *               参考：https://codefun2000.com/p/P1442
 * @create: 2023-08-27 11:16
 **/
public class Main081202 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        // 通过前缀和的思想来得到 x点 到 y点 的距离
        // 通过 距离总和-当前距离，得到逆时针方向的距离
        long sum = 0;
        long[] preSum = new long[n+1];
        for(int i=1;i<=n;i++){
            int dis = in.nextInt();
            sum += dis;
            preSum[i] = preSum[i-1] + dis;
        }
        int x = in.nextInt();
        int y = in.nextInt();
        if(x > y){
            int t = y;
            y = x;
            x = t;
        }
        long distance = preSum[y-1] - preSum[x-1];
        System.out.println(Math.min(distance,sum-distance));
    }

}
