# https://leetcode.com/problems/powx-n/
class Solution(object):
    def myPow(self, x, n):
        """
        :type x: float
        :type n: int
        :rtype: float
        """
        # Approach 1: Tail recursive but results in stack overflow
        # def helper(sub, sup, acc):
        #     if sup == 0:
        #         return acc
        #     else:
        #         accumulated_value = sub*acc
        #         return helper(sub, sup-1, accumulated_value)
        # return helper(x, n, 1)
        
        # Approach 2: Fast Power Algorithm Recursive
        if n < 0:
            x = 1/x
            n = -n
            
        if n == 0:
            return 1
        elif n == 1:
            return x
        else:
            if n % 2 == 0:
                temp = self.myPow(x, n/2)
                return temp * temp
            else:
                temp = self.myPow(x, (n-1)/2)
                return temp * temp * x
