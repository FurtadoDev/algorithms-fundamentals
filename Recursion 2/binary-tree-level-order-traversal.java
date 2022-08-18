// https://leetcode.com/problems/binary-tree-level-order-traversal/
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
    
    public List<List<Integer>> levelOrder(TreeNode root) {
        
        List<List<Integer>> solution = new ArrayList<List<Integer>>();
        if ( root == null ) 
        {
            return solution;
        }
        
        Queue<TreeNode> q_nodes = new LinkedList<>();
        Queue<Integer> q_levels = new LinkedList<>();
        
        // Initialization
        List<Integer> tempList = new ArrayList<Integer>();
        q_nodes.add( root );
        q_levels.add( 0 );
        int prevLevel = 0;
        int currLevel = 0;
        while( !q_nodes.isEmpty() ) 
        {
            TreeNode currNode = q_nodes.peek();
            currLevel = q_levels.peek();
            
            if ( currLevel > prevLevel ) 
            {
                solution.add( tempList );
                prevLevel = currLevel;
                tempList = new ArrayList<Integer>();
            }
            
            tempList.add( currNode.val );
            
            
            if ( currNode.left != null ) 
            {
                q_nodes.add( currNode.left );
                q_levels.add( currLevel + 1 );
            }
            
            if ( currNode.right != null ) 
            {
                q_nodes.add( currNode.right );
                q_levels.add( currLevel + 1 );
            }
            
            q_nodes.remove();
            q_levels.remove();
        }
        
        solution.add(tempList);
        
        return solution;
    }
}
