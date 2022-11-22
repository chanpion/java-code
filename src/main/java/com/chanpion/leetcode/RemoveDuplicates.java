package com.chanpion.leetcode;

/**
 * @author April Chen
 * @date 2022/11/20 16:08
 */
public class RemoveDuplicates {
    public int removeDuplicates(int[] nums) {
        int numsSize = nums.length;
        if (0 == numsSize) {
            return 0;
        }

        int i = 0;
        for (int j = 1; j < numsSize; j++) {
            if (nums[j] != nums[i]) {
                i++;
                nums[i] = nums[j];
            }
        }

        return i + 1;
    }
}
