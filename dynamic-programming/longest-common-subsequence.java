// https://leetcode.com/problems/longest-common-subsequence/description/
class Solution {

    int text1MaxIndex;
    int text2MaxIndex;
    String text1;
    String text2;
    Integer[][] memo;

    public boolean solnExists(int t1Idx, int t2Idx) {
        if(memo[t1Idx][t2Idx] != -1) {
            return true;
        }
        return false;
    }


    public int recurse(int t1CurrIdx, int t2CurrIdx) {
        
        if (t1CurrIdx >  text1MaxIndex || t2CurrIdx > text2MaxIndex) {
            return 0;
        }

        char a = text1.charAt(t1CurrIdx);
        char b = text2.charAt(t2CurrIdx);

        if (a == b) {
            int soln = 0;
            if (solnExists(t1CurrIdx+1, t2CurrIdx+1)) {
                soln = memo[t1CurrIdx+1][t2CurrIdx+1];
            } else {
                soln = recurse(t1CurrIdx+1, t2CurrIdx+1);
                memo[t1CurrIdx+1][t2CurrIdx+1] = soln;
            }
            return 1 + soln;
        } else {
            int leftSoln = 0;
            int rightSoln = 0;
            if (solnExists(t1CurrIdx+1, t2CurrIdx)) {
                leftSoln = memo[t1CurrIdx+1][t2CurrIdx];
            } else {
                leftSoln = recurse(t1CurrIdx+1, t2CurrIdx);
                memo[t1CurrIdx+1][t2CurrIdx] = leftSoln;
            }
            
            
            if (solnExists(t1CurrIdx, t2CurrIdx+1)) {
                rightSoln = memo[t1CurrIdx][t2CurrIdx+1];
            } else {
                rightSoln = recurse(t1CurrIdx, t2CurrIdx+1);
                memo[t1CurrIdx][t2CurrIdx+1] = rightSoln;
            }
            
            return Math.max(leftSoln, rightSoln);
        }
    }


    public int longestCommonSubsequence(String text1, String text2) {
        
        int len1 = text1.length();
        int len2 = text2.length();
        this.text1MaxIndex = len1 - 1;
        this.text2MaxIndex = len2 - 1;
        this.text1 = text1;
        this.text2 = text2;
        memo = new Integer[len1+1][len2+1];
        // todo :: prefill memo with -1
        for(int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                memo[i][j] = -1;
            }
        }

        if (len1 == 0 || len2 == 0) {
            return 0;
        } else {
            return recurse(0, 0);
        }
    }
}
