import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode.cn id=102 lang=java
 *
 * [102] 二叉树的层次遍历
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 定义返回变量类型
       List<List<Integer>> res = new LinkedList<List<Integer>>();
       if(root == null){
           return res;
       }
       // 定义队列，将每层的节点放入队列中
       Queue<TreeNode> q = new LinkedList<TreeNode>(); 
       q.offer(root);

       while(!q.isEmpty()){
           // 计算每一层元素的个数
           int len = q.size();
           List<Integer> s = new ArrayList<>();

           for(int i = 0; i<len; i++){
               // 取出队列元素的值，放入list中
               TreeNode t = q.poll();
               s.add(t.val);

               if(t.left != null){
                   q.add(t.left); //如果存在左子树，放入队列
               }
               if(t.right != null){
                   q.add(t.right);//如果存在右子树，放入队列
               }
           }
           res.add(s); // 将每层的值，按层List<Integer>加入res中
       }
       return res;
    }
}
// @lc code=end

