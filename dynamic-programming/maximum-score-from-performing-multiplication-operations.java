// https://leetcode.com/problems/maximum-score-from-performing-multiplication-operations/description/
class Solution {

    public int recurse(int left, int op, int[] nums, int[] multipliers, Integer[][] memo) {
        
        // base condition
        int n = nums.length;
        int right = (n - 1) - (op-left);
        int result = 0;

        if (memo[left][op] != null) {
            return memo[left][op];
        }

        if (op < multipliers.length && left < n) {
            if (left < right) {
                int leftSoln = (nums[left] * multipliers[op]) + recurse(left+1, op+1, nums, multipliers, memo);
                int rightSoln = (nums[right] * multipliers[op]) + recurse(left, op+1, nums, multipliers, memo);
                result = Math.max(leftSoln, rightSoln);
                memo[left][op] = result;
            } else if (left == right) {
                result = (nums[left] * multipliers[op]);
                memo[left][op] = result;
            } else {
                result = 0;
            }
        }
        
        return result;
    }

    public int maximumScore(int[] nums, int[] multipliers) {
        // todo :: initialize memo 
        Integer[][] memo = new Integer[nums.length+1][multipliers.length+1];
        return recurse(0, 0, nums, multipliers, memo);
    }

}
