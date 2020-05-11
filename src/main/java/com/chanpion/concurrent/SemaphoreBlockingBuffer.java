package com.chanpion.concurrent;

/**
 * @author April Chen
 * @date 2020/5/9 17:16
 */
public class SemaphoreBlockingBuffer<E> implements BlockingBuffer<E> {
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
