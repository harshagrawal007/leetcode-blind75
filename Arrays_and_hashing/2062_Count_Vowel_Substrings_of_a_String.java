import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode 2062: Count Vowel Substrings of a String
 *
 * PROBLEM: Given a string, count substrings that contain all five vowels (a, e, i, o, u)
 * and consist of only vowels.
 *
 * KEY INSIGHT:
 * - A valid substring must contain only vowels and must include each vowel at least once.
 * - While iterating, track the last index of each vowel and the last index of any consonant.
 * - For substrings ending at position i, the earliest valid start is one position after
 *   the most recent consonant, and must also be <= the earliest last-seen vowel index.
 * - The number of valid substrings ending at i is max(0, minVowelIndex - lastConsonantIndex).
 */
class Solution {
    public int countVowelSubstrings(String word) {
        if (word == null || word.length() < 5) return 0;

        // Track the last index each vowel appeared at (initialized to -1)
        Map<Character, Integer> lastVowelIndex = new HashMap<>();
        char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u'};
        for (char v : vowels) {
            lastVowelIndex.put(v, -1);
        }

        int lastConsonantIndex = -1;
        int count = 0;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (!lastVowelIndex.containsKey(c)) {
                // Consonant -> any substring containing this char is invalid
                lastConsonantIndex = i;
            } else {
                // Vowel -> update its last seen index
                lastVowelIndex.put(c, i);

                // Find the earliest position where all five vowels are present
                int minVowelIdx = word.length();
                for (int idx : lastVowelIndex.values()) {
                    minVowelIdx = Math.min(minVowelIdx, idx);
                }

                // Any substring starting after lastConsonantIndex and ending here
                // will contain all five vowels if we have seen each vowel at least once.
                count += Math.max(0, minVowelIdx - lastConsonantIndex);
            }
        }
        /*
        Keep track of 6 values: the last index we saw a consonant, and the last
        index where we saw each of the 5 vowels. 
        At each index: the number of valid substrings ending here is the
        distance backwards from the earliest last seen vowel to the last 
        seen consonant.
        */

        return count;
    }
}

/* ==========================================================================
 * COMPLEXITY ANALYSIS
 * ==========================================================================
 * Time: O(n) - single pass through the string, constant work per character.
 * Space: O(1) - fixed-size map for the 5 vowels.
 *
 * This is optimal since any solution must inspect each character at least once.
 * ========================================================================== */

/* ==========================================================================
 * KEY CITATIONS & REFERENCES
 * ==========================================================================
 * 1. LeetCode discussion: "Count Vowel Substrings of a String".
 * 2. Sliding window / last-seen-index technique for counting substrings.
 * 3. Related problem: "Count Substrings With All Three Characters".
 * ========================================================================== */
