package com.chanpion.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class TaskTurnsExecutor {
    private ReentrantLock lock = new ReentrantLock();
    private int count = 0;
    private int threadCount = 3;
    private Condition[] conditions;

    public TaskTurnsExecutor(int count, int threadCount) {
        this.count = count;
        this.threadCount = threadCount;
        conditions = new Condition[threadCount];
        for (int i = 0; i < threadCount; i++) {
            conditions[i] = lock.newCondition();
        }
    }

    public static void main(String[] args) {

    }

    class ExecuteTask implements Runnable {
        private final int no;

        public ExecuteTask(int no) {
            this.no = no;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                while (count % threadCount != no) {

                }
            }
        }
    }
}
