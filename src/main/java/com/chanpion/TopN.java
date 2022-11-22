package com.chanpion;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author April Chen
 * @date 2020/7/19 20:49
 */
public class TopN {
    private static PriorityQueue<Integer> queue = new PriorityQueue<>(100);

    public static void topN(String filename) {
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8.name());) {
            scanner.useDelimiter("\r\n");
            while (scanner.hasNext()) {
                String line = scanner.next();
                String[] array = line.split(",");
                for (String str : array) {
                    int num = Integer.parseInt(str);
                    insert(num);
                }
            }
        } catch (FileNotFoundException e) {
            //
        }
    }

    public static void insert(int num) {
        if (queue.size() < 100 && !queue.contains(num)) {
            queue.add(num);
        } else {
            int head = queue.peek();
            if (head < num) {
                queue.remove();
                queue.add(num);
            }
        }
    }

    public static void test() {
        Random random = new Random();
        for (int i = 0; i < 10000; i++) {
            int num = random.nextInt(1000);
            insert(num);
        }
        System.out.println(queue);
    }

    public static void main(String[] args) {
        String filename = "data.txt";
        topN(filename);
        List<Integer> result = new ArrayList<>(queue);
        System.out.println(result);
    }
}
