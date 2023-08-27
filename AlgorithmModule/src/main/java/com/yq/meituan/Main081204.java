package com.yq.meituan;

import java.util.Scanner;

/**
 * @program: JavaDemoRep
 * @description: 美团笔试 8.12 第四题
 *               给定一个长度为 n 的字符串，将其转换成大小为 x*y 的矩阵
 *               例如                  a
 *                      a b c    ->   b
 *                                    c
 *               保证 x*y = n，要求找到一个权值最小的矩阵（即连通块数量最少），并输出权值
 *
 *               输入：第一行输入一个正整数 n；      1 <= n <= 10000
 *                    第二行输入一个长度为 n 的字符串；
 *               输出：最小权值
 *
 *               案例：
 *                      8
 *                      abaababa
 *               输出：3
 *
 *               参考：https://codefun2000.com/p/P1444
 * @author: Yuqing
 * @create: 2023-08-27 11:54
 **/
public class Main081204 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String str = in.nextLine();
        int ans = Integer.MAX_VALUE;

        for(int q=1;q<=n/2;q++){
            int x = q,y = n/q;
            if(x*y != n) continue;

            // 字符串平铺
            char[][] cA = new char[x][y];
            for(int i=0;i<x;i++){
                for(int j=0;j<y;j++){
                    cA[i][j] = str.charAt(i*y + j);
                }
            }

            // 将连通块连接
            boolean[][] visited = new boolean[x][y];
            int times = 0;
            for(int i=0;i<x;i++){
                for(int j=0;j<y;j++){
                    if(!visited[i][j]){
                        times++;
                        dfs(cA,i,j,visited);
                    }
                }
            }

            ans = Math.min(ans,times);
        }

        System.out.println(ans);
    }

    static int[][] dirs = new int[][]{{-1,0},{1,0},{0,1},{0,-1}};
    private static void dfs(char[][] graph,int x,int y,boolean[][] visited){
        visited[x][y] = true;
        int n = graph.length,m = graph[0].length;
        char temp = graph[x][y];
        for(int[] dir: dirs){
            int newX = x + dir[0];
            int newY = y + dir[1];
            if(newX<0 || newX>=n || newY<0 || newY>=m) continue;
            if(temp==graph[newX][newY] && !visited[newX][newY]){
                dfs(graph,newX,newY,visited);
            }
        }
    }

}
