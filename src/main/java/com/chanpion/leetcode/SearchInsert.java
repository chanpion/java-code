package com.chanpion.leetcode;

/**
 * @author April Chen
 * @date 2022/11/20 16:51
 */
public class SearchInsert {

    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = ((high - low) >> 1) + low;
            if (target == nums[mid]) {
                return mid;
            }
            if (target < nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
