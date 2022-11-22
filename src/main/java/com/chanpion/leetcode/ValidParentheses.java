package com.chanpion.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * @author April Chen
 * @date 2022/11/20 14:32
 */
public class ValidParentheses {

    public boolean isValid(String s) {
        if (s == null) {
            return false;
        }
        int length = s.length();
        if (length % 2 != 0) {
            return false;
        }
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put(']', '[');
        pairs.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (pairs.containsKey(ch)) {
                if (stack.isEmpty() || pairs.get(ch) != stack.peek()) {
                    return false;
                }
                stack.pop();
            } else {
                stack.push(ch);
            }

        }
        return stack.isEmpty();
    }

    public boolean isValid2(String s) {
        LinkedList<Character> linkedList = new LinkedList();
        for (int i = 0, len = s.length(); i < len; i++) {
            char ch = s.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{') {
                linkedList.addFirst(ch);
            } else if (linkedList.isEmpty()) {
                return false;
            } else {
                char top = linkedList.peekFirst();
                if (ch == ')' && top == '(' || ch == ']' && top == '[' || ch == '}' && top == '{') {
                    linkedList.pop();
                } else {
                    return false;
                }
            }
        }

        return linkedList.isEmpty();
    }
}
