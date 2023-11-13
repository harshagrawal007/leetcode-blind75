class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) {
            // should be 3 integers that will sumo to 0
            return result;
        }
        if (nums[0] > 0) {
            //not negative integers that means sum cant be 0
            return result;
        }
        for (int i =0 ;i<nums.length; i++){
            if (nums[i] > 0) { 
                // if current is greater than 0 rest are going to greater than zero
                break;
            }
                
            if (i> 0 && nums[i] == nums[i-1]) { 
                continue;
            }

            int left = i+1; // element right of current element
            int right = nums.length -1; // element at the end of the array
            while(left < right ){
                int threeSum = nums [i] + nums[left] + nums[right];
                // 3sum should be 0, if its greater than 0 then right most is too big
                if (threeSum > 0) { 
                    right--;
                }
                // 3sum should be 0, if its less than 0 then left most is too small
                else if (threeSum <0) {
                    left ++; 
                }
                else {
                    //other wise add to the triplet
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(nums[i]);
                    triplet.add(nums[left]);
                    triplet.add(nums[right]);
                    result.add(triplet);     
                    int lastLowOccurrence = nums[left];
                    int lastHighOccurrence = nums[right];

                    while (left < right && nums[left] == lastLowOccurrence) {
                        //if duplicate 
                        left++;
                    }
                    while (left < right && nums[right] == lastHighOccurrence) {
                        right--;
                    }
                }
            }    
        }
        return result;
    }
}