/*
 * @lc app=leetcode.cn id=92 lang=java
 *
 * [92] 反转链表 II
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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head ==null || n==m){
            return head;
        }

        ListNode dumy = new ListNode(0);
        dumy.next = head;
        ListNode p,q;
        p = q = dumy; 
        for(int i=1;i<m;i++){
            p = p.next;     //p指向m的前一点
        }
        for(int i = 0;i<n;i++){
            q = q.next; //q指向n的前一点
        }
      
        ListNode d = p.next; //指向m
        ListNode e = q.next; //指向n的下一点

        ListNode a = p.next; //指向m点
        ListNode b = p.next.next; //指向m+1点

        for(int i = 0;i<n-m;i++){
            ListNode c;
            c = b.next;
            b.next = a;
            a = b;
            b = c;
        }

        d.next = e; //将n的下一节点赋给 m的下一个点
        p.next = q; //将m点指向n点
        
        return dumy.next;
    }
}
// @lc code=end

