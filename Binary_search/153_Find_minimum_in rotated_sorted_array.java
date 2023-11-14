class Solution {
    public int findMin(int[] nums) {
        if(nums.length == 1) return nums[0];
        int len = nums.length;
        int mid = nums[len / 2];
        if(mid > nums[0]){
              // left part in order.
            for(int i = len / 2 + 1; i < len; i++){
                if(nums[i] < nums[i - 1]) return nums[i];
            }
            return nums[0];
        }else{
            for(int i = 0; i < len / 2; i++){
                if(nums[i + 1] < nums[i]) return nums[i + 1];
            }
            return nums[len / 2];
        }
    }
}