package com.yq.meituan;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @program: JavaDemoRep
 * @description: 美团笔试 8.12 第五题
 *               给定一棵树，每个节点都有权值，且颜色为白色。
 *               当两个有边相连的白色节点，并且其权值乘积是完全平方数，则可以将其染红。
 *               求这棵树最多可以染红多少节点？
 *
 *               输入：第一行输入一个正整数 n，代表节点的数量
 *                    第二行输入 n 个正整数，分别代表每个节点的权值
 *                    接下来的 n-1 行，每行输入两个正整数 u 和 v，代表节点 u 和 v 有一条边连接
 *               输出：染红的节点个数
 *
 *               案例：
 *                      3
 *                      3 5 7
 *                      1 2
 *                      2 3
 *               输出：0
 *
 *               参考：https://codefun2000.com/p/P1445
 * @author: Yuqing
 * @create: 2023-08-27 13:06
 **/
public class Main081205 {

    static List<List<Integer>> edges;
    // dp[i][0]：表示以节点 i 为根的子树，当 i 不染色时，子树中染色节点的最大数量
    // dp[i][1]：表示以节点 i 为根的子树，当 i 染色时，子树中染色节点的最大数量
    static int[][] dp;
    static int[] weight;
    static int ans;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        edges = new ArrayList<>();
        int n = in.nextInt();
        dp = new int[n][2];
        weight = new int[n];
        for(int i=0;i<n;i++){
            weight[i] = in.nextInt();
            edges.add(new ArrayList<>());
        }
        for(int i=0;i<n-1;i++){
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            edges.get(u).add(v);
            edges.get(v).add(u);
        }
        ans = 0;
        dfs(0,-1);
        System.out.println(ans);
    }

    private static void dfs(int cur,int parent){
        List<Integer> children = edges.get(cur);
        // 节点 cur 不染色
        for(int child: children){
            if(child != parent){
                dfs(child,cur);
                dp[cur][0] += Math.max(dp[child][0],dp[child][1]);
            }
        }

        for(int child: children){
            if(child != parent){
                if(qualifyB(weight[cur],weight[child])){
                    // dp[cur][0]：包含当 cur 节点不染色时，其子节点染色或不染色，所能得到的最大染色节点
                    // - Math.max(dp[child][0],dp[child][1])：child 需要进行染色，所以需要将 child 在 dp[cur][0] 中的贡献去掉
                    // dp[child][0]：以 child 为根节点，并且 child 不染色时的最大染色节点数量加上
                    // +2：将 cur 和 child 染色
                    dp[cur][1] = Math.max(dp[cur][1],dp[cur][0] - Math.max(dp[child][0],dp[child][1]) + dp[child][0] + 2);
                }
            }
        }

        ans = Math.max(dp[cur][0],dp[cur][1]);
    }

    private static boolean qualifyB(int weight1, int weight2) {
        long product = (long) weight1 * weight2;
        long x = (long) Math.sqrt(product);
        return x * x == product;
    }

}
