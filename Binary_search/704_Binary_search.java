class Solution {
    public int search(int[] nums, int target) {

      if(nums == null || nums.length == 0) return -1;
        return binarySearch(nums, target, 0 , nums.length-1);
        
    }

    public int binarySearch(int[] nums, int target, int left, int right ){

        if(left > right) return -1;
        int mid = left + (right-left)/2;

        if(nums[mid] == target){
            return mid;
        } else if( target > nums[mid]){
           return binarySearch(nums, target, mid+1, right);
        } else if (target < nums[mid]){
           return binarySearch(nums, target, left , mid-1);
        }
        

        return -1;

    }
}

// shorter code

class Solution {

    public int search(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;

        while (i <= j) {
            int mid = (i + j) / 2;

            if (nums[mid] == target) return mid; else if (
                nums[mid] < target
            ) i = mid + 1; else j = mid - 1;
        }

        return -1;
    }
}
