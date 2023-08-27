package com.yq;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: JavaDemoRep
 * @description: 247. 中心对称数 II
 *               https://leetcode.cn/problems/strobogrammatic-number-ii/?envType=study-plan-v2&envId=premium-algo-100
 * @author: Yuqing
 * @create: 2023-08-27 09:44
 **/
public class LeetCode_247 {

    public static void main(String[] args) {
        LeetCode_247 obj = new LeetCode_247();
        List<String> ans = obj.findStrobogrammatic(2);
        System.out.println(Arrays.toString(ans.toArray()));
    }


    List<String> ans;
    StringBuilder sb;
    int[] dic = new int[]{0,1,6,8,9};
    int[] map;

    public List<String> findStrobogrammatic(int n) {
        ans = new ArrayList<>();
        sb = new StringBuilder();
        map = new int[10];
        map[1] = 1;
        map[6] = 9;
        map[8] = 8;
        map[9] = 6;
        dfs(n);
        return ans;
    }

    private void dfs(int n){
        if(sb.length() == n/2){
            char[] arr = sb.toString().toCharArray();
            if(n%2 == 1){
                ans.add(sb.toString() + "0" + upsideDown(arr));
                ans.add(sb.toString() + "1" + upsideDown(arr));
                ans.add(sb.toString() + "8" + upsideDown(arr));
            }else{
                ans.add(sb.toString() + upsideDown(arr));
            }
            return;
        }

        for(int i=0;i<dic.length;i++){
            if(sb.length()==0 && dic[i]==0) continue;
            sb.append(dic[i]);
            dfs(n);
            sb = sb.deleteCharAt(sb.length()-1);
        }
    }

    private String upsideDown(char[] arr){
        StringBuilder builder = new StringBuilder();
        for(int i=arr.length-1;i>=0;i--){
            builder.append(map[arr[i]-'0']);
        }
        return builder.toString();
    }

}
