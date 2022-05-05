# https://leetcode.com/problems/maximum-depth-of-binary-tree/
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def maxDepth(self, root):
        left_max_depth = 0
        right_max_depth = 0
        
        if root != None:
            left_max_depth = 1 + self.maxDepth(root.left)
            right_max_depth = 1 + self.maxDepth(root.right)

        return max(left_max_depth, right_max_depth)
