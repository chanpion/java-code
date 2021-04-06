package com.chanpion;

import java.util.Scanner;

/**
 * @author April Chen
 * @date 2020/12/17 18:48
 */
public class Solution {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
