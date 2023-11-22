//start from end and see if you can reach 0 index
// i.e. when start from end your goal is last index 
// then check if you can reach from second last to the last 
// for e.g if position is 3 and to reach 4 you need + 1
// so 3+1 >= 4
class Solution {
    //[2,3,1,1,4]
    public boolean canJump(int[] nums) {
        //goal =4
        int goal = nums.length - 1;
        // i =3, i<=0;i--
        for (int i = nums.length - 2; i >= 0; i--) {
            // 1+ 3 >= 4
            //goal = 3
            //2+ 1 >= 3
            // goal = 2
            //2+3 >= 2
            // goal = 2
            // 3+ 1> =2
            //goal =1
            //2+0 >=1
            //goal = 0
            if (nums[i] + i >= goal) {
                goal = i;
            }
        }
        return goal == 0;
    }
}

