package com.yq.trick;

/**
 * @program: JavaDemoRep
 * @description: 求最大公约数 — 辗转相除法
 * @create: 2023-08-28 10:32
 **/
public class GreatestCommonDivisor {

    public static void main(String[] args) {
        System.out.println(getGreatestCommonDivisor(10,16));
    }

    private static int getGreatestCommonDivisor(int a,int b){
        if(b == 0){
            return a;
        }else{
            return getGreatestCommonDivisor(b,a%b);
        }
    }

}
