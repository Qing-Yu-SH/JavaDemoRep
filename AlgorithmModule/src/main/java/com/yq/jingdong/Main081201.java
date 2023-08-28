package com.yq.jingdong;

import java.util.Scanner;

/**
 * @program: JavaDemoRep
 * @description: 京东笔试 8.12 第一题
 *               有一个字符串 s，通过以下两种操作，将其变为回文串。求最少的操作次数
 *                  1.拿出字符串的第一个字母，将其插入字符串的末尾
 *                  2.将字符串中的一个字符变为任意一个字符
 *
 *               输入：第一行正整数 n，代表字符串长度；    1 <= n <= 1000
 *                    第二行字符串
 *               输出：最小操作数
 *
 *               案例：
 *                      5
 *                      kkhbc
 *               输出：2
 *
 *               参考：https://codefun2000.com/p/P1446
 *
 *               核心思路：枚举。先进性移动，因为修改后再进行移动，可能造成移动的无效。枚举多次的移动操作，获取最少的操作次数
 * @create: 2023-08-28 10:45
 **/
public class Main081201 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String str = in.nextLine();
        int ans = getOperationTimes(str);
        // 枚举移动次数
        for(int i=1;i<n;i++){
            str = str.substring(1) + str.charAt(0);
            ans = Math.min(ans,getOperationTimes(str)+i);
        }
        System.out.println(ans);
    }

    private static int getOperationTimes(String str){
        int left = 0,right = str.length()-1;
        int times = 0;
        while(left < right){
            if(str.charAt(left) != str.charAt(right)){
                times++;
            }
            left++;
            right--;
        }
        return times;
    }

}
