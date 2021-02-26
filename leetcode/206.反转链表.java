/*
 * @lc app=leetcode.cn id=206 lang=java
 *
 * [206] 反转链表
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    // 循环做法
    public ListNode reverseList(ListNode head) {

        if(head == null){
            return null;
        }

        ListNode newhead = null; //用于保存第二个指针后面的节点
        ListNode a,b;
        a = head; //第一个指针
        b = head.next; //第二个指针
        while(b != null){
            newhead = b.next; //把第二个指针后面的节点给newhead
            b.next = a; //将第二个指针的下一个指向第一个节点
            a = b; // 将a指针后移一位
            b = newhead; //b指针后移一位
        }
        head.next = null; //将头指针的下一位指向空
        return a; // 返回头节点为a的指针
    }

    // public ListNode reverse(ListNode head){
    //     return reverseListInt(head,null);
    // }
    // private ListNode reverseListInt(ListNode head, ListNode newhead){
    //     if(head == null){
    //         return newhead;
    //     }
    //     ListNode next = head.next;
    //     head.next = newhead;
    //     return reverseListInt(next, head);
    // }
}
// @lc code=end

