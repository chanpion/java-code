package com.chanpion;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author April Chen
 * @date 2020/12/17 8:46
 */
public class Solution {

    public static void main(String[] args) {
        //key是数字，value为出现次数
        TreeMap<Integer, Integer> map = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        for (int i = 0; i < m; i++) {
            int num = scanner.nextInt();
            if (!map.containsKey(num)) {
                map.put(num, 1);
            } else {
                map.put(num, map.get(num) + 1);
            }
        }

        for (int i = 0; i < m; i++) {
            int minNum = n;
            int minCount = Integer.MAX_VALUE;
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() < minCount) {
                    minCount = entry.getValue();
                    minNum = entry.getKey();
                }
            }
            map.put(minNum, map.get(minNum) + 1);
            System.out.print(minNum + " ");
        }
    }
}
