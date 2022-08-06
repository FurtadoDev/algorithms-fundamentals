// https://leetcode.com/problems/robot-room-cleaner/
/**
 * // This is the robot's control interface.
 * // You should not implement it, or speculate about its implementation
 * interface Robot {
 *     // Returns true if the cell in front is open and robot moves into the cell.
 *     // Returns false if the cell in front is blocked and robot stays in the current cell.
 *     public boolean move();
 *
 *     // Robot will stay in the same cell after calling turnLeft/turnRight.
 *     // Each turn will be 90 degrees.
 *     public void turnLeft();
 *     public void turnRight();
 *
 *     // Clean the current cell.
 *     public void clean();
 * }
 */

class Solution {
    
    Robot robot;
    Set<Pair<Integer, Integer>> visited = new HashSet();
    
    public void goBack() {
        robot.turnRight();
        robot.turnRight();
        robot.move();
        robot.turnRight();
        robot.turnRight();
    }
    
    public void explore_recursive( Pair<Integer, Integer> curr_cell, int curr_d ) {
        int new_d = 0;
        Pair<Integer, Integer> new_cell = new Pair<>(0, 0); 
        
        for ( int i = 0; i < 4; i++ )
        {
            // initialize new direction and new cell base on the direction
            new_d = (curr_d + i) % 4;
            switch ( new_d ) 
            {
                case 0:
                    new_cell = new Pair(curr_cell.getKey(), curr_cell.getValue() + 1);
                    break;
                case 1:
                    new_cell = new Pair(curr_cell.getKey() + 1, curr_cell.getValue());
                    break;
                case 2:
                    new_cell = new Pair(curr_cell.getKey(), curr_cell.getValue() - 1);
                    break;
                case 3:
                    new_cell = new Pair(curr_cell.getKey() - 1, curr_cell.getValue());
                    break;
            }
            
            
            // 0. clean the cell and mark the cell as visited
            // 1. recurse explore in the new direction(if not visited and move() returns true)
            // 2. go back
            // 3. turn right
            
            robot.clean();
            visited.add( curr_cell );
            if ( !visited.contains(new_cell) && robot.move() )
            {
                explore_recursive( new_cell, new_d );
                goBack();
            }
            
            robot.turnRight();
        }        
    }
    
    
    public void cleanRoom(Robot robot) {
        this.robot = robot;
        explore_recursive( new Pair( 0,0 ), 0 );
    }
}
