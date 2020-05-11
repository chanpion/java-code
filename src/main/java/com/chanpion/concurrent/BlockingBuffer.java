package com.chanpion.concurrent;

/**
 * 阻塞缓冲区
 *
 * @author April Chen
 * @date 2020/5/9 15:46
 */
public interface BlockingBuffer<E> {

    /**
     * 放置元素，若已满则阻塞等待
     *
     * @param e 元素
     * @throws InterruptedException
     */
    void put(E e) throws InterruptedException;

    /**
     * 获取元素，若空则阻塞等待
     *
     * @return 一个元素
     * @throws InterruptedException
     */
    E take() throws InterruptedException;

    /**
     * 返回当前元素个数
     *
     * @return 当前元素个数
     */
    long size();
}
