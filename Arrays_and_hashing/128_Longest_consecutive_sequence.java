/**
 * LeetCode 128: Longest Consecutive Elements Sequence
 * 
 * PROBLEM: Find length of longest consecutive sequence in unsorted array
 * Example: [100, 4, 200, 1, 3, 2] → [1,2,3,4] = length 4
 * 
 * CONSTRAINT: Must run in O(n) time!
 * - Can't use sorting (would be O(n log n))
 * - Must use HashSet to achieve O(n)
 * 
 * APPROACH: HashSet + Smart Sequence Detection
 * PATTERN: Only start counting from sequence beginnings
 * 
 * KEY INSIGHT:
 * 1. Convert array to HashSet for O(1) lookups
 * 2. For each number, check if it's the START of a sequence
 *    - A number is a START if (num - 1) doesn't exist
 * 3. Only start counting from sequence beginnings
 *    - This avoids redundant counting
 *    - Example: For sequence [1,2,3,4], only count starting from 1
 *              Not from 2, 3, or 4
 * 4. Count consecutive numbers: num, num+1, num+2, ...
 * 
 * WHY THIS IS O(n) NOT O(n²):
 * - Each number is visited at most 3 times:
 *   Once added to set, once in outer loop, once in inner while
 * - IMPORTANT: We DON'T count from non-start numbers
 * - This ensures total iterations = O(n)
 */

/**
 * SOLUTION: HashSet with Smart Sequence Detection
 * 
 * Complexity: Time O(n) | Space O(n)
 * 
 * TIME ANALYSIS (Critical Understanding):
 * - Building HashSet: O(n)
 * - Outer loop: O(n) - iterate through each number
 * - Inner loop (while): O(1) amortized per number!
 * 
 * AMORTIZED ANALYSIS - Why inner loop is O(1):
 * - Each number is counted in the while loop AT MOST ONCE
 * - Example: [1,2,3,4,5]
 *   - When processing 1: count 1,2,3,4,5 (5 iterations) → length = 5
 *   - When processing 2: skip (2-1 exists) → 0 iterations
 *   - When processing 3: skip (3-1 exists) → 0 iterations
 *   - Total: 5 iterations for 5 elements = O(n)
 * 
 * - The key: We skip all non-start numbers in outer loop
 * - Each number is in while loop exactly once across all invocations
 * - Total = O(n) + O(n) = O(n)
 * 
 * SPACE ANALYSIS:
 * - HashSet stores all unique elements: O(n)
 * - Extra variables: O(1)
 * - Total: O(n)
 * 
 * WHY NOT SORT?:
 * - Sorting: Time O(n log n) - TOO SLOW
 * - This solution: Time O(n) - OPTIMAL
 * 
 * PROOF OF OPTIMALITY:
 * - Must examine each element at least once = O(n) lower bound
 * - This solution achieves that = optimal
 */
class Solution {
    public int longestConsecutive(int[] nums) {
        // STEP 1: Convert array to HashSet
        // Purpose: O(1) lookup for existence check
        // Why set: Remove duplicates, optimize searching
        HashSet<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        
        // STEP 2: Track longest sequence found so far
        int longestSequence = 0;
        
        // STEP 3: Find all sequences
        for (int num : nums) {
            // KEY OPTIMIZATION: Only count from sequence STARTS
            // A number is a sequence start if (num - 1) doesn't exist
            // This avoids redundant counting in the middle of sequences
            // Example: [1,2,3] - only process starting from 1, skip 2 and 3
            if (!numSet.contains(num - 1)) {
                // This IS a sequence start!
                
                // STEP 4: Count how long this sequence is
                // num, num+1, num+2, ... as long as they exist
                int length = 0;
                
                // Count consecutive numbers starting from num
                while (numSet.contains(num + length)) {
                    length++;
                }
                
                // Update longest if current sequence is longer
                longestSequence = Math.max(length, longestSequence);
            }
        }
        
        return longestSequence;
    }
}

/* ============================================================================
 * SIMULATION EXAMPLE
 * ============================================================================
 * Input: [100, 4, 200, 1, 3, 2]
 * 
 * STEP 1: HashSet = {100, 4, 200, 1, 3, 2}
 * 
 * STEP 3: Iterate nums:
 * - num=100: (100-1=99) not in set → START
 *   Check: 100, 101 no → length = 1
 *   longestSequence = 1
 * 
 * - num=4: (4-1=3) IS in set → NOT START, SKIP
 * 
 * - num=200: (200-1=199) not in set → START
 *   Check: 200, 201 no → length = 1
 *   longestSequence = 1
 * 
 * - num=1: (1-1=0) not in set → START
 *   Check: 1✓, 2✓, 3✓, 4✓, 5✗ → length = 4
 *   longestSequence = 4
 * 
 * - num=3: (3-1=2) IS in set → NOT START, SKIP
 * 
 * - num=2: (2-1=1) IS in set → NOT START, SKIP
 * 
 * Output: 4 (sequence [1,2,3,4])
 * ============================================================================
 */

/* ============================================================================
 * COMPLEXITY COMPARISON
 * ============================================================================
 * Solution (Hash Set): Time O(n)  Space O(n)
 *   Optimal for constraints
 *   Must use for O(n) requirement
 * 
 * Alternative 1 (Sort): Time O(n log n)  Space O(1) or O(n)
 *   Slower due to sorting overhead
 *   Good when space is critical
 * 
 * Alternative 2 (Nested Loops): Time O(n²)  Space O(1)
 *   Way too slow, don't use
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Cormen, Leiserson, Rivest, Stein - "Introduction to Algorithms (CLRS)"
 *    - Amortized analysis (why inner loop is O(1) total)
 *    - Hash table analysis and complexity proofs
 * 
 * 2. Steven Skiena - "The Algorithm Design Manual"
 *    - Hash tables for set membership
 *    - When to use sets vs sorting
 * 
 * 3. Jon Lewis - "Algorithmic Thinking"
 *    - Problem decomposition techniques
 *    - Recognizing O(n) vs O(n log n) vs O(n²) problems
 * 
 * RELATED PROBLEMS TO PRACTICE (Similar Pattern):
 * - LeetCode 299 (Bulls and Cows) - Hash counting pattern
 * - LeetCode 523 (Continuous Subarray Sum) - Consecutive sums
 * - LeetCode 659 (Split Array into Consecutive Subsequences) - Match sequences
 * - LeetCode 1157 (Online Majority Element In Subarray) - Range queries
 * ============================================================================
 */