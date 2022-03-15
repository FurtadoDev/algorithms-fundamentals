"""
https://leetcode.com/explore/learn/card/recursion-i/250/principle-of-recursion/1440/
"""
class Solution:
        
    def reverseString(self, s: List[str]) -> None:
        """
        Do not return anything, modify s in-place instead.
        """
        def recurse(left_idx: int, right_idx: int):
            if left_idx < right_idx:
                temp: str = s[left_idx]
                s[left_idx] = s[right_idx]
                s[right_idx] = temp
                return recurse(left_idx+1, right_idx-1)
            else:
                return
        
        recurse(0, len(s)-1)
