/*
 * @lc app=leetcode.cn id=82 lang=java
 *
 * [82] 删除排序链表中的重复元素 II
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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dumphead = new ListNode(0);
        ListNode start,end;
        start = dumphead;
        end = head;
        start.next = end;
        while(end !=null){
            while(end.next != null && end.val == end.next.val){
                end = end.next;
            }
            if(start.next != end){
                start.next = end.next; //删除重复元素
                end = start.next;//重置end指针
            }else{
                start = start.next;            
                end = end.next;
            }
        }
        return dumphead.next;
    }
}
// @lc code=end

