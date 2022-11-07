// https://leetcode.com/problems/letter-combinations-of-a-phone-number/
class Solution {
    
    List<String> solution;
    HashMap<Character, String> key_map;
    String input_digits;
    int input_digits_max_idx;
    
    
    public void recurse( String curr_string, int curr_digit_idx ) {
        
        if ( curr_digit_idx > input_digits_max_idx ) {
            solution.add(curr_string);
        } else {
            String characters = key_map.get( input_digits.charAt(curr_digit_idx) );
            for ( int i = 0; i < characters.length(); i++ ) {
                String new_string = curr_string.concat( Character.toString(characters.charAt(i)) );
                recurse( new_string, curr_digit_idx + 1 );
                // Since we are creating a new string everytime it is equivalent to backtracking(curr_string remains the same)
            }
        }
        
    }
    
    // use backgtracking approach
    public List<String> letterCombinations(String digits) {
        
        // initialize all variables
        input_digits = digits;
        input_digits_max_idx = digits.length() - 1;
        key_map = new HashMap<Character, String>();
        key_map.put('2', "abc");
        key_map.put('3', "def");
        key_map.put('4', "ghi");
        key_map.put('5', "jkl");
        key_map.put('6', "mno");
        key_map.put('7', "pqrs");
        key_map.put('8', "tuv");
        key_map.put('9', "wxyz");
        solution = new ArrayList<String>();
        
        // call recursive function
        if ( digits.length() > 0 )
            recurse( new String(""), 0 );
        
        return this.solution;
    }
}
