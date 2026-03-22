/**
 * LeetCode 74: Search a 2D Matrix
 * 
 * PROBLEM: Search for value in m×n matrix with two properties:
 * 1. Integers in each row sorted left to right
 * 2. Last element of row < first element of next row
 * 
 * This means matrix is FULLY SORTED (like 1D if flattened)
 * 
 * APPROACH 1: Staircase Search O(m+n) - Simple
 * APPROACH 2: Binary Search O(log(m*n)) - Optimal
 * 
 * KEY INSIGHT: Treat 2D matrix as 1D sorted array
 * Can map 1D index to 2D: index i → [i/columns][i%columns]
 */

/**
 * SOLUTION 1: Staircase Search (Simple, but slower)
 * Time: O(m + n) | Space: O(1)
 * 
 * ALGORITHM:
 * 1. Start at top-right corner (or bottom-left)
 * 2. If target > current: move down (eliminate left part)
 * 3. If target < current: move left (eliminate right part)
 * 4. If target == current: found! Return true
 * 
 * Why top-right? Only direction where we can eliminate:
 * - If current > target: we need smaller, go left
 * - If current < target: we need larger, go down
 * Can't do this from top-left or bottom-right efficiently
 * 
 * Example: Find 13
 * [[ 1,  3,  5,  7],
 *  [10, 11, 16, 20],
 *  [23, 30, 34, 60]]
 * 
 * Start at 7 (top-right): 13 > 7, go down
 * At 20: 13 < 20, go left
 * At 16: 13 < 16, go left
 * At 11: 13 > 11, go down
 * At 30: 13 < 30, go left
 * At 23: can't go left anymore, search ends
 * Not found ✓
 */
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        // VALIDATION
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        // STRATEGY: Start at top-right corner
        // Why? Can eliminate rows or columns based on comparison
        int i = 0;  // Row index (start at top)
        int j = matrix[0].length - 1;  // Column index (start at right)
        
        // MOVE: Go down or left until exhausted
        while (i < matrix.length && j >= 0) {
            int current = matrix[i][j];
            
            if (current == target) {
                return true;  // Found!
            } else if (current > target) {
                // Current too large, need smaller
                // All below are larger (property 2), all right are larger
                // Go left to find smaller values
                j--;
            } else {
                // Current too small, need larger
                // All above are smaller, all left are smaller
                // Go down to find larger values
                i++;
            }
        }
        
        return false;  // Exhausted search space
    }
}

/**
 * SOLUTION 2: Binary Search (OPTIMAL)
 * Time: O(log(m * n)) | Space: O(1)
 * 
 * KEY INSIGHT: Treat 2D as 1D sorted array!
 * 
 * Mapping formula:
 * 1D index i → 2D [i/columns][i%columns]
 * Example: matrix[4/3][4%3] = matrix[1][1]
 * 
 * Why this works: Matrix is fully sorted due to problem properties
 * 
 * TIME COMPARISON:
 * - Staircase: O(m + n) = O(log m * n) in terms of linear path
 * - Binary: O(log(m*n)) = true logarithmic, much faster!
 * - For 1000×1000 matrix:
 *   Staircase: ~2000 steps
 *   Binary: ~20 steps
 */
class Solution2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;

        int rows = matrix.length;
        int columns = matrix[0].length;

        // Treat 2D matrix as 1D sorted array
        // Total elements: rows * columns
        int low = 0;
        int high = rows * columns - 1;

        // Standard binary search on "virtual" 1D array
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Convert 1D index to 2D coordinates
            // mid = row * columns + col
            // So: row = mid / columns, col = mid % columns
            int midValue = matrix[mid / columns][mid % columns];

            if (midValue == target) {
                return true;  // Found!
            } else if (midValue < target) {
                // Target must be in right half of virtual 1D array
                low = mid + 1;
            } else {
                // Target must be in left half of virtual 1D array
                high = mid - 1;
            }
        }
        
        return false;  // Not found
    }
}

/* ============================================================================
 * EXAMPLE WALKTHROUGH (Binary Search)
 * ============================================================================
 * Matrix (3×4):
 * [[ 1,  3,  5,  7],
 *  [10, 11, 16, 20],
 *  [23, 30, 34, 60]]
 * 
 * Find 13, rows=3, cols=4, total=12
 * 
 * Search 1: low=0, high=11, mid=5
 *   matrix[5/4][5%4] = matrix[1][1] = 11
 *   11 < 13, so low = 6
 * 
 * Search 2: low=6, high=11, mid=8
 *   matrix[8/4][8%4] = matrix[2][0] = 23
 *   23 > 13, so high = 7
 * 
 * Search 3: low=6, high=7, mid=6
 *   matrix[6/4][6%4] = matrix[1][2] = 16
 *   16 > 13, so high = 5
 * 
 * low > high, exit loop, return false ✓
 * ============================================================================
 */

/* ============================================================================
 * COMPLEXITY COMPARISON
 * ============================================================================
 * Solution 1 (Staircase):
 *   Time: O(m + n)  Space: O(1)
 *   Simpler logic, good for interviews to show thinking
 *   When to use: Simpler code at cost of slower search
 * 
 * Solution 2 (Binary Search):
 *   Time: O(log(m*n))  Space: O(1)
 *   BEST for actual performance
 *   When to use: When performance matters (large matrices)
 * 
 * WHEN BINARY IS EXPONENTIALLY BETTER:
 * - 1000×1000 matrix: 2000 vs 20 comparisons
 * - 10000×10000 matrix: 20000 vs 27 comparisons
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - Binary search and its applications
 *    - When to apply binary search to 2D problems
 * 
 * 2. Steven Skiena - "The Algorithm Design Manual"
 *    - Searching in 2D structures
 *    - Space transformation techniques
 * 
 * 3. LeetCode Editorial Solutions
 *    - Community consensus on approach selection
 *    - Practical performance considerations
 * 
 * RELATED PROBLEMS TO PRACTICE:
 * - LeetCode 240 (Search II) - With duplicates allowed
 * - LeetCode 378 (Kth Smallest in Sorted Matrix) - Find kth element
 * - LeetCode 1239 (Maximum Length of String Combination) - 2D search
 * ============================================================================
 */