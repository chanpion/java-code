package com.chanpion.leetcode;

/**
 * @author April Chen
 * @date 2022/11/20 20:32
 */
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        int end = s.length() - 1;
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }
        int start = end;
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }
        return end - start;
    }
}
