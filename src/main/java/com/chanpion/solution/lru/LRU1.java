package com.chanpion.solution.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author April Chen
 * @date 2020/5/11 17:25
 */
public class LRU1<K, V> {
    private Map<K, V> map;
    private final int capacity;

    public LRU1(int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                // 容量大于capacity 时就删除
                return size() > capacity;
            }
        };
    }

    public V get(K key) {
        return map.get(key);
    }

    public void put(K key, V value) {
        map.put(key, value);
    }
}
