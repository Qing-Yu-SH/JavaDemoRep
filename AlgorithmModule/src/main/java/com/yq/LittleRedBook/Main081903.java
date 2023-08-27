package com.yq.LittleRedBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @program: JavaDemoRep
 * @description: 小红书笔试 8.19 第三题
 *               在某个城市，这个城市有 n 个节点，有 m 条路连通这 n 个景点。游览景点 i 所花费的时间为 ti，获得的快乐值为 hi
 *               第 i 条路连通景点 u 和 v，通过这条路所花费的时间为 wi
 *               由于精力有限，所以至多游览 3 个景点，且这 3 个景点都是相邻的，游览和交通的总时间不能超过 k
 *               在给定的时间下，通过游览景点所能获取的最大快乐值
 *
 *               输入：第一行输入三个正整数 n、m、k，表示景点数量、路径数和规定的时间；    1 <= n,m <= 10^5   1 <= k <= 10^9
 *                    第二行输入 n 个整数，表示每个景点的快乐值 hi；                  1 <= hi <= 10^9
 *                    第三行输入 n 个整数，表示游览每个景点所需花费的时间 ti；          1 <= ti <= 10^9
 *                    接下来 m 行，每行三个正整数 ui，vi，wi，表示两个景点和这条路径所需花费的时间；   1 <= ui,vi <= n   1 <= wi <= 10^9
 *               输出：所能获得的最大快乐值
 *
 *               案例：
 *                      4 3 10
 *                      10 9 8 7
 *                      1 2 3 4
 *                      1 2 1
 *                      2 3 1
 *                      3 4 10
 *               输出：27
 *
 *               参考：https://codefun2000.com/p/P1466
 * @author: Yuqing
 * @create: 2023-08-27 14:19
 **/
public class Main081903 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] h = new int[n];
        int[] t = new int[n];
        for(int i=0;i<n;i++){
            h[i] = in.nextInt();
        }
        for(int i=0;i<n;i++){
            t[i] = in.nextInt();
        }
        List<int[]>[] graph = new List[n];
        for(int i=0;i<n;i++){
            graph[i] = new ArrayList<>();
        }
        // 建图
        for(int i=0;i<m;i++){
            int u = in.nextInt() - 1;
            int v = in.nextInt() - 1;
            int w = in.nextInt();
            graph[u].add(new int[]{v,w});
            graph[v].add(new int[]{u,w});
        }

        long ans = 0;
        // 1 个点
        for(int i=0;i<n;i++){
            if(t[i] <= k){
                ans = Math.max(ans,h[i]);
            }
        }

        // 2 个点
        for(int i=0;i<n;i++){
            for(int[] child: graph[i]){
                int j = child[0];
                int w = child[1];
                if(t[i]+t[j]+w <= k){
                    ans = Math.max(ans,(long) h[i]+h[j]);
                }
            }
        }

        // 3 个点
        // 将 i 作为中间节点，寻找两个相邻节点
        for(int i=0;i<n;i++){
            List<int[]> edges = graph[i];
            long[][] arr = new long[edges.size()][2];
            for(int j=0;j<edges.size();j++){
                int v = edges.get(j)[0];
                int w = edges.get(j)[1];
                arr[j][0] = (long)t[v] + w;
                arr[j][1] = h[v];
            }
            Arrays.sort(arr,(a,b) -> {
                if(a[0] == b[0]){
                    return Long.compare(a[1],b[1]);
                }
                return Long.compare(a[0],b[0]);
            });
            // 枚举第 2 个点
            for(int x=1;x<arr.length;x++){
                int l = 0,r = x-1;
                // 二分查找符合条件的第 3 个点
                while(l < r){
                    int mid = (r-l)/2 + l;
                    if(arr[mid][0]+arr[x][0]+t[i] <= x){
                        l = mid;
                    }else{
                        r = mid - 1;
                    }
                }
                if(arr[l][0] + arr[x][0] + t[i] <= k){
                    ans = Math.max(ans,h[i] + arr[x][1] + arr[l][1]);
                }
                // 更新快乐值，使 arr[x][1] 处的快乐值是 [0..x] 范围内的最大快乐值
                arr[x][1] = Math.max(arr[x][1],arr[x-1][1]);
            }
        }

        System.out.println(ans);
    }

}
