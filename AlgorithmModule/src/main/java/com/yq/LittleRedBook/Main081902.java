package com.yq.LittleRedBook;

import java.util.Scanner;

/**
 * @program: JavaDemoRep
 * @description: 小红书笔试 8.19 第二题
 *                有一个字符串 s，可以对这些字符串进行一些操作；求经过多次操作后，是否可以将一个字符串 s 变为回文串
 *                      1.对于 w 和 m，可以分别拆分为 vv 和 nn
 *                      2.对于 b 和 p，可以轴对称转换为 d 和 q
 *                      3.对于 b、d、n、q、p、u，可以 180 度翻转为 q、p、u、b、d、n
 *                可以总结得到：w=vv  ； m=nn  ；  b=q=d=p  ；  n=u
 *
 *                输入：第一行输入一个正整数 n，表示需要进行判断的字符串个数
 *                     接下来 n 行，输入一个字符串
 *                输出：Yes 或 no
 *
 *                案例：
 *                      5
 *                      wv
 *                      ubpn
 *                      abcd
 *                      mann
 *                      bqnnmqod
 *                 输出：
 *                      Yes
 *                      Yes
 *                      No
 *                      Yes
 *                      No
 *
 *                 参考：https://codefun2000.com/p/P1465
 * @create: 2023-08-27 13:57
 **/
public class Main081902 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String[] str = new String[n];
        for(int i=0;i<n;i++){
            str[i] = in.nextLine();
        }
        for(String s: str){
            resolve(s);
        }
    }

    private static void resolve(String str){
        StringBuilder sb = new StringBuilder();
        for(char ch: str.toCharArray()){
            if(ch == 'w'){
                sb.append('v').append('v');
            }else if(ch == 'm'){
                sb.append('n').append('n');
            }else if(ch=='b' || ch=='q' || ch=='d' || ch=='p'){
                sb.append('b');
            }else if(ch=='n' || ch=='u'){
                sb.append('n');
            }else{
                sb.append(ch);
            }
        }
        int left = 0,right = sb.length()-1;
        while(left < right){
            if(sb.charAt(left) != sb.charAt(right)){
                System.out.println("No");
                return;
            }
            left++;
            right--;
        }
        System.out.println("Yes");
    }

}
