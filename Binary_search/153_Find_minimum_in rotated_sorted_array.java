/**
 * LeetCode 153: Find Minimum in Rotated Sorted Array
 * 
 * PROBLEM: Find minimum element in rotated sorted array (unique elements)
 * Example: [3,4,5,1,2] → 1
 * 
 * PROPERTY: Array was originally sorted [1,2,3,4,5], then rotated at some point
 * 
 * APPROACH: Binary Search (Not Linear Scan!)
 * Pattern: Modified binary search to handle rotation
 * 
 * KEY INSIGHT:
 * - Minimum is always at the rotation point
 * - One time complexity: O(log n)
 * - Compare mid with boundaries to find which half has rotation point
 */

/**
 * ORIGINAL SOLUTION (Given) - INEFFICIENT!
 * Time: O(n) worst case - defeats purpose of binary search!
 * The loops in worst case examine most elements
 * Problem: Uses linear scan within binary search framework
 */
class SolutionInefficient {
    public int findMin(int[] nums) {
        if (nums.length == 1) return nums[0];
        
        int len = nums.length;
        int mid = nums[len / 2];  // Just checking middle, not efficient
        
        // If left half is in order (no rotation there)
        if (mid > nums[0]) {
            // Linear scan right half for rotation point!
            for (int i = len / 2 + 1; i < len; i++) {
                if (nums[i] < nums[i - 1]) return nums[i];  // O(n) !!!
            }
            return nums[0];
        } else {
            // Linear scan left half for rotation point!
            for (int i = 0; i < len / 2; i++) {
                if (nums[i + 1] < nums[i]) return nums[i + 1];  // O(n) !!!
            }
            return nums[len / 2];
        }
    }
}

/**
 * OPTIMAL SOLUTION: True Binary Search
 * Time: O(log n) | Space: O(1)
 * 
 * ALGORITHM:
 * At each step, identify which half is sorted (not rotated)
 * - Left half sorted if: nums[low] < nums[mid]
 * - Right half sorted if: nums[mid] < nums[high]
 * - Minimum must be on boundary of the non-sorted half
 * - Narrow search to that half
 * 
 * ELEGANT INSIGHT:
 * If left is sorted: min is either nums[low] or in right half
 *   → If nums[mid] < nums[low]: rotate in right, move right
 *   → Else: min is nums[low], move left
 * 
 * If right is sorted: min must be on left
 *   → Move left
 */
class Solution {
    public int findMin(int[] nums) {
        int low = 0;
        int high = nums.length - 1;
        
        // Binary search: narrow down to minimum
        while (low < high) {
            int mid = low + (high - low) / 2;
            
            // KEY DECISION: Which half is sorted?
            if (nums[mid] > nums[high]) {
                // Left half is sorted, right half has rotation point
                // Minimum must be in unsorted right half
                low = mid + 1;
            } else {
                // Right half is sorted (or equal)
                // Minimum is somewhere in left or at mid
                // Move high to mid to keep it in search space
                high = mid;
            }
        }
        
        // low and high converge to minimum
        return nums[low];
    }
}

/* ============================================================================
 * EXAMPLE WALKTHROUGH
 * ============================================================================
 * Input: [3, 4, 5, 1, 2]
 * Find minimum = 1
 * 
 * Iteration 1: low=0, high=4, mid=2
 *   nums[2]=5, nums[4]=2
 *   5 > 2? YES → rotation in right half
 *   low = 3
 * 
 * Iteration 2: low=3, high=4, mid=3
 *   nums[3]=1, nums[4]=2
 *   1 > 2? NO → right half is sorted
 *   high = 3
 * 
 * Loop ends: low == high == 3
 * Return nums[3] = 1 ✓
 * ============================================================================
 */

/* ============================================================================
 * COMPLEXITY COMPARISON
 * ============================================================================
 * Given Solution (InefficientSolution):
 *   Time: O(n) in worst case - contains linear scans!
 *   Space: O(1)
 *   Problem: Defeats purpose of binary search
 * 
 * Optimal Solution:
 *   Time: O(log n) guaranteed
 *   Space: O(1)
 *   Achieves true binary search complexity
 * 
 * PERFORMANCE DIFFERENCE:
 * - n = 1,000,000
 * - Inefficient: ~1M operations
 * - Optimal: ~20 operations
 * ============================================================================
 */

/* ============================================================================
 * KEY INSIGHTS FOR ROTATED ARRAY PROBLEMS
 * ============================================================================
 * 
 * 1. ALWAYS identify which half is sorted
 *    (A half is "sorted" if nums[low] < nums[high])
 * 
 * 2. Minimum in rotated array is at rotation boundary
 *    (Where nums[i] > nums[i+1])
 * 
 * 3. Rotation boundary is always in the UNSORTED half
 *    (Sorted half has min at its left end)
 * 
 * 4. Use comparison with nums[high] not nums[mid]
 *    (This clearly separates sorted vs rotated)
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - Binary search variants and modifications
 *    - Correctness proofs for modified binary search
 * 
 * 2. Jon Lewis - "Algorithmic Thinking"
 *    - Rotated array analysis
 *    - How to adapt binary search for variations
 * 
 * 3. LeetCode Editorial Solutions
 *    - Standard binary search approach for rotated arrays
 *    - Discussion of edge cases and optimizations
 * 
 * RELATED PROBLEMS:
 * - LeetCode 33 (Search in Rotated Array) - Search for value
 * - LeetCode 154 (Find Minimum II) - With duplicates allowed
 * - LeetCode 162 (Find Peak Element) - Similar rotation logic
 * ============================================================================
 */