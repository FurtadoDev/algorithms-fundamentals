# https://leetcode.com/explore/learn/card/recursion-i/250/principle-of-recursion/1681/
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def swapPairs(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if(head is None or head.next is None):
            return
        else:
            left_node: ListNode = head
            right_node: ListNode = head.next
            left_node.next = self.swapPairs(right_node.next)
            right_node.next = left_node
            return right_node
