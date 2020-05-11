package com.chanpion.concurrent;

import java.util.LinkedList;
import java.util.List;

/**
 * @author April Chen
 * @date 2020/5/9 16:26
 */
public class WaitNotifyBlockingBuffer<E> implements BlockingBuffer<E> {
    private static final long DEFAULT_MAX_SIZE = 100;
    private long maxSize;

    private List<E> list;

    private final Object notFull = new Object();
    private final Object notEmpty = new Object();

    public WaitNotifyBlockingBuffer() {
        this(DEFAULT_MAX_SIZE);
    }

    public WaitNotifyBlockingBuffer(long maxSize) {
        this.maxSize = maxSize;
        list = new LinkedList<>();
    }

    @Override
    public void put(E e) throws InterruptedException {
        try {
            synchronized (notFull) {
                while (size() == maxSize) {
                    notFull.wait();
                }
                list.add(e);
                System.out.println("put,size:" + list.size());
            }
        } finally {
            synchronized (notEmpty) {
                notEmpty.notifyAll();
            }
        }
    }

    @Override
    public E take() throws InterruptedException {
        try {
            synchronized (notEmpty) {
                while (size() == 0) {
                    notEmpty.wait();
                }
                E e = list.remove(0);
                System.out.println("take,size:" + list.size());
                return e;
            }
        } finally {
            synchronized (notFull) {
                notFull.notifyAll();
            }
        }
    }

    @Override
    public long size() {
        return list.size();
    }
}
