package com.chanpion;

import java.util.ArrayList;
import java.util.List;

/**
 * 蚂蚁
 * 字符串的所有连续子串
 * @author April Chen
 * @date 2020/7/19 20:40
 */
public class AllSubString {

    public static void printAllSubString(char[] array) {
        if (array == null || array.length == 0) {
            return;
        }
        for (int i = 0; i < array.length; i++) {
            List<Character> subList = new ArrayList<>();
            for (int j = i; j < array.length; j++) {
                subList.add(array[j]);
                System.out.println(subList);
            }
        }
    }

    public static void main(String[] args) {
        char[] array = {'a', 'b', 'c'};
        printAllSubString(array);
    }
}
