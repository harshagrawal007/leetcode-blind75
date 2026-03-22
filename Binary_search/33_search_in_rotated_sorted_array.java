/**
 * LeetCode 33: Search in Rotated Sorted Array
 * 
 * PROBLEM: Find target in rotated sorted array (unique elements)
 * Example: [4,5,6,7,0,1,2], target=0 → 4
 * 
 * KEY PROPERTY: Rotation point divides array into 2 sorted halves
 * Example: [4,5,6,7,0,1,2]
 *          Left half (sorted): [4,5,6,7]
 *          Right half (sorted): [0,1,2]
 * 
 * APPROACH: Binary search with rotation awareness
 * PATTERN: Identify which half is sorted, then narrow search space
 * 
 * KEY INSIGHT:
 * - At any mid point, at least ONE half is guaranteed sorted
 * - Use sorted half to determine where target is
 * - Eliminate impossible half, narrow to the other
 */

/**
 * SOLUTION: Recursive Binary Search with Rotation Handling
 * Time: O(log n) | Space: O(log n) for recursion stack
 * 
 * WHY O(log n)?
 * - Each recursive call eliminates half the search space
 * - Maximum recursion depth = log₂(n)
 * - Each operation inside: O(1)
 * 
 * KEY ALGORITHM:
 * 1. Check if mid is target → return
 * 2. Determine which half is properly sorted (not rotated)
 * 3. Check if target is in sorted half
 *    - If yes: search that half
 *    - If no: search the other half (which must contain it)
 */
class Solution {
    public int search(int[] nums, int target) {
        // VALIDATION
        if (nums == null || nums.length == 0) return -1;
        return binarySearch(nums, 0, nums.length - 1, target); 
    }
    
    /**
     * Recursive binary search for rotated array
     * @param nums rotated sorted array
     * @param low left boundary (inclusive)
     * @param high right boundary (inclusive)
     * @param target value to find
     * @return index of target, or -1 if not found
     */
    private int binarySearch(int[] nums, int low, int high, int target) {
        // BASE CASE: Search space exhausted
        if (low > high) return -1;
        
        // CALCULATE MID (avoid integer overflow)
        int mid = low + (high - low) / 2; 
        
        // CASE 1: Found target!
        if (nums[mid] == target) return mid;
        
        // CASE 2: Left half is sorted (no rotation point in left half)
        // Condition: nums[low] <= nums[mid] means left side is sorted
        // This handles cases where rotation point is in right half
        else if (nums[low] <= nums[mid]) {  
            // Left half is properly sorted: [low...mid]
            // Check if target is in sorted left half
            // Target is in left if: low <= target < mid
            if (nums[low] <= target && target < nums[mid]) {
                // Yes, target is in sorted left half
                return binarySearch(nums, low, mid - 1, target);
            } else {
                // No, target must be in right half
                return binarySearch(nums, mid + 1, high, target);
            }
        } 
        // CASE 3: Right half is sorted (rotation point is in left half)
        else {
            // Left has rotation point, right half is sorted: [mid...high]
            // Check if target is in sorted right half
            // Target is in right if: mid < target <= high
            if (nums[mid] < target && target <= nums[high]) {
                // Yes, target is in sorted right half
                return binarySearch(nums, mid + 1, high, target);
            } else {
                // No, target must be in left half
                return binarySearch(nums, low, mid - 1, target);
            }
        }
    }
}

/* ============================================================================
 * EXAMPLE WALKTHROUGH
 * ============================================================================
 * Array: [4,5,6,7,0,1,2], Target: 0
 * 
 * Call 1: low=0, high=6, mid=3
 * - nums[3]=7 ≠ target
 * - nums[0]=4 <= nums[3]=7 → LEFT is sorted
 * - Is 0 between 4 and 6? No → search RIGHT
 * - New: low=4, high=6
 * 
 * Call 2: low=4, high=6, mid=5
 * - nums[5]=1 ≠ target
 * - nums[4]=0 > nums[5]=1 → RIGHT is sorted
 * - Is 0 between 1 and 2? No → search LEFT
 * - New: low=4, high=4
 * 
 * Call 3: low=4, high=4, mid=4
 * - nums[4]=0 = target → FOUND!
 * - Return 4
 * ============================================================================
 */

/* ============================================================================
 * COMPLEXITY ANALYSIS
 * ============================================================================
 * Time: O(log n)
 *   - Each step eliminates half the remaining elements
 *   - Maximum steps: log₂(n)
 *   - Each step: O(1) comparisons
 *   - Works because at least one half is always sorted
 * 
 * Space: O(log n) due to recursion stack
 *   - Recursion depth = O(log n)
 *   - Each frame stores O(1) variables
 * 
 * ITERATIVE ALTERNATIVE:
 *   Time: O(log n) | Space: O(1)
 *   Same logic but with while loop instead of recursion
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - Binary search variants and correctness
 *    - Modified binary search for special cases
 * 
 * 2. Jon Lewis - "Algorithmic Thinking - Problem-Based Introduction"
 *    - Rotated array analysis
 *    - How to handle non-standard binary search scenarios
 * 
 * 3. LeetCode Discussion Forums
 *    - Community analysis of rotation-aware binary search
 *    - Common edge cases and pitfalls
 * 
 * RELATED PROBLEMS TO PRACTICE:
 * - LeetCode 81 (Search in Rotated Array II) - With duplicates
 * - LeetCode 153 (Find Minimum in Rotated) - Find min instead of value
 * - LeetCode 154 (Find Minimum II) - Min with duplicates
 * - LeetCode 162 (Find Peak Element) - Peak in rotated/unsorted
 * ============================================================================
 */