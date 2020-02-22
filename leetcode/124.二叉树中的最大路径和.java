/*
 * @lc app=leetcode.cn id=124 lang=java
 *
 * [124] 二叉树中的最大路径和
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
    // 自低至顶的递归
    // 向左走：-10 + L
    // 向右走：-10 + R
    // 不走：-10 + 0；此时意味着 L 和R 都是非常小的数，此时不走才是最大


    // 对于任意一个节点, 如果最大和路径包含该节点, 那么只可能是两种情况:
    //  1. 其左右子树中所构成的和路径值较大的那个加上该节点的值后向父节点回溯构成最大路径
    //  2. 左右子树都在最大路径中, 加上该节点的值构成了最终的最大路径
    // https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-30/
    // https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/solution/binary-tree-maximum-path-sum-bottom-up-di-gui-jie-/

    int res = Integer.MIN_VALUE; // 定义成全局最小值
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return res;
    }
    int dfs(TreeNode root){
        if(root == null){
            return 0;
        }
        // 左子树走的最大值, 
        int left = dfs(root.left);
        // 右子树走的最大值
        int right = dfs(root.right);

        // 最大值等于经过该节点的左子数走的最大值和右子树走的最大值和当前节点值的和
        res = Math.max(res, left + root.val + right);

        // 返回的左右子树走的点的最大值和根节点的值之和
        int tmp = root.val + Math.max(left,right);
        return tmp>0 ? tmp : 0;
    }
}
// @lc code=end

