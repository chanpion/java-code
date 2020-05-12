package com.chanpion.solution.lru;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author April Chen
 * @date 2020/5/11 19:50
 */
public class LRU3<K, V> {
    private Map<K, V> map;
    private List<K> list;

    private final int capacity;

    public LRU3(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        list = new LinkedList<>();
    }

    public void put(K key, V value) {

    }

    public V get(K key) {
        V value = map.get(key);

        return value;
    }

    class Node<K, V> {
        K key;
        V value;
        Node prev;
        Node next;

    }
}
