/*
 * @lc app=leetcode.cn id=160 lang=java
 *
 * [160] 相交链表
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 * 
 * 思想: a指针遍历headA，遍历完后从headB从头开始遍历；b指针遍历headB,遍历完后从headA从头开始遍历；知道a和b指针走到同一点，就是相交节点
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a,b;
        a = headA;
        b = headB;
        while(a != b){
            if(a !=null){
                a = a.next;
            }else{
                a = headB;
            }
            if(b != null){
                b = b.next;
            }else{
                b = headA;
            }
        }

        return a;

    }
}
// @lc code=end

