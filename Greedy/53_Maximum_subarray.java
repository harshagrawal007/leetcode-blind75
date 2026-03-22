/**
 * LeetCode 53: Maximum Subarray (Kadane's Algorithm)
 * 
 * PROBLEM: Find contiguous subarray with largest sum
 * Example: [-2,1,-3,4,-1,2,1,-5,4] → [4,-1,2,1] = 6
 * 
 * APPROACH: Dynamic Programming / Greedy (Kadane's Algorithm)
 * PATTERN: Maintain current max ending at position, track global max
 * 
 * KEY INSIGHT (Kadane's Greedy Choice):
 * At each position, decide:
 * - Continue existing subarray: sum + nums[i]
 * - Start fresh from current: nums[i]
 * - Choose whichever is larger
 * This greedy choice is always optimal!
 * 
 * INTUITION:
 * Why reset? If current sum becomes negative, it's better to start fresh
 * Example: [-2, -3, 4]
 * - At 4: Should we include -2,-3? No! -2-3+4=-1 < 4
 * - Best to start fresh at 4
 */

/**
 * SOLUTION: Kadane's Algorithm
 * Time: O(n) | Space: O(1)
 * 
 * KEY VARIABLES:
 * - sum: max ending at current position
 * - max: global maximum found so far
 * 
 * ALGORITHM:
 * For each element:
 * 1. Decide: include in previous subarray or start fresh?
 *    sum = max(sum + nums[i], nums[i])
 * 2. Update global max if current sum is better
 *    max = max(max, sum)
 * 
 * WHY O(n) and O(1)?
 * - Single pass through array = O(n)
 * - Only two variables needed = O(1) space
 * - Optimal for this problem
 */
class Solution {
    public int maxSubArray(int[] nums) {
        // EDGE CASE: Single element
        if (nums.length == 1) {
            return nums[0];
        }

        // INITIALIZATION: Start with first element
        int sum = nums[0];  // Max sum ending at current position
        int max = nums[0];  // Global maximum sum found so far
        
        // ITERATE: Process remaining elements
        for (int i = 1; i < nums.length; i++) {
            // KADANE'S CHOICE: Continue subarray or restart?
            // If adding current element to previous sum is negative,
            // better to start fresh from current element
            sum = Math.max(sum + nums[i], nums[i]);
            
            // UPDATE GLOBAL MAX if current sum is better
            max = Math.max(sum, max);
        }
        
        return max;
    }
}

/* ============================================================================
 * SIMULATION EXAMPLE
 * ============================================================================
 * Input: [-2, 1, -3, 4, -1, 2, 1, -5, 4]
 * 
 * i=0: sum=-2, max=-2
 * i=1: sum=max(-2+1, 1)=1, max=max(-2,1)=1
 * i=2: sum=max(1-3, -3)=-2, max=max(1,-2)=1
 * i=3: sum=max(-2+4, 4)=4, max=max(1,4)=4
 * i=4: sum=max(4-1, -1)=3, max=max(4,3)=4
 * i=5: sum=max(3+2, 2)=5, max=max(4,5)=5
 * i=6: sum=max(5+1, 1)=6, max=max(5,6)=6
 * i=7: sum=max(6-5, -5)=1, max=max(6,1)=6
 * i=8: sum=max(1+4, 4)=5, max=max(6,5)=6
 * 
 * Result: 6 ✓ (subarray [4,-1,2,1])
 * ============================================================================
 */

/* ============================================================================
 * COMPLEXITY ANALYSIS
 * ============================================================================
 * Time: O(n)
 *   - Single pass through array
 *   - Constant work per element
 *   - No division, no recursion, no sorting
 * 
 * Space: O(1)
 *   - Only two variables (sum, max)
 *   - No additional data structures
 *   - Best possible space for this problem
 * 
 * WHY THIS IS OPTIMAL:
 * - Must examine every element = O(n) lower bound
 * - This achieves it with minimal space
 * - Literally the best algorithm for this problem
 * ============================================================================
 */

/* ============================================================================
 * WHY KADANE'S IS CORRECT (Greedy Choice Property)
 * ============================================================================
 * 
 * CORRECTNESS CLAIM:
 * At each position, choosing max(sum+nums[i], nums[i]) is always optimal
 * 
 * PROOF INTUITION:
 * Consider optimal solution that doesn't include position i
 * vs one that starts at i
 * 
 * If we're at position i with previous sum, we either:
 * 1. Include previous: contributes sum value
 * 2. Restart here: contributes 0 from previous
 * 
 * If sum < 0 at position i:
 * - Including it makes future elements worse
 * - Restarting is better
 * If sum >= 0:
 * - Including it makes future elements better or equal
 * - Keep it
 * 
 * This greedy choice leads to global optimum!
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Jon Bentley - "Programming Pearls"
 *    - Original problem and Kadane's algorithm
 *    - Elegant DP solution design
 * 
 * 2. Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - Dynamic Programming theory
 *    - Optimal substructure proofs
 * 
 * 3. Steven Skiena - "The Algorithm Design Manual"
 *    - Maximum subarray as canonical DP problem
 *    - How to recognize subproblems
 * 
 * RELATED PROBLEMS TO PRACTICE:
 * - LeetCode 152 (Maximum Product Subarray) - Product version
 * - LeetCode 121 (Best Time to Buy Stock) - Simplified variant
 * - LeetCode 918 (Maximum Circular Subarray) - Circular variant
 * - LeetCode 1143 (Longest Common Subsequence) - Related DP concept
 * ============================================================================
 */