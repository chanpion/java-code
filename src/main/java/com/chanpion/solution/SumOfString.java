package com.chanpion.solution;

/**
 * @author April Chen
 * @date 2021/2/2 15:25
 */
public class SumOfString {

    public static String sum(String str1, String str2) {
        int i = str1.length() - 1;
        int j = str2.length() - 1;
        String result = "";
        int carry = 0;
        for (; i >= 0 && j >= 0; i--, j--) {
            int sum = str1.charAt(i) - '0' + str2.charAt(j) - '0';
            result = (sum + carry) % 10 + result;
            carry = (sum + carry) / 10;
        }
        while (i >= 0) {
            result = (str1.charAt(i) - '0' + carry) % 10 + result;
            carry = (str1.charAt(i) - '0' + carry) / 10;
            i--;
        }

        while (j >= 0) {
            result = (str2.charAt(j) - '0' + carry) % 10 + result;
            carry = (str2.charAt(j) - '0' + carry) / 10;
            j--;
        }
        result = carry + result;
        return result;
    }

    public static void main(String[] args) {
        String str1 = "999999";
        String str2 = "1234";
        System.out.println("result:" + sum(str1, str2));
    }
}
