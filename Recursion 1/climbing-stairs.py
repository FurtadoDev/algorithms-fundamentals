# https://leetcode.com/problems/climbing-stairs/
class Solution(object):
    def recurse(self, n, dictionary):
        if n < 0:
            return 0
        else:
            if n not in dictionary:
                dictionary[n] = self.recurse(n - 1, dictionary) + self.recurse(n - 2, dictionary)
            
            return dictionary[n]
    
    def climbStairs(self, n):
        dictionary = {}
        dictionary[0] = 1
        return self.recurse(n, dictionary)
