/*
 * @lc app=leetcode.cn id=148 lang=java
 *
 * [148] 排序链表
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
    public ListNode sortList(ListNode head) {
        // 求出链表长度
        int n = 0;
        for( ListNode p = head; p.next!= null;p=p.next){
            n++;
        }

        ListNode dumyhead = new ListNode(0);
        dumyhead.next = head;

        for(int i=0;i<n;i *= 2){
            ListNode cur = dumyhead;
            
            for(int j=0;j+i<n;j += i*2){
                ListNode left = cur.next;
                ListNode right = cur.next;

                for(int k=0;k<i;k++){
                    right = right.next;
                }

                int l = 0;
                int r = 0;

                while(l<i && r<i && right.next !=null){
                    if(left.val <= right.val){
                        cur.next = left;
                        cur = left;
                        left = left.next;
                        l++;
                    }else{
                        cur.next = right;
                        cur = right;
                        right = right.next;
                        r++;
                    }

                    while(l<i){
                        cur.next = left;
                        cur = left;
                        left = left.next;
                        l++;
                    }

                    while(r<i && right.next != null){
                        cur.next = right;
                        cur = right;
                        right = right.next;
                        r++;
                    }

                    cur.next = right;
                }
            }
        }
        return dumyhead.next;
    }
}
// @lc code=end

