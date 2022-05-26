# https://leetcode.com/problems/sort-an-array/
class Solution(object):
    def sortArray(self, nums):
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        result_list = []
        n = len(nums)
        if n == 1:
            result_list = nums
        else:
            mid = int(len(nums)/2)
            left_list = self.sortArray(nums[0:mid])
            right_list = self.sortArray(nums[mid:n])
            # merge the two arrays
            left_list_length = len(left_list)
            right_list_length = len(right_list)
            l_idx = 0
            r_idx = 0
            merged = False
            while not merged:
                if l_idx < left_list_length and r_idx < right_list_length: #both didn't reach the end
                    if left_list[l_idx] <= right_list[r_idx]:
                        result_list.append(left_list[l_idx])
                        l_idx = l_idx + 1
                    else:
                        result_list.append(right_list[r_idx])
                        r_idx = r_idx + 1
                elif l_idx < left_list_length and not r_idx < right_list_length: #right reached the end
                    for i in range(l_idx, left_list_length):
                        result_list.append(left_list[i])
                    merged = True
                
                else: #left reached the end and right did not
                    for i in range(r_idx, right_list_length):
                        result_list.append(right_list[i])
                    merged = True
        return result_list
