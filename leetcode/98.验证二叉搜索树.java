/*
 * @lc app=leetcode.cn id=98 lang=java
 *
 * [98] 验证二叉搜索树
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
 * 题解：利用最大值和最小值；参考https://leetcode-cn.com/problems/validate-binary-search-tree/solution/die-dai-yu-di-gui-by-powcai/
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE,Long.MAX_VALUE);
    }

    boolean dfs(TreeNode root, long minv, long maxv){
        if(root == null) return true;
        if(root.val<=minv || root.val>=maxv) return false;
        return dfs(root.left,minv,root.val) && dfs(root.right, root.val, maxv);
    }
}
// @lc code=end

