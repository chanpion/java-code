package com.chanpion;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Scanner;

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

        String input = scanner.nextLine();
        String bitStr = scanner.nextLine();
        int length = input.length();
        int same = 0;
        for (int i = 0; i < length; i++) {
            char ch = input.charAt(i);
            char bit = (ch >= '0' && ch <= '9') || (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z') ? '1' : '0';
            if (bit == bitStr.charAt(i)) {
                same++;
            }
        }
        System.out.println(String.format("%.2f", same * 100.0 / length) + "%");
    }
}
