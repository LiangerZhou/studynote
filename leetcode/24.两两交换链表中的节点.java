/*
 * @lc app=leetcode.cn id=24 lang=java
 *
 * [24] 两两交换链表中的节点
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
    public ListNode swapPairs(ListNode head) {

        ListNode pre = new ListNode(0);
        pre.next = head;
        ListNode temp = pre;

        while(temp.next != null && temp.next.next !=null){
            ListNode start = temp.next;
            ListNode end = temp.next.next;

            temp.next = end; //虚拟头节点指向第二个节点
            start.next = end.next; // 原始第一个节点指向第二个节点后一节点
            end.next = start; //原始第二个指向第一个（换向）
            temp = start; // 虚拟头节点指向前一段的最后一个

        }
        return pre.next;
    }  
}
// 递归解决方法
// class Solution {
//     public ListNode swapPairs(ListNode head) {
//         if(head == null || head.next == null){
//             return head;
//         }
//         ListNode next = head.next;
//         head.next = swapPairs(next.next);
//         next.next = head;
//         return next;
//     }
// }

// 作者：guanpengchn
// 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/hua-jie-suan-fa-24-liang-liang-jiao-huan-lian-biao/
// 来源：力扣（LeetCode）
// 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
// @lc code=end

