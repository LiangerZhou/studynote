import java.util.Stack;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode.cn id=94 lang=java
 *
 * [94] 二叉树的中序遍历
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
 * 迭代算法：
 * 将整棵树的最左边一条链压入栈中，每次去除栈顶元素，
 * 如果它有右子树，则将右子树压入栈中
 * 
 * 递归算法：
 * 
 */
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stk = new Stack<>();
        List<Integer> res = new ArrayList<>();

        TreeNode p = root;
        while(p !=null || stk.size()>0){
            while(p != null){
                stk.push(p);
                p = p.left;
            }

            p = stk.pop();
            res.add(p.val);
            p = p.right;
        }
        return res;
    }

    // public List < Integer > inorderTraversal(TreeNode root) {
    //     List < Integer > res = new ArrayList < > ();
    //     helper(root, res);
    //     return res;
    // }

    // public void helper(TreeNode root, List < Integer > res) {
    //     if (root != null) {
    //         if (root.left != null) {
    //             helper(root.left, res);
    //         }
    //         res.add(root.val);
    //         if (root.right != null) {
    //             helper(root.right, res);
    //         }
    //     }
    // }
    
    // 作者：LeetCode
    // 链接：https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/er-cha-shu-de-zhong-xu-bian-li-by-leetcode/
    // 来源：力扣（LeetCode）
    // 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。


}
// @lc code=end

