// https://leetcode.com/problems/permutations/
class Solution {
    
    public int[] nums; // input
    public List<List<Integer>> soln = new ArrayList<>();
    public int input_size;

    public void recursive_helper( int[] input, int start_idx ) {
        int temp = 0;
        if ( start_idx == input_size-1 ) {
            ArrayList<Integer> row = new ArrayList<>();
            for ( int i = 0; i < input_size; i++ ) {
                row.add( input[i] );
            }
            soln.add( row );
        } else {
            for ( int i = start_idx; i < input_size; i++ ) {
                temp = input[start_idx];
                input[start_idx] = input[i];
                input[i] = temp;
                recursive_helper( input, start_idx+1 );
                // backtrack
                input[i] = input[start_idx];
                input[start_idx] = temp;
            }
        }
        return;
    }

    public List<List<Integer>> permute( int[] nums ) {
        nums = nums;
        input_size = nums.length;
        recursive_helper( nums, 0 );
        // for ( int i = 0; i < soln.size(); i++ ) {
        //     for ( int j = 0; j < input_size; j++ ) {
        //         System.out.print(soln.get(i).get(j)+" ");
        //     }
        //     System.out.println(" ");
        // }

        return soln;
    }
}
