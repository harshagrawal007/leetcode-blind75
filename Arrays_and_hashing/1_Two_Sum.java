/**
 * LeetCode 1: Two Sum
 * 
 * PROBLEM: Find two numbers in array that add up to target
 * Return indices of the two numbers (0-indexed)
 * 
 * CONSTRAINTS:
 * - Each input has exactly one solution
 * - Cannot use same element twice
 * - Return order doesn't matter
 * 
 * APPROACH: Hash Map (One-pass)
 * PATTERN: Hash Map for complement lookup
 * 
 * KEY INSIGHT:
 * While iterating, ask: "Does the complement (target - current) exist in map?"
 * If yes, we found our pair! If no, remember current number for future lookups
 * 
 * WHY THIS WORKS:
 * - We need to find nums[i] + nums[j] = target
 * - Rearrange: nums[j] = target - nums[i]
 * - So for each nums[i], check if (target - nums[i]) was seen before
 */

/**
 * SOLUTION 1: Hash Map (Optimal One-Pass)
 * 
 * Complexity: Time O(n) | Space O(n)
 * - Time: Single pass through array, each HashMap operation is O(1) average
 * - Space: HashMap stores at most n elements
 * 
 * Why O(n) time is optimal:
 * - We must examine each element at least once = O(n) lower bound
 * - This solution achieves that bound
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // STRATEGY: Store seen numbers as we go
        // For each number, check if we've seen its complement before
        Map<Integer, Integer> map = new HashMap<>();  // num → index
        
        for (int i = 0; i < nums.length; i++) {
            // What number do we need to reach target?
            int complement = target - nums[i];
            
            // Have we seen the complement before?
            if (map.containsKey(complement)) {
                // Found it! Return both indices
                return new int[] { map.get(complement), i };
            }
            
            // Haven't seen complement yet, remember current number
            map.put(nums[i], i);
        }
        
        // No solution found (shouldn't happen per constraints)
        return null;
    }
}

/**
 * SOLUTION 2: Hash Set (Two-Pass - Less Optimal)
 * 
 * Complexity: Time O(n) | Space O(n)
 * - Time: Two passes through array
 * - Space: HashSet stores at most n elements
 * 
 * DRAWBACK: Requires two passes (less efficient than Solution 1)
 * - First pass: Find if complement exists
 * - Second pass: Find indices
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        // PASS 1: Find if complement exists
        HashSet<Integer> set = new HashSet<>();
        int i;
        
        for (i = 0; i < nums.length; i++) {
            // Check if complement is already in set
            if (set.contains(target - nums[i])) {
                break;  // Found! Exit at index i
            }
            set.add(nums[i]);
        }

        // PASS 2: Find the indices
        for (int j = 0; j < i; j++) {
            // Find which earlier index has the complement
            if (nums[j] == target - nums[i]) {
                return new int[] { j, i };
            }
        }
        
        return new int[2];  // No solution
    }
}

/* ============================================================================
 * COMPLEXITY COMPARISON
 * ============================================================================
 * Solution 1 (Hash Map - One Pass):
 *   Time: O(n)  | Space: O(n)  | Passes: 1
 *   BEST APPROACH - Recommended
 * 
 * Solution 2 (Hash Set - Two Pass):
 *   Time: O(n)  | Space: O(n)  | Passes: 2
 *   Conceptually simpler but less efficient in practice
 * 
 * Brute Force (not shown):
 *   Time: O(n²) | Space: O(1)  | Passes: 1
 *   Try every pair - only acceptable for very small arrays
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Donald Knuth - "The Art of Computer Programming, Vol. 3: Searching & Sorting"
 *    - Hash table fundamentals and collision resolution
 *    - Theoretical foundation for O(1) average case lookup
 * 
 * 2. Thomas Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - Hash table analysis and proof of O(n) average time
 *    - Complement search pattern in symbol tables
 * 
 * 3. LeetCode Problem Discussions & Analysis
 *    - Industry standard solution for interview preparation
 *    - Pattern: "For every X, check if Y exists" → use HashMap
 * 
 * RELATED PROBLEMS TO PRACTICE (Similar Patterns):
 * - LeetCode 15 (3Sum) - Extend to three numbers
 * - LeetCode 16 (3Sum Closest) - Find trio closest to target
 * - LeetCode 167 (Two Sum II - Sorted Array) - Use two pointers instead
 * - LeetCode 170 (Two Sum III - Store in DS) - Design with add/find
 * - LeetCode 653 (Two Sum IV in BST) - Tree version
 * ============================================================================
 */
