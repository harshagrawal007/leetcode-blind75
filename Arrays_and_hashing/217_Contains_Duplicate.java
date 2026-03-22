/**
 * LeetCode 217: Contains Duplicate
 * 
 * PROBLEM: Determine if array contains any duplicate value
 * Return true if any value appears more than once, otherwise false
 * 
 * APPROACH: Hash Set for efficient lookup
 * PATTERN: Single-pass set membership checking
 * 
 * KEY INSIGHT:
 * - As we iterate, maintain a set of "seen" values
 * - If we ever see a value that's already in set = duplicate found!
 * - Time: O(n) because we might scan entire array
 * - Space: O(n) for potentially storing all unique elements
 * 
 * WHY HASH SET (NOT HASH MAP):
 * - We only care about existence, not indices
 * - Set is simpler, slightly faster memory access than Map
 */

/**
 * SOLUTION: Hash Set (Optimal)
 * 
 * Complexity: Time O(n) average, O(n²) worst case | Space O(min(n, capacity))
 * 
 * TIME ANALYSIS:
 * - Best case: Duplicate at position 1 → O(1)
 *   Example: [1, 1, 2, 3]
 * - Average case: Duplicate somewhere in middle → O(n/2) = O(n)
 * - Worst case: No duplicate or duplicate near end → O(n)
 *
 * SPACE ANALYSIS:
 * - If no duplicates: O(n) - store all elements
 * - If many duplicates: O(m) where m is unique count (m < n)
 * 
 * HashSet operations:
 * - contains(): O(1) average (hashing), O(n) worst (collision chain)
 * - add(): O(1) average (hashing), O(n) worst (collision chain)
 * 
 * WHY THIS IS OPTIMAL:
 * - Must examine each element at least once = O(n) lower bound
 * - Can't do better than checking every element
 * - This achieves the theoretical optimal
 */
class Solution {
    public boolean containsDuplicate(int[] nums) {
        // STRATEGY: Track all numbers we've seen
        // As soon as we see a repeat, return true
        HashSet<Integer> mySet = new HashSet();
 
        for (int i = 0; i < nums.length; i++) {
            // Already seen this number?
            if (mySet.contains(nums[i])) {
                // Found a duplicate!
                return true;
            } else {
                // New number - remember it
                mySet.add(nums[i]);
            }
        }
        
        // Completed entire array without finding duplicate
        return false;
    }
}

/* ============================================================================
 * ALTERNATIVE APPROACHES (Not Recommended)
 * ============================================================================
 * 
 * 1. BRUTE FORCE (Nested Loop) - Not Recommended
 *    Time: O(n²)  Space: O(1)
 *    
 *    for (int i = 0; i < nums.length; i++) {
 *        for (int j = i + 1; j < nums.length; j++) {
 *            if (nums[i] == nums[j]) return true;
 *        }
 *    }
 * 
 *    Problem: Way too slow for large arrays
 * 
 * 2. SORT + ADJACENT CHECK - Compromise Solution
 *    Time: O(n log n)  Space: O(n) or O(log n) depending on sort
 *    
 *    Arrays.sort(nums);
 *    for (int i = 0; i < nums.length - 1; i++) {
 *        if (nums[i] == nums[i + 1]) return true;
 *    }
 * 
 *    Problem: Modifies input array (bad for interviews)
 *            Slower than hash set for typical cases
 * 
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Donald Knuth - "The Art of Computer Programming, Vol. 3"
 *    - Hash table foundations and collision handling
 *    - Big O analysis of hash operations
 * 
 * 2. Thomas Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - HashSet data structure and O(1) average lookup proof
 *    - Set membership problem as fundamental algorithmic problem
 * 
 * 3. Joshua Bloch & Neal Gafter - "Java Puzzlers: Traps, Pitfalls, and Corner Cases"
 *    - HashSet performance characteristics in practice
 *    - Common mistakes in set usage
 * 
 * RELATED PROBLEMS TO PRACTICE (Similar Pattern):
 * - LeetCode 136 (Single Number) - Find unique element (XOR trick)
 * - LeetCode 202 (Happy Number) - Cycle detection in set
 * - LeetCode 219 (Contains Duplicate II) - Within k distance variation
 * - LeetCode 220 (Contains Duplicate III) - Within value range
 * - LeetCode 36 (Valid Sudoku) - Multiple sets for validation
 * ============================================================================
 */
