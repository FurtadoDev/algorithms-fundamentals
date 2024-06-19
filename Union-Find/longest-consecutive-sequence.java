// https://leetcode.com/problems/longest-consecutive-sequence/submissions/
class Solution {
    public int longestConsecutive(int[] nums) {
        UnionFind unionFind = new UnionFind(nums);
        int n = nums.length;
        HashSet<Integer> inputElements = new HashSet<Integer>(n);

        for (int i = 0; i < n; i++) {
            int test = nums[i];
            inputElements.add(nums[i]);
            if (inputElements.contains(nums[i] - 1)) {
                unionFind.unionElement(nums[i], nums[i] - 1);
            }
            if (inputElements.contains(nums[i] + 1)) {
                unionFind.unionElement(nums[i], nums[i] + 1);
            }
        }

        HashMap<Integer, Integer> parentSizeMap = unionFind.getParentSizeMap();
        int longestSequence = 0;
        Iterator entries = parentSizeMap.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            Integer key = (Integer) entry.getKey();
            Integer value = (Integer) entry.getValue();
            if (value > longestSequence) {
                longestSequence = value;
            }
        }

        return longestSequence;
    }
    
    class UnionFind {

        // structure to element numbers to each value in the input array
        HashMap<Integer, Integer> inputElementNumberMap;

        // array to hold parent of each element number
        int[] parent;

        // structure to hold parent nodes and the size of the set containing the parent
        HashMap<Integer, Integer> parentSizeMap;


        public UnionFind(int[] inputArray) {

            int n = inputArray.length;

            parent = new int[n];

            parentSizeMap = new HashMap<Integer, Integer>();

            inputElementNumberMap = new HashMap<Integer, Integer>();

            for (int i = 0; i < n; i++) {
                inputElementNumberMap.put(inputArray[i], i);
                parentSizeMap.put(i, 1);
                parent[i] = i;
            }
        }


        // return the parent (with path compression - (only to be used internally))
        private int find(int x) {

            if (x == parent[x]) {
                // x is the root
                return x;
            }
            // path compression
            parent[x] = find(parent[x]);
            return parent[x];
        }


        public int findElement(int element) {

            int elementNumber = inputElementNumberMap.get(element);
            int parent = find(elementNumber);

            return parent;
        }


        public void unionElement(int elementLeft, int elementRight) {

            int elementLeftNumber = inputElementNumberMap.get(elementLeft);
            int elementRightNumber = inputElementNumberMap.get(elementRight);
            union(find(elementLeftNumber), find(elementRightNumber));
        }


        // merge x & y so that they are in the same set (only to be used internally)
        private void union(int left, int right) {

            int parentLeft = find(left);
            int parentRight = find(right);

            // smaller set becomes the subtree
            if (parentLeft != parentRight) {
                int sizeLeft = parentSizeMap.get(parentLeft);
                int sizeRight = parentSizeMap.get(parentRight);
                if (sizeLeft <= sizeRight) {
                    parent[left] = parentRight;
                    parentSizeMap.put(parentRight, parentSizeMap.get(parentRight) + sizeLeft);
                    parentSizeMap.remove(parentLeft);
                }
                else {
                    parent[right] = parentLeft;
                    parentSizeMap.put(parentLeft, parentSizeMap.get(parentLeft) + sizeRight);
                    parentSizeMap.remove(parentRight);
                }
            }

        }

        public HashMap<Integer, Integer> getParentSizeMap() {
            return parentSizeMap;
        }
    }
}
