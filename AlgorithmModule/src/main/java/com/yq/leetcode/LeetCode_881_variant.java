package com.yq.leetcode;

import java.util.Arrays;

/**
 * @program: JavaDemoRep
 * @description: LeetCode 881 的基础上，多增加一个条件，要求两个人的重量之和要小于等于 limit 的同时结果为偶数，才可以搭乘同一条船
 * @author: Yuqing
 * @create: 2023-11-04 20:25
 **/
public class LeetCode_881_variant {

    public int getMinNumBoats (int[] visitors, int limit) {
        // write code here
        int ans = 0;
        int n = visitors.length;
        boolean[] visited = new boolean[n];
        Arrays.sort(visitors);
        int left = 0,right = n - 1;
        while(left <= right){
            while(left<n && visited[left]) left++;
            while(right>=0 && visited[right]) right--;
            if(left > right) break;
            int weight = visitors[left] + visitors[right];
            if(weight <= limit){
                if(weight%2 == 0){
                    visited[left] = true;
                    visited[right] = true;
                    left++;
                    right--;
                    ans++;
                }else{
                    boolean consumeLeft = false;
                    boolean consumeRight = false;
                    int tempLeft = left+1;
                    int tempRight = right-1;
                    // 判断是否存在一个 tempRight 可以和该 left 乘同一条船
                    while(tempRight > left){
                        int tempWeight = visitors[tempRight]+visitors[left];
                        if(tempWeight > limit) break;
                        if(!visited[tempRight] && tempWeight % 2 == 0){
                            visited[left] = true;
                            visited[tempRight] = true;
                            consumeLeft = true;
                            ans++;
                            break;
                        }
                        tempRight--;
                    }
                    // 判断是否存在一个 tempLeft 可以和该 right 乘同一条船
                    while(tempLeft < right){
                        int tempWeight = visitors[tempLeft]+visitors[right];
                        if(!visited[tempLeft] && tempWeight<=limit && tempWeight%2==0){
                            visited[right] = true;
                            visited[tempLeft] = true;
                            consumeRight = true;
                            ans++;
                            break;
                        }
                        tempLeft++;
                    }
                    if(consumeLeft && consumeRight){
                        left++;
                        right--;
                    }else if(consumeLeft){
                        left++;
                    }else if(consumeRight){
                        right--;
                    }else{
                        right--;
                        ans++;
                    }
                }
            }else{
                visited[right] = true;
                ans++;
                right--;
            }
        }
        return ans;
    }

}
