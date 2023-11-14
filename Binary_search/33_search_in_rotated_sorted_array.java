class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        return binarySearch(nums, 0, nums.length - 1, target); 
    }
    private int binarySearch(int[] nums, int low, int high, int target){
       
        if(low > high)
        return -1; 
        // handle integer overflow
        int mid = low + (high - low) / 2; 
        
        if(nums[mid] == target) 
        return mid; 
        else if(nums[low] <= nums[mid]){  
           // left is smaller than mid
            if(nums[low] <= target && target < nums[mid]) 
                // target is between left most and mid
                return binarySearch(nums, low, mid - 1, target);
           else // target is between mid and right most 
       return binarySearch(nums, mid + 1, high, target);
        } else {
            //left is bigger than mid
           if(nums[mid] < target && target <= nums[high])
               // target is between mid and right most 
                return binarySearch(nums, mid + 1, high, target);
            else
            // target is between left most and mid 
       return binarySearch(nums, low, mid - 1, target);
        }
    }
}