package com.chanpion.solution.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author April Chen
 * @date 2020/5/11 17:30
 */
public class LRU2<K, V> {

    private Map<K, V> map;
    private final int capacity;

    public LRU2(int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<>(capacity, 0.75f, true);
    }

    public V get(K key) {
        if (!map.containsKey(key)) {
            return null;
        }

        V value = map.remove(key);
        map.put(key, value);
        return value;
    }

    public void put(K key, V value) {
        if (map.containsKey(key)) {
            map.remove(key);
        }
        if (map.size() == capacity) {
            map.remove(map.keySet().iterator().next());
        }
        map.put(key, value);
    }
}
