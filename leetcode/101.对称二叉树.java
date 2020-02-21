import java.util.Stack;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode.cn id=101 lang=java
 *
 * [101] 对称二叉树
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
 * 
 * 递归算法：
 * 1、两个根节点的值相等
 * 2、左边的左子树和右边的右子树对称
 * 3、左边的右子树和右边的左子树对称
 * 
 */
class Solution {
    // 递归
    public boolean isSymmetric(TreeNode root) {
        if(root == null){
            return true;
        }
        return dfs(root.left,root.right);
    }

    boolean dfs(TreeNode left, TreeNode right){
        if(left == null || right == null){
            return (left == right);
        }
        return left.val == right.val && dfs(left.left, right.right) && dfs(left.right,right.left);
    }

    //迭代
    //中序遍历，左边：左中右遍历；右边：右中左遍历
    // public boolean isSymmetric(TreeNode root) {
    //     if(root == null){
    //         return true;
    //     }
    //     Stack<TreeNode> l = new Stack<>();
    //     Stack<TreeNode> r = new Stack<>();
    //     TreeNode p = root.left;
    //     TreeNode q = root.right;
    //     while(p!=null || q!=null || l.size()>0 || r.size()>0){
    //         //中序遍历对称做法，左边：左中右遍历；右边：右中左遍历 加入栈中
    //         while(p != null && q != null){
    //             l.push(p);
    //             p = p.left;
    //             r.push(q);
    //             q = q.right;
    //         }
            
    //         // 遍历完后发现一个空一个不为空，返回false
    //         if(p==null || q==null){
    //             return false;
    //         }
    //         //把栈中的第一个元素拿出来比对两个值是否相等，不等则退出
    //         p = l.peek(); l.pop();
    //         q = r.peek(); l.pop();
    //         if(p.val !=q.val){
    //             return false;
    //         }
    //         //然后拿出后面的元素，遍历左边的右节点，右边的左节点
    //         p = p.right;
    //         q = q.left;
    //     }
    //     return true;
    // }
}
// @lc code=end

