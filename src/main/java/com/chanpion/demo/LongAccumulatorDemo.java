package com.chanpion.demo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * @author April.Chen
 * @date 2024/5/9 09:21
 */
public class LongAccumulatorDemo {

    public static void main(String[] args) throws InterruptedException {
        LongAccumulator balancer = new LongAccumulator(Long::sum, 10000L);
        ExecutorService executor = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 50; i++) {
            executor.submit(() -> balancer.accumulate(1000L));
        }

        executor.shutdown();
        if (executor.awaitTermination(1000L, TimeUnit.MILLISECONDS)) {
            System.out.println("Balance: " + balancer.get());
        }
        assert balancer.get() == 60000L;
    }
}
