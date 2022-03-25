# https://leetcode.com/problems/reverse-linked-list/
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        prev_node: ListNode = None
        curr_node: ListNode = head
        while(curr_node != None):
            temp_node = curr_node.next
            curr_node.next = prev_node
            prev_node = curr_node
            curr_node = temp_node 
        return prev_node
# Todo : Recursive Approach
