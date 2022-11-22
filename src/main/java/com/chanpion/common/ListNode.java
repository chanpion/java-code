package com.chanpion.common;

import javax.sound.midi.Soundbank;

/**
 * @author April Chen
 * @date 2022/11/13 21:15
 */
public class ListNode {
    public int value;
    public ListNode next;

    public ListNode(int value) {
        this.value = value;
    }

    public void print() {
        ListNode next = this;
        while (next != null) {
            System.out.print(this.value + " ");
            next = next.next;
        }
    }

    public static void print(ListNode head) {
        System.out.println("====================================");
        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
        System.out.println("====================================");
    }
}
