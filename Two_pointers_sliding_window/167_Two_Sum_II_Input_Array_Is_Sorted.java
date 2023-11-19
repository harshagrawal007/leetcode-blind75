class Solution {
    
    public int[] twoSum(int[] numbers, int target) {   
        //no need of hashmap, the input array is already sorted     
        int left = 0;
        int right = numbers.length-1;
        while(left<right){
            int currentSum= numbers[left] + numbers[right];
            if(currentSum > target){
                right--;
            }
            if(currentSum< target){
                left++;
            }
            if(currentSum == target){
                int[] res= {left +1,right +1};
                return res;
            }
        }
       
        return null;
    }
}