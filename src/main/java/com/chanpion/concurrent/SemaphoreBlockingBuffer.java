package com.chanpion.concurrent;

import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * @author April Chen
 * @date 2020/5/9 17:16
 */
public class SemaphoreBlockingBuffer<E> implements BlockingBuffer<E> {
    private static final long DEFAULT_MAX_SIZE = 100;
    private long maxSize;

    private List<E> list;

    private Semaphore semaphore;

    public SemaphoreBlockingBuffer() {
        this(DEFAULT_MAX_SIZE);
    }

    public SemaphoreBlockingBuffer(long maxSize) {
        this.maxSize = maxSize;
        semaphore = new Semaphore((int) maxSize);
    }

    @Override
    public void put(E e) throws InterruptedException {

    }

    @Override
    public E take() throws InterruptedException {
        return null;
    }

    @Override
    public long size() {
        return 0;
    }
}
