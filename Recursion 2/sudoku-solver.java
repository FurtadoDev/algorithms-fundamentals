// https://leetcode.com/problems/sudoku-solver/
class Solution {
    
    int n = 3;
    int N = 3*3;
    // N+1 because the numbers go from 1 to 9, we're basically ignoring the 0th index for easier housekeeping
    int[][] rows = new int[N][N+1];
    int[][] cols = new int[N][N+1];
    int[][] boxes = new int[N][N+1];
    char[][] board = new char[N][N];

    
    public int getBoxIndex(int r_idx, int c_idx)
    {
        int idx = (n * (r_idx/n)) + (c_idx/n);
        return idx;
    }
    
    public void insertNumber(int newNumber, int r_idx, int c_idx)
    {
        board[r_idx][c_idx] = (char)(newNumber+'0');
        rows[r_idx][newNumber]++;
        cols[c_idx][newNumber]++;
        int box_idx = getBoxIndex(r_idx, c_idx);
        boxes[box_idx][newNumber]++;
    }
    
    public void deleteNumber(int oldNumber, int r_idx, int c_idx)
    {
        board[r_idx][c_idx] = '.';
        rows[r_idx][oldNumber]--;
        cols[c_idx][oldNumber]--;
        int box_idx = getBoxIndex(r_idx, c_idx);
        boxes[box_idx][oldNumber]--;
    }
    
    
    public boolean isInsertionValid(int newNum, int r_idx, int c_idx)
    {
        int box_idx = getBoxIndex(r_idx, c_idx);
        return ( (rows[r_idx][newNum] == 0) && (cols[c_idx][newNum] == 0) && (boxes[box_idx][newNum] == 0) );  
    }
    
    
    public boolean recurse(int r_idx, int c_idx)
    {
        int temp_num_to_insert = 0;
        int temp_box_idx = 0;
        
        int next_r_idx = 0;
        int next_c_idx = 0;
        if ( c_idx == N-1 ) 
        {
            
            next_r_idx = r_idx + 1;
            next_c_idx = 0;
            
        } else {
            
            next_r_idx = r_idx;
            next_c_idx = c_idx + 1;
            
        }
        
        if ( r_idx == N )
        {
            
            return true;
            
        } else {
            
            if ( board[r_idx][c_idx] != '.' )
            {
                
                return recurse( next_r_idx, next_c_idx ); //recurse to the next cell
                
            } else {
                
                for ( char c='1'; c <= '9'; c++ )
                {

                    temp_num_to_insert = c - '0';
                    
                    if ( isInsertionValid( temp_num_to_insert, r_idx, c_idx ) ) {
                        
                        insertNumber( temp_num_to_insert, r_idx, c_idx );
                        rows[r_idx][temp_num_to_insert]++;
                        cols[c_idx][temp_num_to_insert]++;
                        temp_box_idx = getBoxIndex( r_idx, c_idx );
                        boxes[temp_box_idx][temp_num_to_insert]++;
                        
                        
                        if ( recurse( next_r_idx, next_c_idx ) ) 
                        {
                            return true;
                        }    
                        else {

                            deleteNumber( temp_num_to_insert, r_idx, c_idx );
                            rows[r_idx][temp_num_to_insert]--;
                            cols[c_idx][temp_num_to_insert]--;
                            //temp_box_idx = getBoxIndex( r_idx, c_idx );
                            boxes[temp_box_idx][temp_num_to_insert]--;
                        }
                        
                    }
                }
                
                return false;
            }
            
        }
    }
    

    public void solveSudoku(char[][] board) {
        this.board = board;
        
        int temp_num = 0;
        int temp_box_idx = 0;
        int temp_r_idx = 0;
        int temp_c_idx = 0;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                if (board[i][j] != '.'){
                    temp_num = board[i][j] - '0';
                    temp_r_idx = i;
                    temp_c_idx = j;    
                    rows[temp_r_idx][temp_num]++;
                    cols[temp_c_idx][temp_num]++;
                    temp_box_idx = getBoxIndex(temp_r_idx, temp_c_idx);
                    boxes[temp_box_idx][temp_num]++;
                }
            }
        }
        
        
        recurse( 0, 0 );
    }
}
