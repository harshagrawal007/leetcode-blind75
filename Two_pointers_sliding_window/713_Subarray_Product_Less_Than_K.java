
/**
 * LeetCode 713: Subarray Product Less Than K
 *
 * PROBLEM: Count subarrays where product of elements < k
 * Example: nums=[10,5,2,6], k=100 → 8 subarrays
 *          Valid: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6]
 *          Invalid: [10,5,2]=100, [10,5,2,6]=600, etc.
 *
 * APPROACH: Sliding Window (Two Pointers)
 * PATTERN: Maintain window where product < k, count valid subarrays
 *
 * KEY INSIGHT:
 * - Use sliding window to maintain product < k
 * - When product >= k, shrink from left until product < k
 * - Each valid window adds (right - left + 1) subarrays ending at right
 * - Window expansion: add new element, shrink if needed
 * - Count calculation: all subarrays ending at current right pointer
 */

/**
 * SOLUTION: Sliding Window (Optimal)
 * Time: O(n) | Space: O(1)
 *
 * ALGORITHM:
 * - Expand window by adding right element
 * - Shrink from left if product >= k
 * - Count all valid subarrays ending at current right
 *
 * WHY O(n): Each element is added once, removed at most once
 * WHY COUNTING WORKS: For window [left..right], all subarrays
 * ending at right are valid (since product < k)
 */
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // EDGE CASE: k <= 1, impossible to have product < k
        // Since all elements >= 1, product is always >= 1
        if (k <= 1) return 0;

        // SLIDING WINDOW VARIABLES
        long prod = 1;        // Current window product (use long to avoid overflow)
        int left = 0;         // Left pointer of window
        int count = 0;        // Total valid subarrays

        // EXPAND WINDOW: Move right pointer through entire array
        for (int right = 0; right < nums.length; right++) {
            // Add current element to window product
            prod *= nums[right];

            // SHRINK WINDOW: Remove elements from left until product < k
            // This ensures current window [left..right] has product < k
            while (prod >= k && left <= right) {
                prod /= nums[left];  // Remove left element
                left++;              // Move left pointer right
            }

            // COUNT VALID SUBARRAYS: All subarrays ending at 'right'
            // Window [left..right] has product < k
            // All subarrays within this window ending at right are valid
            // Number of such subarrays: (right - left + 1)
            count += right - left + 1;
        }

        return count;
    }
}

/**
 * EXAMPLE WALKTHROUGH:
 * nums = [10, 5, 2, 6], k = 100
 *
 * right=0: prod=10, left=0, count += 1 → count=1  ([10])
 * right=1: prod=50, left=0, count += 2 → count=3  ([5], [10,5])
 * right=2: prod=100, shrink: prod/=10 → prod=10, left=1
 *          count += 2 → count=5  ([5,2], [2])
 * right=3: prod=60, left=1, count += 3 → count=8  ([5,2,6], [2,6], [6])
 *
 * Total: 8 valid subarrays
 */

/* ============================================================================
 * COMPLEXITY ANALYSIS
 * ============================================================================
 * Time: O(n) - Each element is added to window once, removed at most once
 * Space: O(1) - Only constant variables, no extra data structures
 *
 * WHY THIS WORKS:
 * - Sliding window maintains invariant: product of [left..right] < k
 * - When we add right element, we shrink left until invariant holds
 * - All subarrays ending at right within current window are valid
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. LeetCode Discussion Forum - "Sliding Window for Product"
 *    - Standard pattern for subarray product problems
 *    - Counting technique for all valid subarrays
 *
 * 2. Competitive Programming Books - "Two Pointers Technique"
 *    - Window shrinking when constraint violated
 *    - Amortized analysis for O(n) time
 *
 * 3. GeeksforGeeks - "Count subarrays with product less than k"
 *    - Detailed explanation of counting method
 *    - Edge cases and examples
 *
 * RELATED PROBLEMS TO PRACTICE (Sliding Window Pattern):
 * - LeetCode 209 (Minimum Size Subarray Sum) - Sum instead of product
 * - LeetCode 3 (Longest Substring Without Repeating) - No repeats
 * - LeetCode 76 (Minimum Window Substring) - Contains all characters
 * - LeetCode 424 (Longest Repeating Character Replacement) - Character replacement
 * ============================================================================
 */

