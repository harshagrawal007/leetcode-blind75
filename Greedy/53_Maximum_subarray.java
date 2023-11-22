class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }

       int sum = nums[0];
        int max = nums[0];
        int i=1;
        while(i <nums.length){
            sum = Math.max(sum + nums[i], nums[i] );
            max =  Math.max(sum, max);
            i++;
        }
        return  max;
    }
}