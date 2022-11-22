package com.chanpion.solution;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * @author April Chen
 * @date 2020/7/18 11:50
 */
public class TopN {
    // 父节点
    private int parent(int n) {
        return (n - 1) / 2;
    }

    // 左孩子
    private int left(int n) {
        return 2 * n + 1;
    }

    // 右孩子
    private int right(int n) {
        return 2 * n + 2;
    }

    // 构建堆
    private void buildHeap(int n, int[] data) {
        for (int i = 1; i < n; i++) {
            int t = i;
            // 调整堆
            while (t != 0 && data[parent(t)] > data[t]) {
                int temp = data[t];
                data[t] = data[parent(t)];
                data[parent(t)] = temp;
                t = parent(t);
            }
        }
    }

    // 调整data[i]
    private void adjust(int i, int n, int[] data) {
        if (data[i] <= data[0]) {
            return;
        }
        // 置换堆顶
        int temp = data[i];
        data[i] = data[0];
        data[0] = temp;
        // 调整堆顶
        int t = 0;
        while ((left(t) < n && data[t] > data[left(t)])
                || (right(t) < n && data[t] > data[right(t)])) {
            if (right(t) < n && data[right(t)] < data[left(t)]) {
                // 右孩子更小，置换右孩子
                temp = data[t];
                data[t] = data[right(t)];
                data[right(t)] = temp;
                t = right(t);
            } else {
                // 否则置换左孩子
                temp = data[t];
                data[t] = data[left(t)];
                data[left(t)] = temp;
                t = left(t);
            }
        }
    }

    // 寻找topN，该方法改变data，将topN排到最前面
    public void findTopN(int n, int[] data) {
        // 先构建n个数的小顶堆
        buildHeap(n, data);
        // n往后的数进行调整
        for (int i = n; i < data.length; i++) {
            adjust(i, n, data);
        }
    }

    // 打印数组
    public void print(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    public static void insertSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int toInsert = array[i];
            int insertPos = 0;
            for (int j = i; j > 0; j--) {
                if (array[j - 1] < toInsert) {
                    insertPos = j;
                    break;
                }
                array[j] = array[j - 1];

            }
            array[insertPos] = toInsert;
        }
    }

    public void topN(int n, int[] data) {
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {

        }
    }

    public void top() {

    }

    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            queue.add(random.nextInt(100000));
        }
        System.out.println(queue);
        for (int i = 0; i < 10000; i++) {
            int num = random.nextInt(100000);
            int head = queue.peek();
            if (num > head) {
                queue.remove();
                queue.add(num);
            }
        }
        System.out.println(queue);
    }

    public void insert(){

    }
}
