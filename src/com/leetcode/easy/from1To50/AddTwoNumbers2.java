package com.leetcode.easy.from1To50;

public class AddTwoNumbers2 {
    // TODO 采用递归来调用
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

            // 如果结果大于10，并且l1和l2后续都没有节点了，需要手动增加一个节点，以便开始下一个循环
            if(result >= 10) {
                flag = true;
                if(l1 == null && l2 == null) {
                    l1 = new ListNode(0);
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
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        l1.next = l2;
        l2.next = l3;

        ListNode p1 = new ListNode(5);
        ListNode p2 = new ListNode(6);
        ListNode p3 = new ListNode(4);
        p1.next = p2;
        p2.next = p3;

//        ListNode l1 = new ListNode(9);
//        ListNode l2 = new ListNode(9);
//        ListNode l3 = new ListNode(9);
//        ListNode l4 = new ListNode(9);
//        ListNode l5 = new ListNode(9);
//        ListNode l6 = new ListNode(9);
//        ListNode l7 = new ListNode(9);
//        l1.next = l2;
//        l2.next = l3;
//        l3.next = l4;
//        l4.next = l5;
//        l5.next = l6;
//        l6.next = l7;
//
//        ListNode p1 = new ListNode(9);
//        ListNode p2 = new ListNode(9);
//        ListNode p3 = new ListNode(9);
//        ListNode p4 = new ListNode(9);
//        p1.next = p2;
//        p2.next = p3;
//        p3.next = p4;
//
        ListNode curr = addTwoNumbers2(l1, p1);
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