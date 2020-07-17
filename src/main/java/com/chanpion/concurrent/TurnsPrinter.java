package com.chanpion.concurrent;

/**
 * @author April Chen
 * @date 2020/7/17 10:28
 */
public class TurnsPrinter {
    private final Object lock = new Object();
    private int count = 0;
    private final Runnable oddTask = () -> {
        synchronized (lock) {
            while (count < 100) {
                if (count % 2 == 1) {
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    };
    private final Runnable evenTask = () -> {
        synchronized (lock) {
            while (count < 100) {
                if (count % 2 == 0) {
                    System.out.println(Thread.currentThread().getName() + ":" + count++);
                    lock.notify();
                } else {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    };

    public void print() {
        Thread threadA = new Thread(oddTask, "threadA");
        Thread threadB = new Thread(evenTask, "threadB");
        threadA.start();
        threadB.start();
    }

    public static void main(String[] args) {
        TurnsPrinter printer = new TurnsPrinter();
        printer.print();
    }
}
