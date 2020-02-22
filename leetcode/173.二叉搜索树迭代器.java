import java.util.LinkedList;
import java.util.Stack;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode.cn id=173 lang=java
 *
 * [173] 二叉搜索树迭代器
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
class BSTIterator {

    // 中序遍历
    //用栈来存储元素
    private Stack<TreeNode> stk = new Stack<TreeNode>();

    public BSTIterator(TreeNode root) {
        // 将左子树的那条左子树节点全部放入栈中
        while(root != null){
            stk.push(root);
            root = root.left;
        }
    }
    
    /** @return the next smallest number */
    public int next() {
        TreeNode p = stk.pop();
        int res = p.val;
        p = p.right;
        // 继续递归将该右子树节点的所有左子树的左子树节点放入栈中
        while(p != null){
            stk.push(p);
            p = p.left;
        }
        return res;
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stk.empty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
// @lc code=end

