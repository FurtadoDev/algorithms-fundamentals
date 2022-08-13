// https://leetcode.com/problems/binary-tree-inorder-traversal/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    
    public List<Integer> solution = new ArrayList<Integer>();
    
    public void recurse(TreeNode node) 
    {
        if (node != null) 
        {
            recurse(node.left);
            solution.add(node.val);
            recurse(node.right);
        }
        return;
    }
    
    public List<Integer> inorderTraversal(TreeNode root) {
        recurse(root);
        return solution;
    }
}
