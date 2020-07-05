package com.chanpion;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author April Chen
 * @date 2020/7/5 14:03
 */
public class ProductSubscription {
    private final Map<Long, Set<Long>> subscriptionMap;
    public ProductSubscription(int productCount) {
        subscriptionMap = new HashMap<>(productCount);
    }
    public ProductSubscription() {
        subscriptionMap = new ConcurrentHashMap<>();
    }

    /**
     * 为指定的用户id订阅指定的商品id
     *
     * @param userId    用户ID
     * @param productId 商品ID
     */
    public synchronized void subscribe(long userId, long productId) {
        if (!subscriptionMap.containsKey(productId)) {
            Set<Long> userSet = new HashSet<>();
            userSet.add(userId);
            subscriptionMap.put(productId, userSet);
        } else {
            subscriptionMap.get(productId).add(userId);
        }
    }

    /**
     * 返回所有商品的订阅总数
     */
    public long sumOfSubscribe() {
        long sum = 0L;
        for (Map.Entry<Long, Set<Long>> entry : subscriptionMap.entrySet()) {
            sum += entry.getValue().size();
        }
        return sum;
    }

    /**
     * 根据商品id返回这个商品的订阅总数
     *
     * @param productId 商品ID
     * @return 商品订阅总数
     */
    public long getSubscribeCount(long productId) {
        return subscriptionMap.containsKey(productId) ? subscriptionMap.get(productId).size() : 0;
    }
}

