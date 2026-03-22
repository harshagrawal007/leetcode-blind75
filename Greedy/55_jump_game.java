/**
 * LeetCode 55: Jump Game
 * 
 * PROBLEM: Determine if you can reach the last index
 * Starting at index 0, at each position i you can jump UP TO nums[i] steps
 * Example: [2,3,1,1,4] → true (jump 1 at 0 → land at 1, jump 3 at 1 → land at 4)
 * 
 * APPROACH: Backwards Greedy - track reachable position
 * PATTERN: Greedy algorithm working backwards
 * 
 * KEY INSIGHT:
 * - Work BACKWARDS from the end
 * - Track the furthest left position we can reach
 * - If we can reach index 0, answer is true
 * - At each position i: can we reach current goal from i?
 *   If nums[i] + i >= goal: YES, update goal to i
 * 
 * WHY BACKWARDS?
 * - Simpler logic: "Can I reach my current goal from here?"
 * - Don't need to track distances, just jump capabilities
 * - One forward pass after would be more complex
 */

/**
 * SOLUTION: Backwards Greedy Approach
 * Time: O(n) | Space: O(1)
 * 
 * ALGORITHM:
 * 1. Start with goal = last index
 * 2. Work backwards from nums.length - 2
 * 3. At position i: can we reach current goal from here?
 *    If nums[i] + i >= goal: YES! Update goal to i
 * 4. If goal reaches 0, return true
 * 
 * INTUITION:
 * Instead of asking "how far can I jump forward?",
 * ask "can I reach this goal from my current position?"
 */
class Solution {
    public boolean canJump(int[] nums) {
        // STEP 1: Initialize goal = last index (we want to reach here)
        int goal = nums.length - 1;
        
        // STEP 2: Work backwards from second-to-last element
        // At each position, check if we can reach our current goal
        for (int i = nums.length - 2; i >= 0; i--) {
            // Can we jump from position i to reach goal?
            // We can jump UP TO nums[i] steps from position i
            // We need to reach position 'goal'
            // Condition: i + nums[i] >= goal
            // Meaning: current position + max_jump >= goal_position
            
            if (nums[i] + i >= goal) {
                // YES! We can reach goal from position i
                // So now goal becomes i (we need to reach i instead)
                goal = i;
            }
        }
        
        // If goal reached 0, we can reach the last index!
        return goal == 0;
    }
}

/* ============================================================================
 * SIMULATION EXAMPLE
 * ============================================================================
 * Input: [2, 3, 1, 1, 4]
 * 
 * Iteration 1: i=3, nums[3]=1, goal=4
 *   1 + 3 >= 4? YES (4 >= 4) → goal = 3
 * 
 * Iteration 2: i=2, nums[2]=1, goal=3
 *   1 + 2 >= 3? YES (3 >= 3) → goal = 2
 * 
 * Iteration 3: i=1, nums[1]=3, goal=2
 *   3 + 1 >= 2? YES (4 >= 2) → goal = 1
 * 
 * Iteration 4: i=0, nums[0]=2, goal=1
 *   2 + 0 >= 1? YES (2 >= 1) → goal = 0
 * 
 * Loop ends: goal == 0? YES → return true ✓
 * ============================================================================
 */

/* ============================================================================
 * ALTERNATIVE APPROACH: Forward Greedy
 * ============================================================================
 * 
 * class Solution {
 *     public boolean canJump(int[] nums) {
 *         int maxReach = 0;  // Furthest index we can reach
 *         
 *         for (int i = 0; i < nums.length; i++) {
 *             // Can we reach this position?
 *             if (i > maxReach) return false;  // Stranded!
 *             
 *             // Update furthest we can reach
 *             maxReach = Math.max(maxReach, i + nums[i]);
 *             
 *             // Reached the end?
 *             if (maxReach >= nums.length - 1) return true;
 *         }
 *         return false;
 *     }
 * }
 * 
 * Time: O(n) | Space: O(1)
 * 
 * COMPARISON:
 * Both approaches are O(n) greedy solutions
 * Backwards slightly cleaner conceptually
 * Forwards: track max reachable distance
 * ============================================================================
 */

/* ============================================================================
 * COMPLEXITY ANALYSIS
 * ============================================================================
 * Time: O(n)
 *   - Single backwards pass through array
 *   - Each position visited once
 *   - Constant work per position
 * 
 * Space: O(1)
 *   - Only one variable 'goal'
 *   - No extra data structures
 * 
 * WHY THIS IS OPTIMAL:
 * - Must check each position at least once = O(n) lower bound
 * - This solution achieves it
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - Greedy algorithms and correctness proofs
 *    - Exchange argument for greedy choice
 * 
 * 2. Steven Skiena - "The Algorithm Design Manual"
 *    - When greedy algorithms work
 *    - Reachability problems in graphs/arrays
 * 
 * 3. Kleinberg & Tardos - "Algorithm Design"
 *    - Greedy approach to interval/sequence problems
 *    - Correctness of backwards reasoning
 * 
 * RELATED PROBLEMS TO PRACTICE:
 * - LeetCode 45 (Jump Game II) - Min jumps to reach end
 * - LeetCode 1306 (Jump Game III) - Jump to 0 with specific rules
 * - LeetCode 1345 (Jump Game IV) - Jump to equal values
 * - LeetCode 1871 (Jump Game VII) - Range jumps allowed
 * ============================================================================
 */

