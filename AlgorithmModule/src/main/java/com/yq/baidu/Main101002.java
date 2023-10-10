package com.yq.baidu;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: JavaDemoRep
 * @description: 百度 - 测开 - 2023.10.10 - 第二题
 * @author: Yuqing
 * @create: 2023-10-10 19:39
 **/
public class Main101002 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int k = in.nextInt();
        int[] address = new int[m];
        for(int i=0;i<m;i++){
            address[i] = in.nextInt();
        }
        Arrays.sort(address);
        long ans = 0;
        int pre = 0;
        for(int i=0;i<m-k+1;i++){
            int left = address[i] - pre;
            int lastPos = i+k-1;
            int right = 0;
            if(lastPos+1 < m){
                if(address[lastPos]+1 == address[lastPos+1]){
                    right = 1;
                }else{
                    right = address[lastPos+1] - address[lastPos];
                }
            }else{
                if(address[lastPos] == n){
                    right = 1;
                }else{
                    right = n - address[lastPos] + 1;
                }
            }
            ans += ((long)left*right);
            pre = address[i];
        }
        System.out.println(ans);
    }

}
