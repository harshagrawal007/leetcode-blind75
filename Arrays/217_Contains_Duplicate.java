class Solution {
    public boolean containsDuplicate(int[] nums) {
        
        HashSet<Integer> mySet= new HashSet();
 
        for (int i =0 ; i< nums.length;i++){
        if(mySet.contains(nums[i])){
            return true;
        } else{
            mySet.add(nums[i]);
            }
        }
        return false;
    }
}
