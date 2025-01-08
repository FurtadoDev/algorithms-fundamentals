// https://leetcode.com/problems/maximal-square/
class Solution {
    public int maximalSquare(char[][] matrix) {

        int max_temp = 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] mem = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || j == 0) {
                    mem[i][j] = matrix[i][j] == '0' ? 0 : 1;
                } else {
                    if (mem[i-1][j-1] == 0 || mem[i-1][j] == 0 || mem[i][j-1] == 0) {
                        mem[i][j] = matrix[i][j] == '0' ? 0 : 1;
                    } else {
                        if (matrix[i][j] == '0') {
                            mem[i][j] = 0;
                        } else {
                            int left = mem[i][j-1];
                            int upper = mem[i-1][j];
                            int upper_left = mem[i-1][j-1];
                            int min = Math.min(left, upper);
                            if (min >= upper_left) {
                                mem[i][j] = upper_left + 1;
                            } else {
                                mem[i][j] = min + 1;
                            }
                        }
                    }
                }

                max_temp = Math.max(max_temp, mem[i][j]);
            }
        }

        // for (int i = 0; i < m; i++) {
        //     for (int j = 0; j < n; j++) {
        //         System.out.print(mem[i][j]+ "   ");
        //     }
        //     System.out.println("");
        // }

        return max_temp*max_temp;
    }
}
