/**
 * LeetCode 704: Binary Search
 * 
 * PROBLEM: Find the index of target in a sorted array
 * Return index if found, otherwise return -1
 * 
 * CONSTRAINTS:
 * - Array is sorted in ascending order
 * - Array may contain duplicates (though doesn't matter for first occurrence)
 * 
 * APPROACH: Binary Search (Divide and Conquer)
 * PATTERN: Classic binary search - fundamental algorithm
 * 
 * KEY INSIGHT:
 * - Array is sorted = we can eliminate half the remaining elements each time
 * - Compare middle element with target
 * - If match: return; If target > mid: search right half; else: search left half
 * - Each step halves the search space
 * 
 * TWO IMPLEMENTATIONS:
 * - Recursive: More elegant, uses call stack
 * - Iterative: More efficient memory, prefer in interviews
 */

/**
 * SOLUTION 1: Recursive Binary Search
 * 
 * Complexity: Time O(log n) | Space O(log n) due to call stack
 * 
 * TIME ANALYSIS:
 * - Each recursive call eliminates half the elements
 * - n → n/2 → n/4 → ... → 1
 * - Number of steps = log₂(n)
 * - Example: 1000000 elements takes ~20 comparisons
 * 
 * SPACE ANALYSIS:
 * - Call stack depth = O(log n)
 * - Each call stores left, right, mid variables
 * - Total recursion depth = log n levels
 * 
 * WHY RECURSIVE:
 * - More intuitive and easier to understand
 * - Matches the divide-and-conquer nature of the algorithm
 * - Easier for students to visualize the partitioning
 * 
 * WHEN TO USE:
 * - Interview: Use if you have space to spare and want clarity
 * - Production: Avoid if memory is constrained (stack overflow risk)
 */
class Solution {
    public int search(int[] nums, int target) {
        // VALIDATION
        if (nums == null || nums.length == 0) return -1;
        
        // Call recursive helper
        return binarySearch(nums, target, 0, nums.length - 1);
    }

    /**
     * Recursive helper for binary search
     * @param nums sorted array
     * @param target value to find
     * @param left left boundary (inclusive)
     * @param right right boundary (inclusive)
     * @return index of target, or -1 if not found
     */
    public int binarySearch(int[] nums, int target, int left, int right) {
        // BASE CASE: Search space exhausted
        if (left > right) return -1;
        
        // CALCULATE MID (avoid integer overflow)
        // mid = (left + right) / 2 can overflow for large numbers
        // mid = left + (right - left) / 2 is safer
        int mid = left + (right - left) / 2;

        // CASE 1: Found the target
        if (nums[mid] == target) {
            return mid;
        }
        // CASE 2: Target is in right half (target is greater)
        else if (target > nums[mid]) {
            return binarySearch(nums, target, mid + 1, right);
        }
        // CASE 3: Target is in left half (target is smaller)
        else if (target < nums[mid]) {
            return binarySearch(nums, target, left, mid - 1);
        }

        return -1;
    }
}

/**
 * SOLUTION 2: Iterative Binary Search (PREFERRED)
 * 
 * Complexity: Time O(log n) | Space O(1)
 * 
 * TIME ANALYSIS:
 * - Same as recursive: eliminates half each iteration
 * - O(log n) comparisons needed
 * 
 * SPACE ANALYSIS:
 * - O(1) constant space - only keeps track of pointers
 * - No call stack overhead
 * - Much better for large arrays or memory-constrained systems
 * 
 * WHY ITERATIVE:
 * - More efficient use of space (no recursion overhead)
 * - No risk of stack overflow (stack depth = 0)
 * - Generally preferred in production code
 * - Easier to debug (can set breakpoints easily)
 * 
 * WHEN TO USE:
 * - Interview: PREFERRED - shows knowledge of optimization
 * - Production: ALWAYS - better space efficiency
 */
class Solution {
    public int search(int[] nums, int target) {
        // INITIALIZATION: Set boundaries
        int i = 0;  // left pointer (inclusive)
        int j = nums.length - 1;  // right pointer (inclusive)

        // LOOP: While search space is not empty
        // Invariant: If target exists, it's between i and j
        while (i <= j) {
            // CALCULATE MID (avoid overflow)
            int mid = i + (j - i) / 2;

            // THREE CASES:
            if (nums[mid] == target) {
                // Found it!
                return mid;
            } else if (nums[mid] < target) {
                // Target is larger than middle, search right half
                // We can skip mid (mid + 1)
                i = mid + 1;
            } else {
                // Target is smaller than middle, search left half
                // We can skip mid (mid - 1)
                j = mid - 1;
            }
        }

        // Not found after exhausting search space
        return -1;
    }
}

/* ============================================================================
 * COMPLEXITY COMPARISON & ANALYSIS
 * ============================================================================
 * Solution 1 (Recursive):
 *   Time: O(log n)  Space: O(log n) call stack
 *   Good: Clean, elegant code
 *   Bad: Stack overhead, potential stack overflow on very large arrays
 * 
 * Solution 2 (Iterative):
 *   Time: O(log n)  Space: O(1)
 *   Good: Most efficient, no stack risk
 *   Bad: Slightly less elegant than recursive
 * 
 * BINARY SEARCH INVARIANT:
 * - At each step: if target exists, it's in range [left, right]
 * - This invariant is maintained throughout the algorithm
 * - Guarantees correctness of the algorithm
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Donald Knuth - "The Art of Computer Programming, Vol. 3"
 *    - First systematic analysis of binary search
 *    - Proof of O(log n) correctness
 * 
 * 2. Thomas Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - Divide-and-conquer analysis
 *    - Binary search as foundation for many algorithms
 * 
 * 3. Jon Lewis - "Algorithmic Thinking - A Problem-Based Introduction"
 *    - Real-world applications of binary search
 *    - When and when not to use binary search
 * 
 * RELATED PROBLEMS TO PRACTICE (Binary Search Variations):
 * - LeetCode 33 (Search in Rotated Sorted Array) - Rotated array variant
 * - LeetCode 34 (Find First and Last Position) - Find boundaries
 * - LeetCode 35 (Search Insert Position) - Where would it go?
 * - LeetCode 74 (Search in 2D Matrix) - 2D grid variant
 * - LeetCode 153 (Find Minimum in Rotated) - Find minimum element
 * ============================================================================
 */
