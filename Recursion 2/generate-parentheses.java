// https://leetcode.com/problems/generate-parentheses/
class Solution {
    
    List<String> helper_string = new ArrayList<String>(Arrays.asList("(", ")"));
    List<String> solution = new ArrayList<String>();
    int n = 0;
    
    
    // Note : backtrack after every recursive call to bring the curr_str back to initial assignment
    public void recurse(int count_open, int count_close, StringBuilder curr_str) 
    {
        
        if ( count_close >= count_open ) // base case : if close brackets are lesser then the str is invalid. This means, more than required number of close brackets were used.
        {
            if ( ( count_open == 0 ) && ( count_close == 0 ) ) 
            {
                solution.add( curr_str.toString() );
            } 
            else if ( ( count_open == 0 ) && ( count_close != 0 ) ) 
            {
                curr_str.append( ")" );
                recurse( count_open, count_close - 1, curr_str);
                // backtrack
                curr_str.deleteCharAt( curr_str.length() - 1 );
            } 
            else if ( ( count_open != 0 ) && ( count_close == 0 ) ) 
            {
                // won't ever get here because of base case
                
            } 
            else // both are not zero
            {
                // choice 1
                curr_str.append( "(" );
                recurse( count_open - 1, count_close, curr_str );
                // backtrack
                curr_str.deleteCharAt( curr_str.length() - 1 );
                
                // choice 2
                curr_str.append( ")" );
                recurse( count_open, count_close - 1, curr_str );
                // backtrack
                curr_str.deleteCharAt( curr_str.length() - 1 );
            }
            
        }
        
        return;
    }
    
    public List<String> generateParenthesis(int n) {
        
        this.n = n;
        recurse( n, n, new StringBuilder() );
        return solution;
        
    }
}
