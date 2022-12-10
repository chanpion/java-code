package com.chanpion.leetcode;

/**
 * @author April Chen
 * @date 2022/11/23 12:44
 */
public class ClimbStairs {
    public int climbStairs(int n) {
        if(n<=1){

        }


        int f = 1;
        int g = 0;
        while(n--!=0)
        {
            f += g;
            g = f - g;
        }
        return f;
    }

    public static int climbStairsWithRecursion(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return climbStairsWithRecursion(n - 1) + climbStairsWithRecursion(n - 2);
        }
    }

    public static int climbStairsWithDynamic(int n) {
        if (n == 1) {
            return 1;
        }
        int dynamicArray[] = new int[n + 1];
        dynamicArray[1] = 1;
        dynamicArray[2] = 2;
        for (int i = 3; i <= n; i++) {
            dynamicArray[i] = dynamicArray[i - 1] + dynamicArray[i - 2];
        }

        return dynamicArray[n];
    }

    public static int climbStairsWithFibonacci(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second =third;
        }

        return second;
    }
}
