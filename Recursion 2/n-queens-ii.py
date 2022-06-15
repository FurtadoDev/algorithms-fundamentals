# https://leetcode.com/problems/n-queens-ii/
class Solution(object):
    def totalNQueens(self, n):
        """
        :type n: int
        :rtype: int
        """
        def recursive_helper(curr_row, cols, diagnals, anti_diagnals):
            # Base case(goes into this case only if all the queens are exhausted since the row passed after insertion is curr_row+1. If the last queen wasn't inserted, the solution=0 would autmatically be returned. Therefore, we don't need to keep track of remaining no of queens)
            if curr_row == n:
                return 1
            
            solution = 0
            for curr_col in range(n):
                curr_diag = curr_row - curr_col
                curr_adiag = curr_row + curr_col
                if curr_col in cols or curr_diag in diagnals or curr_adiag in anti_diagnals:
                    continue
                # Insert the queen and update the attack cols, diagnals and anti diagnals
                cols.add(curr_col)
                diagnals.add(curr_diag)
                anti_diagnals.add(curr_adiag)
                # Explore further with the queen inserted
                solution += recursive_helper(curr_row+1, cols, diagnals, anti_diagnals)
                # Undo the insert and update the attack cols, diagnals and anti diagnals(equivalent to skipping and element)
                cols.remove(curr_col)
                diagnals.remove(curr_diag)
                anti_diagnals.remove(curr_adiag)
            return solution       
        return recursive_helper(0, set(), set(), set())
