/**
 * LeetCode 1657: Determine if Two Strings Are Close
 *
 * PROBLEM: A string w1 is "close" to w2 if you can transform w1 to w2 using:
 *   1) Swap any two existing characters (reorder characters arbitrarily)
 *   2) Transform all occurrences of one character into another (and vice versa)
 *
 * KEY PROPERTIES:
 * - The set of characters must be identical between w1 and w2
 * - The multiset of character frequencies must be identical (order doesn't matter)
 *
 * APPROACH:
 * 1) Check character sets are equal (same unique letters)
 * 2) Check sorted frequency arrays are equal
 *
 * WHY IT WORKS:
 * - Swap operations allow arbitrary reordering, so only counts matter.
 * - Character transform operations allow frequency values to be permuted.
 * - Therefore, the sorted list of frequencies must match.
 */

import java.util.Arrays;

class Solution {
    public boolean closeStrings(String word1, String word2) {
        // Quick check: must have same length
        if (word1.length() != word2.length()) {
            return false;
        }

        // Count character frequencies for both words
        int[] freq1 = new int[26];
        int[] freq2 = new int[26];
        for (char c : word1.toCharArray()) {
            freq1[c - 'a']++;
        }
        for (char c : word2.toCharArray()) {
            freq2[c - 'a']++;
        }

        // Check that both words have exactly the same set of characters
        // (i.e., no character appears in one word but not the other)
        for (int i = 0; i < 26; i++) {
            if ((freq1[i] == 0) != (freq2[i] == 0)) {
                return false;
            }
        }

        // for (int i = 0; i < 26; i++) {
        //     if ((freq1[i] == 0 && freq2[i] > 0) ||
        //         (freq2[i] == 0 && freq1[i] > 0)) {
        //         return false;
        //     }
        // }

        // Sort frequencies and compare
        Arrays.sort(freq1);
        Arrays.sort(freq2);
        return Arrays.equals(freq1, freq2);
    }
}


class Solution {

    public boolean closeStrings(String word1, String word2) {
        if (word1.length() != word2.length()) {
            return false;
        }
        Map<Character, Integer> word1Map = new HashMap<>();
        Map<Character, Integer> word2Map = new HashMap<>();
        for (char c : word1.toCharArray()) {
            word1Map.put(c, word1Map.getOrDefault(c, 0) + 1);
        }
        for (char c : word2.toCharArray()) {
            word2Map.put(c, word2Map.getOrDefault(c, 0) + 1);
        }
        if (!word1Map.keySet().equals(word2Map.keySet())) {
            return false;
        }
        List<Integer> word1FrequencyList = new ArrayList<>(word1Map.values());
        List<Integer> word2FrequencyList = new ArrayList<>(word2Map.values());
        Collections.sort(word1FrequencyList);
        Collections.sort(word2FrequencyList);
        return word1FrequencyList.equals(word2FrequencyList);
    }
}

/* ============================================================================
 * COMPLEXITY ANALYSIS
 * ============================================================================
 * Time: O(n + k log k) where n = length of strings, k = alphabet size (26)
 * - Counting frequencies: O(n)
 * - Sorting fixed-size arrays: O(k log k) = constant
 * - Overall: O(n)
 *
 * Space: O(k) = O(1) (26-sized arrays)
 *
 * NOTE: This is optimal since we must examine each character at least once.
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. LeetCode discussion: "Close Strings" - character frequency sorting pattern
 * 2. CLRS: Frequency analysis & sorting fixed-size arrays
 * 3. Competitive programming: "anagram with frequency transformation" pattern
 */