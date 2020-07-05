package com.chanpion.leetcode;

/**
 * @author April Chen
 * @date 2020/7/5 11:48
 */
public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode right = head;
        ListNode left = head;
        for (int i = 0; i < n; i++) {
            right = right.next;
        }

        if (right == null) {
            head = head.next;
            return head;
        }
        while (right.next != null) {
            left = left.next;
            right = right.next;
        }
        left.next = left.next.next;
        return head;
    }
}
