import java.awt.List;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeMap;

import javax.swing.tree.TreeNode;

/*
 * @lc app=leetcode.cn id=297 lang=java
 *
 * [297] 二叉树的序列化与反序列化
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

 // 一般情况下：只有前序遍历，是无法确定一颗二叉树，需要前序遍历和中序遍历一起才能确定
 // 本题是因为，我们把所有空节点都表示出来了，所以可以实现

 //前序遍历 dfs
 // 1，2，3，null,null,4,null,null,5,null,null

// 代码有问题，注释了 
// public class Codec {

//     // Encodes a tree to a single string.
//     public String serialize(TreeNode root) {
//         String res = "";
//         dfs1(root,res);
//         return res;
//     }
//     private void dfs1(TreeNode root, String res){
//         if(root == null){
//             res += "#,";
//             return;
//         }
//         res += res.valueOf(root.val)+",";
//         dfs1(root.left, res);
//         dfs1(root.right, res);

//     }

//     // Decodes your encoded data to tree.
//     public TreeNode deserialize(String data) {
//         if(data.isEmpty()){
//             return null;
//         }
//         String[] data_array = data.split(",");
//         List<String> data_list = new LinkedList<>(Arrays.asList(data_array));
//         return dfs2(data_list);
//     }
//     private TreeNode dfs2(List<String> s){

//         if(s.get(0).equals("#")){
//             s.remove(0);
//             return null;
//         }
//         TreeNode root = new TreeNode(Integer.valueOf(s.get(0)));
//         s.remove(0);
//         root.left = dfs2(s);
//         root.right = dfs2(s);

//         return root;
//     }
// }




 // 层序遍历 bfs
 // 1 2 3 n n 4 5 n n n n
public class Codec {
    public String serialize(TreeNode root) {
        if (root == null) return "";
        // 队列，存放每层的节点
        Queue<TreeNode> q = new LinkedList<>();
        StringBuilder res = new StringBuilder();
        // 先将根节点放入队列
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.poll();
            if (node == null) {
                res.append("n ");
                continue;
            }
            res.append(node.val + " ");
            // 将根节点的左右子节点放入队列
            q.add(node.left);
            q.add(node.right);
        }
        return res.toString();
    }

    public TreeNode deserialize(String data) {
        if (data == "") return null;
        Queue<TreeNode> q = new LinkedList<>();
        // 将字符串输出为字符串数组
        String[] values = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        //将根节点加入队列
        q.add(root);
        // 遍历所有字符串数组元素
        for (int i = 1; i < values.length; i++) {
            // 将根节点取出
            TreeNode parent = q.poll();
            //如果第一个节点不是n
            if (!values[i].equals("n")) {
                // 将该节点赋给根节点的左节点
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                parent.left = left;
                q.add(left);
            }
            //如果第二个节点不是n
            if (!values[++i].equals("n")) {
                // 将该节点赋给根节点的右节点
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                parent.right = right;
                q.add(right);
            }
        }
        return root;
    }
}


// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
// @lc code=end

