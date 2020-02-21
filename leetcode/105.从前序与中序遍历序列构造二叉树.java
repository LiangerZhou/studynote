import java.util.HashMap;

/*
 * @lc app=leetcode.cn id=105 lang=java
 *
 * [105] 从前序与中序遍历序列构造二叉树
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
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--22/
 */
class Solution {
    Map<Integer,Integer> pos = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        for(int i = 0; i<n;i++){
            pos.put(inorder[i], i);
        }
        return dfs(preorder,inorder,0,n-1,0,n-1);
    }

    private TreeNode dfs(int[] preorder, int[] inorder, int pl,int pr, int il, int ir){
        //递归的第一步：递归终止条件，避免死循环
        if(pl>pr || il>ir){
            return null;
        }
        int val = preorder[pl];
        int k = pos.get(val);
        int len = k - il;
        //重建根节点
        TreeNode root = new TreeNode(val);
        // 左子树
        root.left = dfs(preorder, inorder, pl+1, pl+len, il, k-1);
        // 右子树
        root.right = dfs(preorder, inorder, pl+len+1, pr, k+1, ir);
        return root;
    }
}
// @lc code=end

