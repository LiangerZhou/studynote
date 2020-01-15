/*
 * @lc app=leetcode.cn id=19 lang=java
 *
 * [19] 删除链表的倒数第N个节点
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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dumbhead = new ListNode(0);
        dumbhead.next = head;

        ListNode start,end; //双指针
        start = end = dumbhead;
        for(int i=0;i<n;i++){ //后指针先走N步
            end = end.next;   
        }
        while(end.next!=null){ //第二个指针指到尾部
            start = start.next;
            end = end.next;
        }
        start.next = start.next.next;
        return dumbhead.next;
    }
}
// @lc code=end

