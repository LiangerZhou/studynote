/*
 * @lc app=leetcode.cn id=83 lang=java
 *
 * [83] 删除排序链表中的重复元素
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

 /**
  * 标签：链表
指定 cur 指针指向头部 head
当 cur 和 cur.next 的存在为循环结束条件，当二者有一个不存在时说明链表没有去重复的必要了
当 cur.val 和 cur.next.val 相等时说明需要去重，则将 cur 的下一个指针指向下一个的下一个，这样就能达到去重复的效果
如果不相等则 cur 移动到下一个位置继续循环
时间复杂度：O(n)O(n)

作者：guanpengchn
链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/solution/hua-jie-suan-fa-83-shan-chu-pai-xu-lian-biao-zhong/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
  */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode first;
        first = head;
        while(first != null && first.next != null){
            if(first.val == first.next.val){
                first.next = first.next.next;
            }else{
                first = first.next;
            }
        }
        return head;
    }
}
// @lc code=end

