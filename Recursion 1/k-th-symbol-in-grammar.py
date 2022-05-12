# https://leetcode.com/problems/k-th-symbol-in-grammar/
class Solution(object):
    def kthGrammar(self, n, k):
        """
        :type n: int
        :type k: int
        :rtype: int
        """
        if k == 1:
            return 0
        half = pow(2, n-1-1)
        if k <= half:
            return self.kthGrammar(n-1, k)
        else:
            if self.kthGrammar(n-1, k-half) == 0:
                return 1
            else:
                return 0
