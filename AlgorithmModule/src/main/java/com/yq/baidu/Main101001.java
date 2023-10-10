package com.yq.baidu;

import java.util.Scanner;

/**
 * @program: JavaDemoRep
 * @description: 百度 - 测开 - 2023.10.10 - 第一题
 * @author: Yuqing
 * @create: 2023-10-10 19:28
 **/
public class Main101001 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine());
        String[] number = new String[n];
        for(int i=0;i<n;i++){
            number[i] = in.nextLine();
        }
        for(int i=0;i<n;i++){
            if(check(number[i])){
                System.out.println("Yes");
            }else{
                System.out.println("No");
            }
        }
        in.close();
    }

    private static boolean check(String num){
        int sum = 0;
        for(int i=0;i<3;i++){
            sum += (num.charAt(i) - '0');
        }
        for(int i=3;i<6;i++){
            sum -= (num.charAt(i) - '0');
        }
        return sum == 0;
    }
}
