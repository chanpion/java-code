package com.chanpion.leetcode;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.util.Arrays;

/**
 * 给出一个区间的集合，请合并所有重叠的区间。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * <p>
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * @author April Chen
 * @date 2020/7/5 17:32
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length < 1) {
            return new int[0][];
        }
        int[][] answer = new int[intervals.length][];
        answer[0] = intervals[0];
        int index = 0;
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            if (interval[0] > answer[index][1]) {
                answer[++index] = interval;
            } else if (interval[1] < answer[index][0]) {
                answer[++index] = answer[index];
                answer[index] = interval;
            } else if (interval[0] < answer[index][0] && interval[1] < answer[index][1]) {
                answer[index] = new int[]{interval[0], answer[index][1]};
            } else if (interval[0] < answer[index][0] && interval[1] >= answer[index][1]) {
                answer[index] = new int[]{interval[0], interval[1]};
            } else if (interval[0] >= answer[index][0] && interval[1] <= answer[index][1]) {
            } else if (interval[0] <= answer[index][1]) {
                answer[index] = new int[]{answer[index][0], interval[1]};
            }
        }
        return Arrays.copyOf(answer, index + 1);
    }
}
