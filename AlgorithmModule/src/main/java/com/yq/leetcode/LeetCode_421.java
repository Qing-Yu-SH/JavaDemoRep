package com.yq.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: JavaDemoRep
 * @description:
 * @author: Yuqing
 * @create: 2023-11-04 09:29
 **/
public class LeetCode_421 {

    public static void main(String[] args) {
        int[] arr = new int[]{3,10,5,25,2,8};
        int maximumXOR = findMaximumXOR(arr);
        System.out.println(maximumXOR);
    }

    public static int findMaximumXOR(int[] nums) {
        int HIGH_BIT = 30;
        int x = 0;
        for (int k = HIGH_BIT; k >= 0; --k) {
            Set<Integer> seen = new HashSet<Integer>();
            // 将所有的 bit[31..k] 位放入哈希表中，只保留从最高位开始到第 k 个二进制位为止的部分
            for (int num : nums) {
                // 如果只想保留从最高位开始到第 k 个二进制位为止的部分，只需将其右移 k 位
                seen.add(num >> k);
            }

            // 目前 x 包含从最高位开始到第 k+1 个二进制位为止的部分
            // 将 x 的第 k 个二进制位置为 1，即为 x = (x << 1) + 1 = x*2+1
            int xNext = x * 2 + 1;
            boolean found = false;

            // 枚举 i；只要存在两个数字的 bit[31..k] 异或后等于 xNext，则说明可以将 k 位置 1
            for (int num : nums) {
                if (seen.contains(xNext ^ (num >> k))) {
                    found = true;
                    break;
                }
            }

            if (found) {
                x = xNext;
            } else {
                // 如果没有找到满足等式的 a_i 和 a_j，那么 x 的第 k 个二进制位只能为 0
                // 即为 x = x*2
                x = xNext - 1;
            }
        }
        return x;
    }

}
