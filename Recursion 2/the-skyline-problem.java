// https://leetcode.com/problems/the-skyline-problem/
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        
        List<List<Integer>> edges = new ArrayList<List<Integer>>();
        List<List<Integer>> solution = new ArrayList<List<Integer>>();
        for ( int i = 0; i < buildings.length; i++ ) {
            // 0th position holds the X-cordinate, 1th position contains the index for the building. Add both the edges
            // of the building to the edges array
            edges.add( Arrays.asList( buildings[i][0], i ) );
            edges.add( Arrays.asList( buildings[i][1], i ) );
        }
        // sort the edges array
        Collections.sort(edges, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                if ( o1.get(0) < o2.get(0) ) {
                    return -1;
                } else if ( o1.get(0) == o2.get(0) ) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        // initialze the priority queue
        PriorityQueue<int[]> maxPriorityQueue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if ( o1[2] < o2[2] ) {
                    return 1;
                } else if ( o1[2] == o2[2] ) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });


        int curr_max_height = 0;
        int prev_max_height = 0;

        int curr_x = 0;
        int temp_count = 0;
        int temp_edge_idx = 0;
        for( int i = 0; i < edges.size(); i++ ) {
            // 1. look at all edges with the same x-coordinate
            curr_x = edges.get( i ).get( 0 );
            temp_count = 0;
            temp_edge_idx = i;
            while ( ( temp_edge_idx < edges.size() ) && ( edges.get( temp_edge_idx ).get( 0 ) == curr_x ) )
            {
                int[] edge_building = buildings[ edges.get( temp_edge_idx ).get( 1 ) ];
                // add the building to the priority queue if it's not present.
                // ( if it's the left edge, the building is not present)
                if ( curr_x == edge_building[0] ) {   // edges.get( temp_edge_idx ).get( 0 )
                    maxPriorityQueue.add( edge_building );
                }
                temp_edge_idx += 1;
                temp_count += 1;
            }

            curr_max_height = 0;
            while ( !maxPriorityQueue.isEmpty() )
            {
                if ( maxPriorityQueue.peek()[1] <= curr_x) {
                    maxPriorityQueue.remove();
                } else {
                    curr_max_height = maxPriorityQueue.peek()[2];
                    break;
                }
            }

            if ( curr_max_height != prev_max_height ) {
                solution.add( Arrays.asList( curr_x, curr_max_height ) );
                prev_max_height = curr_max_height;
            }

            i += temp_count - 1;
        }
        
        return solution;
    }
}
