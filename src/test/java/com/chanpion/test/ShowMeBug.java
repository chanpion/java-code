// 写一个字符串大整数的加法运算函数，输入为两个正整数字符串，长度不等，上限8192，函数返回这两个正整数之和对应的字符串，举例：“123” + “11” = “134”；

// 使用Java语言；时间不超过20分钟；
package com.chanpion.test;

public class ShowMeBug {
    public static String sum(String str1, String str2) {
        // TODO: 补全加法运算代码逻辑；
        int length1 = str1.length();
        int length2 = str2.length();
        String result = "";
        int tmp = 0;
        int i = length1 - 1;
        int j = length2 - 1;
        for (; i >= 0 && j >= 0; i--, j--) {
            int sum = str1.charAt(i) - '0' + str2.charAt(j) - '0';
            System.out.println(sum);
            result = sum % 10 + tmp + result;
            System.out.println(result);
            tmp = sum / 10;
            System.out.println(tmp);
        }
        if (i >= 0) {
            while (i >= 0) {
                result = (str1.charAt(i) - '0') % 10 + tmp + result;
                tmp = (str1.charAt(i) - '0') / 10;
                i--;
            }
        }
        if (j >= 0) {
            while (j >= 0) {
                result = (str2.charAt(j) - '0') % 10 + tmp + result;
                tmp = (str2.charAt(j) - '0') / 10;
                j--;
            }
        }
        if (tmp > 0) {
            result = tmp + result;
        }
        return result;
    }

    public static void main(String[] args) {
//     String str1 = "999999";
//     String str2 = "1234";
        String str1 = "123";
        String str2 = "11";
        String result = sum(str1, str2);
        System.out.println("The sum is: " + result);
    }
}
