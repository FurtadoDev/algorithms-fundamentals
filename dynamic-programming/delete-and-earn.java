// https://leetcode.com/problems/delete-and-earn/description/
class Solution {
    public int deleteAndEarn(int[] nums) {
        
        if (nums.length == 0) return 0;
        
        Arrays.sort(nums);
        int inputSize = nums.length;
        Map<Integer, Integer> numCount = new TreeMap<Integer, Integer>();
        for(int i=0; i<inputSize; i++) {
            if (numCount.containsKey(nums[i])) {
                int oldValue = numCount.get(nums[i]);
                numCount.replace(nums[i], oldValue+1);
            } else {
                numCount.put(nums[i], 1);
            }
        }
        
        int treeMapSize = numCount.size();
        int[] soln = new int[treeMapSize];
        int currentIdx = 0;
        int prevKey = 0;
        for (Map.Entry<Integer, Integer> entry : numCount.entrySet()) {
            //System.out.println(entry.getKey() + " => " + entry.getValue());
            int currentValue = entry.getKey() * entry.getValue();
            
            if (currentIdx == 0) { 
                soln[currentIdx] = currentValue;
            } 
            else if (currentIdx == 1) {
                
                if (entry.getKey() - prevKey == 1) {
                    soln[currentIdx] = Math.max(soln[currentIdx - 1], currentValue);
                } else {
                    soln[currentIdx] = soln[currentIdx - 1] + currentValue;
                }
                
            } else {
                if (entry.getKey() - prevKey == 1) {
                    soln[currentIdx] = Math.max(soln[currentIdx - 2] + currentValue, soln[currentIdx -1]);
                } else {
                    soln[currentIdx] = soln[currentIdx - 1] + currentValue;
                }
            }
            
            prevKey = entry.getKey();
            currentIdx++;
        }
        
        return soln[currentIdx-1];
    }
}
