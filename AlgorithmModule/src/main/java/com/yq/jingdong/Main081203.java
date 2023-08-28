package com.yq.jingdong;

import java.util.Scanner;

/**
 * @program: JavaDemoRep
 * @description: 京东笔试 8.12 第三题
 *               有一个 n*m 的棋盘，其中 'X' 表示棋子，'.' 表示为空。要在这个棋盘中选取四个棋子组成正方形，求有多少个正方形
 *
 *               输入：第一行输入正整数 n、m，表示棋盘大小
 *                    接下来 n 行，每行 m 个字符，代表每个位置的情况
 *               输出：一个整数，表示可以获得的不同正方形的数量
 *
 *               案例：
 *                      4 4
 *                      XX.X
 *                      XX.X
 *                      .X.X
 *                      ..X.
 *               输出：2
 *
 *               参考：https://codefun2000.com/p/P1448
 * @create: 2023-08-28 12:28
 **/
public class Main081203 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        char[][] map = new char[n][m];
        for(int i=0;i<n;i++){
            String line = in.next();
            for(int j=0;j<m;j++){
                map[i][j] = line.charAt(j);
            }
        }

        int ans = 0;
        // 枚举两个顶点，可以固定一条边；通过对这条边进行顺时针移动或逆时针移动，可得到剩下两个点
        // 枚举第一个点
        for(int x1=0;x1<n;x1++){
            for(int y1=0;y1<m;y1++){
                if(map[x1][y1] == 'X'){
                    // 枚举第二个点
                    for(int x2=0;x2<n;x2++){
                        for(int y2=0;y2<m;y2++){
                            if(x1==x2 && y1==y2) continue;
                            if(map[x2][y2] == 'X'){
                                // 确定两个点，得到一条边，通过顺时针或逆时针移动后，计算得到剩下两个点
                                int dx = x2 - x1;
                                int dy = y2 - y1; // 按照向量顺时针或者逆时针
                                int x3 = x1 - dy; // 求到第三第四个点
                                int y3 = y1 + dx;
                                int x4 = x3 + dx;
                                int y4 = y3 + dy;
                                if (0 <= x3 && x3 < n && 0 <= y3 && y3 < m && 0 <= x4 && x4 < n && 0 <= y4 && y4 < m && map[x3][y3] == 'X' && map[x4][y4] == 'X') {
                                    ans++; // 符合要求就计数
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.print(ans/4);
    }

}
