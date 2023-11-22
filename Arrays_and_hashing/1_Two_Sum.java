class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        // In case there is no solution, we'll just return null
        return null;
    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        HashSet<Integer> set = new HashSet<>();
        int i ;
        for (i= 0; i < nums.length; i++) {
            if (set.contains(target - nums[i]) ){
                break;
            }
            set.add(nums[i]);
        }

        for(int j=0;j<i;j++){
            if(nums[j] == target - nums[i]){
                return new int[]{i,j};
            }
        }
        // In case there is no solution, we'll just return null
        return new int[2];
    }
}
