// https://leetcode.com/problems/largest-rectangle-in-histogram/description/
class Solution {
    
    int heights[];
    int heights_length;
    int st[];
    
    public int range_min_query( int input_l_idx, int input_r_idx,
                                int curr_seg_range_l_idx, int curr_seg_range_r_idx,
                                int seg_tree_idx ) {

        int left_idx = 0;
        int right_idx = 0;
        int min_idx = 0;

        if ( input_l_idx > curr_seg_range_r_idx || input_r_idx <  curr_seg_range_l_idx ) {
            // out of range
            return -1;
        } else if( input_l_idx <= curr_seg_range_l_idx && input_r_idx >= curr_seg_range_r_idx ) {
            // if the query segment covers the entire segment tree segment represented
            // by curr_seg_range_l_idx & curr_seg_range_r_idx
            return st[seg_tree_idx];
        } else {

            int mid_segment = Math.floorDiv( curr_seg_range_l_idx + curr_seg_range_r_idx, 2 );

            left_idx = range_min_query( input_l_idx, input_r_idx, curr_seg_range_l_idx, mid_segment,
                            ( 2 * seg_tree_idx ) + 1 );
            right_idx = range_min_query( input_l_idx, input_r_idx, mid_segment+1, curr_seg_range_r_idx,
                            ( 2 * seg_tree_idx ) + 2 );

            if ( left_idx == -1 && right_idx == -1 ) {

                min_idx = -1;

            }  else if ( left_idx == -1 ) {

                min_idx = right_idx;

            } else if ( right_idx == -1 ) {

                min_idx = left_idx;

            } else {

                if ( heights[ left_idx ] <= heights[ right_idx ] ) {

                    min_idx = left_idx;

                } else {

                    min_idx = right_idx;

                }
            }
        }

        return min_idx;
    }

    
    // this segment tree contains the indexes of the min value instead of the min values
    public void construct_segment_tree_recursive_helper( int l_idx, int r_idx, int st_curr_idx ) {

        int mid_idx = 0;
        int st_left_child = 0;
        int st_right_child = 0;

        if ( l_idx == r_idx ) {
            st[st_curr_idx] = l_idx;
        } else {
            mid_idx = Math.floorDiv( l_idx + r_idx, 2 );
            st_left_child = ( 2 * st_curr_idx ) + 1;
            st_right_child = ( 2 * st_curr_idx ) + 2;
            construct_segment_tree_recursive_helper( l_idx, mid_idx, st_left_child);
            construct_segment_tree_recursive_helper( mid_idx + 1, r_idx, st_right_child);
            if ( heights[ st[st_left_child] ] <= heights[ st[st_right_child] ] ) {
                st[st_curr_idx] = st[st_left_child];
            } else {
                st[st_curr_idx] = st[st_right_child];
            }
        }
        return;
    }


    public int largestRectangleArea_recursive_helper( int l_idx, int r_idx ) {

        int max_area = 0;
        int min_idx = 0;
        int max_left = 0;
        int max_right = 0;
        int max_across = 0;

        if ( l_idx == r_idx ) {
            max_area = heights[l_idx];
        } else {
            min_idx = range_min_query( l_idx, r_idx, 0,
                    heights_length-1, 0 );

            if ( min_idx == l_idx ) {
                max_left = heights[min_idx] * ( ((r_idx+1) - (l_idx+1)) + 1);
                max_right = largestRectangleArea_recursive_helper( min_idx+1, r_idx );
                max_area = Math.max( max_left, max_right );
            } else if ( min_idx == r_idx ){
                max_left = largestRectangleArea_recursive_helper( l_idx, r_idx-1 );
                max_right = heights[min_idx] * ( ((r_idx+1) - (l_idx+1)) + 1);
                max_area = Math.max( max_left, max_right );
            } else {
                max_left = largestRectangleArea_recursive_helper( l_idx, min_idx-1 );
                max_right = largestRectangleArea_recursive_helper( min_idx+1, r_idx );
                max_across = heights[min_idx] * ( ((r_idx+1) - (l_idx+1)) + 1);
                max_area = Math.max( max_left, max_right );
                max_area = Math.max( max_area, max_across );
            }
        }

        return max_area;
    }
    
    
    public int largestRectangleArea(int[] heights) {
        
        int n = heights.length; // size of the input array
        int num_internal_nodes = n - 1; // total number of internal nodes in the segment tree
        int h = (int) ( Math.ceil(Math.log(n) / Math.log(2)) ); // height of the segment tree
        int num_total_nodes = 1 + ( 2*( ((int) Math.pow(2, h)) - 1 ) ); // total number of nodes in the segment tree
        st = new int[num_total_nodes]; // array to hold the segment tree

        this.heights = heights;
        this.heights_length = n;
        construct_segment_tree_recursive_helper(0, n-1, 0);

        return largestRectangleArea_recursive_helper(0, n-1);
        
    }
}
