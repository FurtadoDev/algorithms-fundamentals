#https://leetcode.com/problems/unique-binary-search-trees-ii/
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def recursive_helper(self, sorted_list):
        all_trees = []
        n = len(sorted_list)
        if n == 0:
            return [None,]
        elif n == 1:
            all_trees.append(TreeNode(sorted_list[0]))
        else:
            for i in range(0, n):
                left_list = sorted_list[0:i]
                right_list = sorted_list[i+1:n]
                left_trees = self.recursive_helper(left_list)
                right_trees = self.recursive_helper(right_list)
                for l_tree in left_trees:
                    for r_tree in right_trees:
                        root = TreeNode(sorted_list[i])
                        root.left = l_tree
                        root.right = r_tree
                        all_trees.append(root)                 
        return all_trees            
            
            
    def generateTrees(self, n):
        """
        :type n: int
        :rtype: List[TreeNode]
        """
        sorted_list = list(range(1, n+1))
        return self.recursive_helper(sorted_list)
