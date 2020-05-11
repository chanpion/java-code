package com.chanpion.concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author April Chen
 * @date 2020/5/9 16:36
 */
public class AwaitSignalBlockingBuffer<E> implements BlockingBuffer<E> {
    private static final long DEFAULT_MAX_SIZE = 100;
    private long maxSize = DEFAULT_MAX_SIZE;

    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    private List<E> list = new LinkedList<>();

    public AwaitSignalBlockingBuffer() {
        this(DEFAULT_MAX_SIZE);
    }

    public AwaitSignalBlockingBuffer(long maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void put(E e) throws InterruptedException {
        lock.lock();
        try {
            while (list.size() == maxSize) {
                notFull.await();
            }
            list.add(e);
            System.out.println("put,size:" + size());
            notEmpty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @Override
    public E take() throws InterruptedException {
        lock.lock();
        try {
            while (list.isEmpty()) {
                notEmpty.await();
            }
            return list.remove(0);
        } finally {
            System.out.println("take,size:" + size());
            notFull.signalAll();
            lock.unlock();
        }
    }

    @Override
    public long size() {
        return list.size();
    }
}
