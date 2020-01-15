/*
 * @lc app=leetcode.cn id=61 lang=java
 *
 * [61] 旋转链表
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
    public ListNode rotateRight(ListNode head, int k) {
    //     ListNode left, right;
    //     ListNode dumyhead = new ListNode(0);
    //     dumyhead.next = right = head;
    //     left = head;
    //     while(left.next != null){
    //         left = left.next;
    //         if(left.next == null){
    //             left.next = dumyhead.next;
    //         }
    //     }
    //     left = dumyhead;
    //     while(k != 0){
    //         right = right.next;
    //         left = left.next;
    //         k--;
    //     }
    //     left.next = null;
    //     return right;
    // }
    // 以上未考虑k大于n的情况
    if(head == null || head.next == null){
        return head;
    }
    ListNode dumy = new ListNode(0);
    dumy.next = head;
    ListNode fast,slow;
    fast = slow = dumy;

    int i;
    for(i = 0;fast.next !=null;i++){ //求出链表长度 i
        fast = fast.next;
    }

    for(int j = i-k%i;j>0;j--){ //获取i-k%i的节点
        slow = slow.next;
    }
    fast.next = dumy.next; // 尾节点和头节点相连
    dumy.next = slow.next; //慢指针的下一个节点就是新的头节点
    slow.next = null; //慢指针为最后一位，下一位置空

    return dumy.next;
    }
}
// @lc code=end

