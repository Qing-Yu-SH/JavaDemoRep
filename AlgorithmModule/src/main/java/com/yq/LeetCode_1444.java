package com.yq;

/**
 * @program: JavaDemoRep
 * @description: 1444. 切披萨的方案数
 *               https://leetcode.cn/problems/number-of-ways-of-cutting-a-pizza/
 * @author: Yuqing
 * @create: 2023-08-17 09:25
 **/
public class LeetCode_1444 {

    private static boolean isPrintApple = true;
    private static boolean isPrintDp = true;

    public static void main(String[] args) {
        String[] pizza = new String[]{"A..","AAA","..."};
        int k = 3;
        int ways = LeetCode_1444.ways(pizza, k);
        System.out.println("------------------------------------------------");
        System.out.println("共有方案数：" + ways);
    }

    public static int ways(String[] pizza, int k) {
        int m = pizza.length, n = pizza[0].length(), mod = 1_000_000_007;
        int[][] apples = new int[m + 1][n + 1];
        int[][][] dp = new int[k + 1][m + 1][n + 1];

        // 预处理：apple[i][j] 指从 (i,j) 到 (m-1,n-1) 所围成的矩形中苹果的数量
        //        dp[1][i][j] 指从 (i,j) 到 (m-1,n-1) 所围成的矩形切成 1 块的方案
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                apples[i][j] = apples[i][j + 1] + apples[i + 1][j] - apples[i + 1][j + 1] + (pizza[i].charAt(j) == 'A' ? 1 : 0);
                dp[1][i][j] = apples[i][j] > 0 ? 1 : 0;
            }
        }

        if(isPrintApple){
            printApple(apples);
        }

        if(isPrintDp){
            printDp(1,dp[1]);
        }

        for (int ki = 2; ki <= k; ki++) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    // 水平方向切
                    for (int i2 = i + 1; i2 < m; i2++) {
                        // 有可能 (i,j) 处没有苹果，切分后，所围成的区域无苹果则不能切分
                        // 例如 i=0,j=1；此时横向切，则 i2=1,j=1，明显得到的上面区域无苹果
                        if (apples[i][j] > apples[i2][j]) {
                            dp[ki][i][j] = (dp[ki][i][j] + dp[ki - 1][i2][j]) % mod;
                        }
                    }
                    // 垂直方向切
                    for (int j2 = j + 1; j2 < n; j2++) {
                        if (apples[i][j] > apples[i][j2]) {
                            dp[ki][i][j] = (dp[ki][i][j] + dp[ki - 1][i][j2]) % mod;
                        }
                    }
                }
            }
            if(isPrintDp){
                printDp(ki,dp[ki]);
            }
        }
        return dp[k][0][0];
    }

    private static void printApple(int[][] apple){
        int m = apple.length,n = apple[0].length;
        System.out.println("苹果数量：");
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.format("%2d\t",apple[i][j]);
            }
            System.out.println();
        }
    }

    private static void printDp(int k,int[][] dp){
        int m = dp.length,n = dp[0].length;
        System.out.println("切 " + k + "次方案：");
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.format("%2d\t",dp[i][j]);
            }
            System.out.println();
        }
    }

}
