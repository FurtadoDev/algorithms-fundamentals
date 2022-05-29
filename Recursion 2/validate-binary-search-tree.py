# https://leetcode.com/problems/validate-binary-search-tree/
# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution(object):
    def recursive_helper(self, root, min, max):
        if root == None:
            return True
        elif root.val > min and root.val < max:
            return self.recursive_helper(root.left, min, root.val) and self.recursive_helper(root.right, root.val, max)
        else:
            return False

    def isValidBST(self, root):
        """
        :type root: TreeNode
        :rtype: bool
        """
        return self.recursive_helper(root, -float("inf"), float("inf"))
