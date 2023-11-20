//condition is left<=right
class Solution {
    public int minEatingSpeed(int[] piles, int H) {
        int fast = 1;
        int sum=0;
        for (int pile : piles) {
            fast = Math.max(fast, pile);
            sum+= pile;
        }
        //optimal speed will be always greater than avg 
        int slow = sum/H;
        System.out.println(slow);
        while(slow <= fast){
            int mid = slow + (fast - slow) / 2;
            int hourspent = 0;
            for(int pp : piles){
                hourspent += Math.ceil((double) pp / mid);
            }
            if( hourspent <= H) {
                fast = mid - 1;
            }
            else slow = mid + 1;
        }
        return slow;
    }
}

// here we return right condition is left<right 
class Solution {

    public int minEatingSpeed(int[] piles, int h) {
        // Initalize the left and right boundaries
        int left = 1, right = 1;
        for (int pile : piles) {
            right = Math.max(right, pile);
        }

        while (left < right) {
            // Get the middle index between left and right boundary indexes.
            // hourSpent stands for the total hour Koko spends.
            int middle = left + (right - left) / 2;
            int hourSpent = 0;

            // Iterate over the piles and calculate hourSpent.
            // We increase the hourSpent by ceil(pile / middle)
            for (int pile : piles) {
                hourSpent += Math.ceil((double) pile / middle);
            }

            // Check if middle is a workable speed, and cut the search space by half.
            if (hourSpent <= h) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }

        // Once the left and right boundaries coincide, we find the target value,
        // that is, the minimum workable eating speed.
        return right;
    }
}
