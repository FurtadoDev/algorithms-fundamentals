# https://leetcode.com/problems/search-a-2d-matrix-ii/
# Algorithm inspired by kd-tree
class Solution(object):
    def kd_search_rec(self, matrix, target, splitAlongVertical, isMatrixValid):
        
        if not isMatrixValid:
            return False
        
        num_rows = len(matrix)
        num_cols = len(matrix[0])
        mid_col_idx = int(num_cols/2)
        mid_row_idx = int(num_rows/2)
        isLeftValid = False
        isRightValid = False
        isTopValid = False
        isBottomValid = False
        
        if num_rows == 1 and num_cols == 1:
            return target == matrix[0][0]
        
        if (splitAlongVertical == True and num_cols > 1) or (splitAlongVertical == False and num_rows==1):
            if target >= matrix[0][0] and target <= matrix[num_rows-1][mid_col_idx-1]:
                isLeftValid = True
            if target >= matrix[0][mid_col_idx] and target <= matrix[num_rows-1][num_cols-1]:
                isRightValid = True
            leftMatrix = [sublist[0:mid_col_idx] for sublist in matrix]
            rightMatrix = [sublist[mid_col_idx:num_cols] for sublist in matrix]
            return self.kd_search_rec(leftMatrix,target,False,isLeftValid) or self.kd_search_rec(rightMatrix,target,False,isRightValid)
        else:
        # if (splitAlongVertical == False and num_rows > 1) or (splitAlongVertical == True and num_cols==1):
            if target >= matrix[0][0] and target <= matrix[mid_row_idx-1][num_cols-1]:
                isTopValid = True
            if target >= matrix[mid_row_idx][0] and target <= matrix[num_rows-1][num_cols-1]:
                isBottomValid = True
            topMatrix = matrix[0:mid_row_idx]
            bottomMatrix = matrix[mid_row_idx:num_rows]
            return self.kd_search_rec(topMatrix,target,True,isTopValid) or self.kd_search_rec(bottomMatrix,target,True,isBottomValid)
            
        
        
    def searchMatrix(self, matrix, target):
        """
        :type matrix: List[List[int]]
        :type target: int
        :rtype: bool
        """
        # print(len(matrix[0]))
        # print(matrix[0:2])
        # print([sublist[3:5] for sublist in matrix])
        
        return self.kd_search_rec(matrix, target, True, True)
