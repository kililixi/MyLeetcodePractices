package com.leetcode.easy.from1To50;

/**
 * https://leetcode.com/problems/add-two-numbers/
 *
 * 给定两个非空的链表代表两组非负的整数，数字方向存储，比如 2 -> 4 -> 3 代表 342.返回两组数字相加后的结果。
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers2 {

    /**
     * 思路： 把结果也当初一个链表，然后把两个链表相同位的数据相加，把结果赋给结果链表，如果有进位就+1
     *
     * 链表的结构，可以获取下一个节点的值，所以：
     * 1 先定义结果链表开始的点位
     * 2 循环处理 l1 和 l2 ，跳出条件为两个节点都是空
     * 3 获取 l1 和 l2 的当前值，如果其中一个是null，当作0
     * 4 两个相加，判断是否有进位标志，如果有，结果加1. 把 结果 % 10 的余数赋给当前结果链表.
     * 5 把l1 l2 指向下一个各自的节点， 如果上一步的结果大于等于10，标上进位标志，并判断双方是否为空，如果都为空，给结果链表加一个进位的节点，值为1，然后返回。如果不都为空，则给结果链表加一个进位的节点，值为0，继续循环。
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode curr = new ListNode(0);

        // 保存首个节点的句柄，作为结果返回
        ListNode resultNode = curr;

        // 结果是否有超过10
        boolean flag = false;

        while (l1 != null || l2 != null) {

            // 计算结果
            int a1 = l1 == null ? 0 : l1.val;
            int a2 = l2 == null ? 0 : l2.val;
            int result = a1 + a2;

            // 如果前次计算结果大于10，结果+1
            if(flag) {
                result++;
                flag = false;
            }

            curr.val = result%10;

            // 节点移动到下一个
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;

            // 如果结果大于10，并且l1和l2后续都没有节点了，给结果链表加一个进位节点，值为1,返回
            if(result >= 10) {
                flag = true;
                if(l1 == null && l2 == null) {
//                    l1 = new ListNode(0);
                    ListNode next = new ListNode(1);
                    curr.next = next;
                    return resultNode;
                }
            }

            // 如果l1和l2有一个不为null, 把结果节点移动到下一个
            if( l1 != null || l2 != null) {
                ListNode next = new ListNode(0);
                curr.next = next;
                curr = next;
            }

        }
        return resultNode;

    }

    /**
     * 类似第一个方法，只是把最后计算进位的方法放到while循环外，因为while循环的跳出条件就是 l1 和 l2为Null，没必要在循环体内一直判断
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {

        ListNode curr = new ListNode(0);

        // 保存首个节点的句柄，作为结果返回
        ListNode resultNode = curr;

        // 结果是否有进位
        boolean flag = false;

        while (l1 != null || l2 != null) {

            // 计算结果
            int a1 = l1 == null ? 0 : l1.val;
            int a2 = l2 == null ? 0 : l2.val;
            int result = a1 + a2;

            // 如果前次计算结果大于10，结果+1
            if(flag) {
                result++;
                flag = false;
            }

            curr.val = result%10;

            // 节点移动到下一个
            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
            if(l1 != null || l2 != null) {
                curr.next = new ListNode(0);
                curr = curr.next;
            }

            // 如果结果大于10，并且l1和l2后续都没有节点了，需要手动增加一个节点，以便开始下一个循环
            if(result >= 10) {
                flag = true;
            }


        }
        // 有进位，结果再+1
        if(flag) {
            curr.next = new ListNode(1);
        }

        return resultNode;

    }

    /**
     * official
     *
     * 解法都差不多，这里把进位保留，留到下一位相加时再使用
     * 比如 第一位分别是 l1 = [6,4,3], l2 = [5,6,4] 第一次 6 + 5 时，进位(carry=0),但是相加后carry 为 (6 + 5) / 10 = 1 ,余 1
     * 第二位相加时再使用这个carry, 就是 carry + 4 + 6 = 11 , 此时carry 是 11 /10 = 1， 余 1
     * 第三次是 carry + 3 + 4 = 8 ， carry 为0
     * 循环结束后， 判断carry ，如果大于 0 ，再进一位
     *
     *
     * ，最终结果 811
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode p = l1, q = l2, curr = dummyHead;
        int carry = 0;
        while (p != null || q != null) {
            int x = (p != null) ? p.val : 0;
            int y = (q != null) ? q.val : 0;
            int sum = carry + x + y;
            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            if (p != null) p = p.next;
            if (q != null) q = q.next;
        }
        if (carry > 0) {
            curr.next = new ListNode(carry);
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
//        ListNode l1 = new ListNode(2);
//        ListNode l2 = new ListNode(4);
//        ListNode l3 = new ListNode(3);
//        l1.next = l2;
//        l2.next = l3;
//
//        ListNode p1 = new ListNode(5);
//        ListNode p2 = new ListNode(6);
//        ListNode p3 = new ListNode(4);
//        p1.next = p2;
//        p2.next = p3;

        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(9);
        ListNode l3 = new ListNode(9);
        ListNode l4 = new ListNode(9);
        ListNode l5 = new ListNode(9);
        ListNode l6 = new ListNode(9);
        ListNode l7 = new ListNode(9);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        l4.next = l5;
        l5.next = l6;
        l6.next = l7;

        ListNode p1 = new ListNode(9);
        ListNode p2 = new ListNode(9);
        ListNode p3 = new ListNode(9);
        ListNode p4 = new ListNode(9);
        p1.next = p2;
        p2.next = p3;
        p3.next = p4;
//
        ListNode curr = addTwoNumbers(l1, p1);
        while(curr != null) {
            System.out.println("value: " + curr.val);
            curr = curr.next;
        }
//        System.out.println(curr.val);
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}