package com.yq.meituan;

import java.util.Scanner;

/**
 * @program: JavaDemoRep
 * @description: 美团笔试 8.19 第三题
 *               一个 01 串 s。每次操作可以将一个 1 修改为 0 或者一个 0 修改为 1。求所有子串的最小操作数
 *               输入：一个 01 串       输出：最小操作数
 *               思路：最终生成的字符串只能是 "010101.." 或者 "101010.."；每读取一个字符，就判断一下这两种情况下，是否当前位置需要修改
 *               案例： 000   ->   3
 *
 *               参考：https://codefun2000.com/p/P1469
 * @create: 2023-08-26 13:26
 **/
public class Main081903 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int n = str.length();
        int ans = 0;
        for(int i=0;i<n;i++){
            // cnt0：以 0 开头的字符串
            // cnt1：以 1 开头的字符串
            int cnt0 = 0,cnt1 = 1;
            for(int j=i;j<n;j++){
                if (str.charAt(i) == '1'){
                    if((j-i)%2 == 0){
                        cnt0 += 1;
                    }else{
                        cnt1 += 1;
                    }
                }else{
                    if((j-i)%2 == 0){
                        cnt1 += 1;
                    }else{
                        cnt0 += 1;
                    }
                }
                ans += Math.min(cnt0,cnt1);
            }
        }
        System.out.print(ans);
    }

}
