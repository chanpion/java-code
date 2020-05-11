package com.chanpion.concurrent;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author April Chen
 * @date 2020/5/9 16:32
 */
public class ProducerConsumer {
    private BlockingBuffer<Integer> buffer;
    private Random random = new Random();

    public ProducerConsumer(BlockingBuffer<Integer> buffer) {
        this.buffer = buffer;
    }

    public void produce() {
        while (true) {
            try {
                Thread.sleep(random.nextInt(1000));
                buffer.put(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void consume() {
        while (true) {
            try {
                Thread.sleep(random.nextInt(1000));
                buffer.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Executor executor = Executors.newFixedThreadPool(10);
        BlockingBuffer<Integer> buffer = new AwaitSignalBlockingBuffer<>(20);
        ProducerConsumer producerConsumer = new ProducerConsumer(buffer);
        executor.execute(() -> producerConsumer.consume());
        for (int i = 0; i < 3; i++) {
            executor.execute(() -> producerConsumer.produce());
        }

    }
}
