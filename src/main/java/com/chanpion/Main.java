package com.chanpion;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine());
        int[] powers = new int[num];
        int[] otherPowers = new int[num];
        for (int i = 0; i < num; i++) {
            powers[i] = scanner.nextInt();
            otherPowers[i] = i + 1;
        }
        Arrays.sort(powers);
        int count = 0;
        for (int i = 0,j = 0; i < num && j < num; i++,j++) {
            while (powers[j] <= otherPowers[i]) {
                j++;
            }
            count++;
        }
        System.out.println(count);
    }
}
