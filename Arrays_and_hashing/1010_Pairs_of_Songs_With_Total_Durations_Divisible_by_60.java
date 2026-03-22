/**
 * LeetCode 1010: Pairs of Songs With Total Durations Divisible by 60
 *
 * PROBLEM: Given an array of song durations (seconds), count pairs (i, j) with i < j
 *          such that (time[i] + time[j]) % 60 == 0.
 *
 * APPROACH: Remainder counting (mod 60) using a frequency array
 * PATTERN: Two-sum with modular arithmetic
 *
 * KEY INSIGHT:
 * - (a + b) % 60 == 0 iff (a % 60) + (b % 60) == 60 (or both are 0)
 * - For each song, compute remainder r = t % 60.
 *   - If r == 0, it pairs with previously seen songs with remainder 0.
 *   - Else, it pairs with previously seen songs with remainder (60 - r).
 * - Keep a running count of how many songs had each remainder.
 *
 * WHY THIS IS O(n):
 * - We scan the array once and maintain constant-size remainder counts (60 slots).
 * - Each element is processed in O(1).
 *
 * EXTRA: This is basically a mod-60 variant of the Two-Sum pattern.
 */


class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        // Use an array of size 60 to count how many songs have each remainder
        // (duration % 60). This avoids using a HashMap and keeps it O(1) space.
        int[] remainders = new int[60];
        int count = 0;

        for (int t : time) {
            int r = t % 60;

            // If r == 0, it matches with other r==0 values
            // Otherwise, it matches with values whose remainder is 60 - r
            if (r == 0) {
                count += remainders[0];
            } else {
                count += remainders[60 - r];
            }

            // Record this song's remainder for future pairs
            remainders[r]++;
        }

        return count;
    }
}

/* ============================================================================
 * COMPLEXITY ANALYSIS
 * ============================================================================
 * Time: O(n) where n = number of songs
 * Space: O(1) (constant 60-sized array)
 *
 * This is optimal: you must inspect each song at least once, and the remainder
 * bookkeeping is constant-size.
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Steven Skiena - "The Algorithm Design Manual"
 *    - Techniques for counting pairs using complement-based hashing
 * 
 * 2. LeetCode Discuss - Common solutions for mod-based pair counting
 *    - Frequently used pattern: (a + b) % k == 0 → (a % k) + (b % k) == k
 * 
 * 3. Programming Pearls (Jon Bentley) - Pair counting with remainder buckets
 *    - Efficiently counting pair sums with fixed mod buckets
 * 
 * RELATED PROBLEMS TO PRACTICE:
 * - LeetCode 523 (Continuous Subarray Sum) - Modulo prefix sums
 * - LeetCode 1679 (Max Number of K-Sum Pairs) - Two-sum with frequency counts
 * - LeetCode 974 (Subarray Sums Divisible by K) - Prefix sum remainder counting
 * ============================================================================
 */