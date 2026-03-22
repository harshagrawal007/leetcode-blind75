/**
 * LeetCode 1207: Unique Number of Occurrences
 *
 * PROBLEM: Given an integer array, determine if the number of occurrences
 *          of each value is unique.
 * Example: [1,2,2,1,1,3] → occurrences: {1:3, 2:2, 3:1} → unique? Yes → true
 *
 * APPROACH: Count occurrences, then check if counts are unique
 * PATTERN: Frequency map + uniqueness check using a set
 *
 * KEY INSIGHT:
 * - First build a frequency map of value → count
 * - Then check if the counts themselves are unique by using a set
 * - If any count repeats, answer is false
 *
 * WHY THIS WORKS:
 * - We only care about frequency bucket uniqueness, not value order
 * - HashMap gives O(1) count updates and lookups
 * - HashSet gives O(1) uniqueness checks
 */

/**
 * Complexity: Time O(n) | Space O(n)
 *
 * TIME ANALYSIS:
 * - Build frequency map: O(n)
 * - Build unique-count set: O(m) where m = number of unique values (≤ n)
 * - Total: O(n + m) = O(n)
 *
 * SPACE ANALYSIS:
 * - Frequency map: O(m)
 * - Unique-count set: O(m)
 * - Total: O(m) = O(n)
 */
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        // 1) Count frequencies of each number
        HashMap<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // 2) Check if all frequencies are unique
        HashSet<Integer> seenCounts = new HashSet<>();
        for (int count : frequencyMap.values()) {
            // If we've seen this count before, it's not unique
            if (!seenCounts.add(count)) {
                return false;
            }
        }
        return true;
    }
}

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - Hash table usage for counting frequency
 *    - Use of sets to enforce uniqueness
 *
 * 2. Steven Skiena - "The Algorithm Design Manual"
 *    - Pattern: Counting + uniqueness check
 *    - Real-world analog: histogram + uniqueness test
 *
 * 3. LeetCode Discussion Forums
 *    - Common interview pattern: "frequency of frequencies"
 *    - Variation: unique frequency set checks
 *
 * RELATED PROBLEMS TO PRACTICE:
 * - LeetCode 1297 (Maximum Number of Occurrences of a Substring)
 * - LeetCode 347 (Top K Frequent Elements) - Frequent element patterns
 * - LeetCode 451 (Sort Characters By Frequency) - frequency sort
 * - LeetCode 242 (Valid Anagram) - frequency count comparison
 * ============================================================================
 */