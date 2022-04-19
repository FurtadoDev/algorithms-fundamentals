# https://leetcode.com/problems/pascals-triangle-ii/
class Solution(object):
    
    def getNumber(self, r, c, dictionary):
        if r == c or c == 0:
            dictionary["("+str(r)+", "+str(c)+")"] = 1
            return 1
        else:
            left_key = "("+str(r - 1)+", "+str(c - 1)+")"
            right_key = "("+str(r - 1)+", "+str(c)+")"
            left_num = 0
            right_num = 0
            if left_key in dictionary:
                left_num = dictionary[left_key]
            else:
                dictionary[left_key] = self.getNumber(r - 1, c - 1, dictionary)
                left_num = dictionary[left_key]
            
            if right_key in dictionary:
                right_num = dictionary[right_key]
            else:
                dictionary[right_key] = self.getNumber(r - 1, c, dictionary)
                right_num = dictionary[right_key]
                
            return left_num + right_num

    def getRow(self, rowIndex):
        dict = {}
        result = []
        for colIndex in range(0, rowIndex+1):
            result.append(self.getNumber(rowIndex, colIndex, dict))
                 
        return result
