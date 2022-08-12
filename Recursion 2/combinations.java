// https://leetcode.com/problems/combinations/
class Solution {
    
    public List<List<Integer>>  solution = new ArrayList<List<Integer>>();
    int n = 0;
    
    public void recurse( List<Integer> curr_list, int k_remaining, int start_idx ) 
    {
        
        
        if ( k_remaining == 0 ) 
        {
            
            solution.add( new ArrayList<Integer>(curr_list) );
            return;
            
        } 
        else 
        {
            if ( start_idx > n && k_remaining > 0 ) 
            {
                return;
            } 
            else 
            {
                for ( int i = start_idx; i <= n; i++ ) 
                {
                    curr_list.add( i );
                    recurse( curr_list, k_remaining - 1, i + 1  );
                    curr_list.remove( Integer.valueOf(i) );
                }
                return;
            }
        }
        
    }
    
    
    public List<List<Integer>> combine(int n, int k) {
        
        this.n = n;
        List<Integer> curr_list = new ArrayList<Integer>();
        recurse( curr_list, k, 1 );
        return solution;
    }
}
