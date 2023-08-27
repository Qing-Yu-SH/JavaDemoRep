package com.yq.LittleRedBook;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @program: JavaDemoRep
 * @description:  小红书笔试 8.19 第一题
 *                小 A 正在背单词，他有一套自己的记忆方法。如果他已经背了 i 个单词，则下一个要记住的单词，需要背 i+1 次
 *                给你一个单词顺序，求小 A 可以记住多少单词
 *
 *                输入：第一行正整数 n，表示单词数量     1 <= n <= 10000
 *                     接下来 n 行，每行代表一个单词
 *                输出：小 A 记住的单词数
 *
 *                案例：
 *                      7
 *                      tazi
 *                      abandon
 *                      zoo
 *                      abandon
 *                      zoo
 *                      abandon
 *                      zoo
 *               输出：3
 *
 *               参考：https://codefun2000.com/p/P1464
 *
 * @create: 2023-08-27 13:44
 **/
public class Main081901 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        int ans = 0;
        Map<String,Integer> map = new HashMap<>();
        for(int i=0;i<n;i++){
            String word = in.nextLine();
            map.put(word,map.getOrDefault(word,0)+1);
            if(map.get(word) == ans+1){
                ans++;
                map.put(word,-n);
            }
        }
        System.out.println(ans);
    }

}
