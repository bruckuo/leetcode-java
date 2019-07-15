package com.neu.x.leetcode.addTwoNumbers;

/**
 * @ description:
 * @ author: guojiang.x
 * @ created: 2019-02-25 16:27
 */
public class Solution {
    public static class Node {
        int val;
        Node next;

        Node(int x) {
            val = x;
        }
    }

    private static Node addTwoNumbers(Node l1, Node l2) {
        Node dummyHead = new Node(0);
        Node p = l1;
        Node q = l2;
        Node curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new Node(sum % 10);
            curr = curr.next;
            if (p != null) {
                p = p.next;
            }
            if (q != null) {
                q = q.next;
            }
        }
        if (carry > 0) {
            curr.next = new Node(carry);
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        Node l1 = new Node(2);
        l1.next = new Node(4);
        l1.next.next = new Node(3);

        Node l2 = new Node(5);
        l2.next = new Node(6);
        l2.next.next = new Node(6);


//        Node l3 = new Node(0);
//        Node curr = l3;
//
//        curr.next = new Node(7);
//        curr = curr.next;
//        curr.next = new Node(0);
//        curr = curr.next;
//        curr.next = new Node(8);
//        curr = curr.next;

        System.out.println(addTwoNumbers(l1, l2).val);

    }
}