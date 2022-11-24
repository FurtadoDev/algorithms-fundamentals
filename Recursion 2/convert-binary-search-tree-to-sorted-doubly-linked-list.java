// https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/
class Solution {

    Node first = null;
    Node last = null;

    public void dfs(Node curr_node) {

        if (curr_node != null) {
             dfs(curr_node.left);
            if (last != null) {
                curr_node.left = last;
                last.right = curr_node;
            } else {
                first = curr_node;
            }

            last = curr_node;
            dfs(curr_node.right);
        }
        
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) return root;
        
        dfs(root);
        first.left = last;
        last.right = first;

        return first;
    }
}
