package com.chanpion.datastructure.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * 字典树、前缀树
 *
 * @author April.Chen
 * @date 2023/4/3 10:00 上午
 **/
public class Trie {
    private TrieNode root;

    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }

        TrieNode node = root;
        TrieNode tempNode = null;
        for (int i = 0, len = word.length(); i < len; i++) {
            Character c = word.charAt(i);
            tempNode = node.content.get(c);
            if (tempNode == null) {
                tempNode = new TrieNode();
                node.content.put(c, tempNode);
            }
            node = tempNode;
        }
        node.isLeaf = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }

        TrieNode node = root;
        TrieNode tempNode = null;
        for (int i = 0, len = word.length(); i < len; i++) {
            Character c = word.charAt(i);
            tempNode = node.content.get(c);
            if (tempNode == null) {
                return false;
            }
            node = tempNode;
        }
        return node.isLeaf;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return false;
        }

        TrieNode node = root;
        TrieNode tempNode = null;
        for (int i = 0, len = prefix.length(); i < len; i++) {
            Character c = prefix.charAt(i);
            tempNode = node.content.get(c);
            if (tempNode == null) {
                return false;
            }
            node = tempNode;
        }
        return true;
    }

    public static class TrieNode {
        boolean isLeaf;
        Map<Character, TrieNode> content;

        // Initialize your data structure here.
        public TrieNode() {
            content = new HashMap<Character, TrieNode>();
        }
    }


}
