package com.chanpion.leetcode;

import jdk.nashorn.internal.ir.LiteralNode;

import java.util.List;
import java.util.TreeMap;

/**
 * @author April Chen
 * @date 2022/12/5 8:29
 */
public class LInkedListSolution {

    /**
     * 203. 移除链表元素
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        ListNode temp = newHead;
        while (temp.next != null) {
            if (temp.next.val == val) {
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        return newHead.next;
    }
}
