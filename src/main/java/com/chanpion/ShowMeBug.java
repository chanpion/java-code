package com.chanpion;

import javax.sound.midi.Soundbank;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author April Chen
 * @date 2021/3/12 19:28
 */
public class ShowMeBug {
    public static void main(String[] args) {
        System.out.println("微信红包算法：");
        dealRedPackage(13.14, 7);
        dealRedPackage(10, 5);
    }

    //请补充下列函数内容
    public static void dealRedPackage(double money, int n) {
        if (money <= 0 || n <= 0) {
            System.out.println("参数非法");
            return;
        }
        List<Double> result = new ArrayList<>();
        if (n == 1) {
            result.add(money);
        } else {
            double remainMoney = money;
            int remainNum = n;
            Random random = new Random();
            double min = 0.01;
            for (int i = 0; i < n - 1; i++) {
                double max = Math.max(remainMoney * 0.9, remainMoney / remainNum * 2);
                double randMoney = random.nextDouble() * max;
                randMoney = Math.max(randMoney, min);
                randMoney = BigDecimal.valueOf(randMoney).setScale(2, RoundingMode.FLOOR).doubleValue();
                result.add(randMoney);
                remainMoney -= randMoney;
                remainNum--;
            }
            double randMoney = BigDecimal.valueOf(remainMoney).setScale(2, RoundingMode.FLOOR).doubleValue();
            result.add(randMoney);
        }
        System.out.println(result);
        double total = 0;
        for (Double m : result) {
            total += m;
        }
        System.out.println("total:" + total);
    }
}
