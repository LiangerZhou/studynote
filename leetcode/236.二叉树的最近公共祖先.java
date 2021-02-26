/*
 * @lc app=leetcode.cn id=236 lang=java
 *
 * [236] 二叉树的最近公共祖先
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 如果以root为根节点的子树中包含p和q，则返回它们的最近公共祖先
        // 如果只包含p，则返回p
        // 如果只包含q，则返回q
        // 如果都不包含，则返回null
        if(root==null || root == p || root == q){
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 如果左边是空的，则返回右边，进入递归
        if(left == null){
            return right;
        }
        // 如果右边为空，则返回左边，进入递归
        if(right == null){
            return left;
        }
        // 如果两边都不为空，说明p和q分别在左右两边，则返回根节点root
        return root;
    }
}
// @lc code=end

