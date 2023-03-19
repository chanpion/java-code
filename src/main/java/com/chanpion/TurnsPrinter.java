package com.chanpion;

/**
 * 蚂蚁-两个线程交替输出奇偶数
 * @author April Chen
 * @date 2020/7/19 20:28
 */
public class TurnsPrinter {
    private final Object lock = new Object();
    private int count = 1;

    private final Runnable printTask1 = () -> {
        synchronized (lock) {
            while (count < 100) {
                if (count % 2 == 1) {
                    System.out.println(Thread.currentThread().getName() + " - " + count++);
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException ignore) {
                    }
                }
            }
        }
    };

    private final Runnable printTask2 = () -> {
        synchronized (lock) {
            while (count < 100) {
                if (count % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + " - " + count++);
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException ignore) {
                    }
                }
            }
        }
    };

    public void print() {
        Thread printThread1 = new Thread(printTask1, "Printer1");
        Thread printThread2 = new Thread(printTask2, "Printer2");
        printThread1.start();
        printThread2.start();
    }

    public static void main(String[] args) {
        TurnsPrinter printer = new TurnsPrinter();
        printer.print();
    }
}
