package com.yq.didi;

import java.util.Arrays;

/**
 * @program: JavaDemoRep
 * @description: 给定给一个无序数组和一个目标值，请返回数组中能够组成的小于目标值且最接近目标值的结果
 * @author: Yuqing
 * @create: 2024-03-19 16:49
 **/
public class Interview_01 {

    public static void main(String[] args) {
        int[] arr = new int[]{8,9};
        int target = 9000;
        System.out.println(new Interview_01().getNumber(arr,target));
    }

    public int getNumber(int[] arr,int target){
        int n = arr.length;
        String num = String.valueOf(target);
        int ans = 0;
        Arrays.sort(arr);
        boolean isSame = true;
        int preIndex = 0;
        for(int i=0;i<num.length();i++){
            int number = num.charAt(i) - '0';
            if(isSame){
                int numIndex = binarySearch(arr,number);
                // 找不到，则回退
                if(numIndex == -1){
                    ans /= 10;
                    ans = ans*10 + arr[preIndex-1];
                    isSame = false;
                    ans = ans*10 + arr[n-1];
                }else{
                    ans = ans*10 + arr[numIndex];
                    preIndex = numIndex;
                    isSame = number==arr[numIndex];
                }
            }else {
                ans = ans*10 + arr[n-1];
            }
        }
        return ans;
    }

    private int binarySearch(int[] nums,int target){
        int left = 0,right = nums.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(nums[mid] == target){
                return mid;
            }else if(nums[mid] < target){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }
        return right;
    }

}
