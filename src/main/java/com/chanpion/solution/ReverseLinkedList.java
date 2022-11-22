package com.chanpion.solution;


import com.chanpion.common.ListNode;

import javax.sound.midi.Soundbank;
import java.util.List;

/**
 * @author April Chen
 * @date 2022/11/13 20:53
 */
public class ReverseLinkedList {

    public static ListNode reverseNode_1(ListNode node) {
        ListNode previous = null, current, next;
        current = node;
        // 如果当前node不为空,遍历链表
        while (current != null) {
            // 保存当前节点的链接
            next = current.next;
            // 修改当前节点的链接,更改为指向之前的节点
            current.next = previous;

            // 修改之前的节点的值为当前节点
            previous = current;
            // 修改当前节点的值,继续向下遍历
            current = next;
        }
        return previous;
    }

    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = head;
        ListNode cur = head.next;
        ListNode temp = null;
        while (cur != null) {
            temp = cur;
            cur = cur.next;
            temp.next = head;
            head = temp;
        }
        last.next = null;
        return head;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode cur = head;
        ListNode n = null;
        for (int i = 2; i < 10; i++) {
            n = new ListNode(i);
            cur.next = n;
            cur = cur.next;
        }

        ListNode.print(head);

        ListNode reverse = reverse(head);

        ListNode.print(reverse);
    }

}
