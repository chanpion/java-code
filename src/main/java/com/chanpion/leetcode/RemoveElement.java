package com.chanpion.leetcode;

import com.sun.xml.internal.bind.v2.model.core.ID;

/**
 * @author April Chen
 * @date 2022/11/20 16:34
 */
public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val){
                nums[index] = nums[i];
                index ++ ;
            }
        }
        return index;
    }
}
