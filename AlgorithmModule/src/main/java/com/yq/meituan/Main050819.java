package com.yq.meituan;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @program: JavaDemoRep
 * @description: 美团笔试 8.19 第五题
 *               有一个长度为 n 的数组 A；对数组 A 进行改造，要求在最少操作数的情况下，使得众数出现的次数尽可能的多
 *               一次操作：选择任意两个元素，一个加 1，一个减 1
 *               众数：数组中出现次数最多的数字
 *
 *               输入：第一行一个整数 n，表示数组长度；第二行输入 n 个正整数
 *               输出：使得众数出现次数最多的情况下的最少操作次数
 *
 *               案例：
 *               3
 *               1 2 3
 *               输出：1
 *
 *               参考：https://codefun2000.com/p/P1471
 * @author: Yuqing
 * @create: 2023-08-26 14:24
 **/
public class Main050819 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        long sum = 0,ans = Long.MAX_VALUE;
        for(int i=0;i<n;i++){
            nums[i] = in.nextInt();
            sum += nums[i];
        }

        // 如果 sum%n = 0，可以将每个数都变为 sum/n
        if(sum%n == 0){
            ans = 0;
            long avg = sum/n;
            for(int num: nums){
                ans += Math.abs(avg-num);
            }
            System.out.print(ans/2);
            return;
        }
        Arrays.sort(nums);

        // 求前缀和
        int[] pre = new int[n+1];
        for(int i=1;i<=n;i++){
            pre[i] = pre[i-1] + nums[i-1];
        }

        // 枚举每个数为配对数
        for(int i=0;i<n;i++){
            ans = Math.min(ans,getAnswer(sum,i,nums,n,pre));
        }

        System.out.print(ans);
    }

    private static long getAnswer(long sum,int idx,int[] nums,int n,int[] pre){
        // n-1 个数的和
        long cur_sum = sum - nums[idx];
        // 计算 n-1 个数的平均值 floor(average)
        long avg = cur_sum / (n-1);
        // 计算 target = floor(average) 的操作数
        long ans = getOperation(avg,cur_sum,idx,nums,n,pre);
        // 计算 target = ceil(average) 的操作数
        if(cur_sum%(n-1) != 0){
            ans = Math.min(ans,getOperation(avg+1,cur_sum,idx,nums,n,pre));
        }
        return ans;
    }

    private static long getOperation(long avg,long sum,int idx,int[] nums,int n,int[] pre){
        int l = 0,r = n-1;
        while(l <= r){
            int mid = (r-l)/2 + l;
            if(nums[mid] < avg){
                l = mid+1;
            }else{
                r = mid-1;
            }
        }
        // l_sum 小于 avg 的数的和；l_cnt 小于 avg 的个数
        int l_sum = 0,l_cnt = 0;
        // r_sum 大于 avg 的数的和；r_cnt 大于 avg 的个数
        int r_sum = 0,r_cnt = 0;

        if(idx > r){
            // 配合数属于大于 avg 的部分
            l_sum = pre[r+1];
            l_cnt = r+1;
            r_sum = pre[n] - pre[r+1] - nums[idx];
            r_cnt = n - l_cnt - 1;
        }else{
            // 配合数属于小于 avg 的部分
            r_sum = pre[n] - pre[r+1];
            r_cnt = n - (r+1);
            l_sum = pre[r+1] - nums[idx];
            l_cnt = n - r_cnt - 1;
        }

        // 小于 avg 的数变换为 avg 的所需 +1 次数
        long x_avg = avg*l_cnt - l_sum;
        // 大于 avg 的数变换为 avg 的所需 -1 次数
        long y_avg = r_sum - avg*r_cnt;

        return Math.max(x_avg,y_avg);
    }

}
