/*
 * @lc app=leetcode.cn id=142 lang=java
 *
 * [142] 环形链表 II
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null){
            return null;
        }
        ListNode fast,slow;
        fast = head;
        slow = head;

        while(fast.next != null){
            fast = fast.next;
            slow = slow.next;
            if(fast.next != null){
                fast = fast.next;
            }else{
                break;
            }

            if(fast == slow){
                fast = head;
                while(slow != fast){
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }   
        }
        return null;
    }
}
// @lc code=end

