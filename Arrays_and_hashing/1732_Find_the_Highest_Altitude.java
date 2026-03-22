/**
 * LeetCode 1732: Find the Highest Altitude
 *
 * PROBLEM: Given an array gain where gain[i] is the net change in altitude
 *          between point i and i+1, compute the highest altitude achieved.
 *
 * Example: gain = [-5, 1, 5, 0, -7] -> altitudes: [0, -5, -4, 1, 1, -6] -> max = 1
 *
 * APPROACH: Prefix sum / running sum
 * PATTERN: Maintain running total, track maximum seen so far
 *
 * KEY INSIGHT:
 * - Altitude after step i = sum(gain[0..i-1]) (starting at 0)
 * - We can compute this in one pass using a running total
 * - Track max at each step
 *
 * WHY THIS IS O(n):
 * - We iterate over gain once
 * - Each step does constant work
 */

/**
 * COMPLEXITY: Time O(n) | Space O(1)
 *
 * TIME: One pass through gain array
 * SPACE: Constant extra space (only variables, no extra arrays needed)
 *
 * NOTE: The original solution uses an extra array to store altitudes,
 * but we can keep only the current altitude and current max.
 */
class Solution {
    public int largestAltitude(int[] gain) {
        int maxAltitude = 0;  // Start at altitude 0
        int currentAltitude = 0;  // Running altitude

        for (int delta : gain) {
            currentAltitude += delta;  // Update altitude after this segment
            maxAltitude = Math.max(maxAltitude, currentAltitude);
        }

        return maxAltitude;
    }
}

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - Prefix sums and running totals
 *
 * 2. Steven Skiena - "The Algorithm Design Manual"
 *    - One-pass scan patterns
 *
 * 3. LeetCode Discussion Forums
 *    - Common optimization: avoid extra arrays for running sums
 *
 * RELATED PROBLEMS TO PRACTICE:
 * - LeetCode 122 (Best Time to Buy and Sell Stock) - running max profit
 * - LeetCode 53 (Maximum Subarray) - running sum and max tracking
 * - LeetCode 152 (Maximum Product Subarray) - running product and max tracking
 * - LeetCode 560 (Subarray Sum Equals K) - prefix sum pattern
 * ============================================================================
 */
