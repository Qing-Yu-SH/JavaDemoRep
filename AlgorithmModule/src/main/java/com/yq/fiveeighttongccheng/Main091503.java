package com.yq.fiveeighttongccheng;

import java.util.Arrays;

/**
 * @program: JavaDemoRep
 * @description: 58同城笔试 9.16 第三题
 *               给定一个字符串 source，模式串 pattern。求 source 中有多少个 pattern
 *
 *               案例：
 *                  AAABBCCAC     ABC
 *               输出：18
 * @create: 2023-09-18 19:39
 **/
public class Main091503 {

    public static void main(String[] args) {
        Main091503 main091503 = new Main091503();
        System.out.println(main091503.subsequence("AABBBACBBC","ABC"));;
    }

    public int subsequence (String source, String pattern) {
        int n = source.length(),m = pattern.length();
        if(n < m) return 0;
        int[] preSum = new int[n];
        // 初始化
        for(int i=0;i<n;i++){
            if(source.charAt(i) == pattern.charAt(0)){
                preSum[i] += 1;
            }
            if(i>0) preSum[i] += preSum[i-1];
        }
        int[] nxtSum = new int[n];
        for(int k=1;k<m;k++){
            for(int i=k;i<n;i++){
                if(source.charAt(i) == pattern.charAt(k)){
                    nxtSum[i] += preSum[i-1];
                }
                if(i > k) nxtSum[i] += nxtSum[i-1];
            }
            System.arraycopy(nxtSum,0,preSum,0,n);
            Arrays.fill(nxtSum,0);
        }
        return preSum[n-1];
    }

}
