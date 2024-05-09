package com.chanpion.solution;

import java.util.BitSet;

/**
 * @author April.Chen
 * @date 2024/5/8 17:55
 */
public class BloomFilter {

    private BitSet bits;
    private int numHashFunctions;
    private HashFunction[] hashFunctions;

    public BloomFilter(int capacity, double falsePositiveProbability) {
        // 根据容量和误报率计算位数组的大小和哈希函数的数量
        // 这里只是示例，实际的计算会更复杂
        int bitsSize = (int) (-capacity * Math.log(falsePositiveProbability) / (Math.log(2) * Math.log(2)));
        this.numHashFunctions = (int) Math.round((double) bitsSize / capacity * Math.log(2));
        System.out.println("bitsSize: " + bitsSize);
        System.out.println("numHashFunctions: " + numHashFunctions);
        bits = new BitSet(bitsSize);
        hashFunctions = new HashFunction[numHashFunctions];
        for (int i = 0; i < numHashFunctions; i++) {
            // 这里简单地使用不同的盐和初始值来模拟不同的哈希函数
            // 在实际应用中，应该使用更复杂的哈希函数
            hashFunctions[i] = new HashFunction(i);
        }
    }

    public void add(String data) {
        for (HashFunction hashFunction : hashFunctions) {
            bits.set(hashFunction.hash(data));
        }
    }

    public boolean mightContain(String data) {
        for (HashFunction hashFunction : hashFunctions) {
            if (!bits.get(hashFunction.hash(data))) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(Math.log(2));
        //   容量为 100,000，误报率为 1%
        BloomFilter filter = new BloomFilter(1000000000, 0.01);
        filter.add("element1");
        filter.add("element2");
        // ... 添加更多元素

        // 应该返回 true
        System.out.println(filter.mightContain("element1"));
        // 可能返回 true（误报）或 false
        System.out.println(filter.mightContain("nonexistent"));
    }

    /**
     * 示例哈希函数类（仅用于演示）
     */
    private class HashFunction {
        private final int salt;

        public HashFunction(int salt) {
            this.salt = salt;
        }

        public int hash(String data) {
            // 这里使用简单的哈希算法（如 MurmurHash、FNV-1a 等）
            // 为了演示，我们使用简单的加法哈希
            int hash = 0;
            for (int i = 0; i < data.length(); i++) {
                hash = (hash + data.charAt(i) + salt) % bits.size();
            }
            return hash;
        }
    }


}
