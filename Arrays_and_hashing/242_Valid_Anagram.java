/**
 * LeetCode 242: Valid Anagram
 * 
 * PROBLEM: Determine if two strings are anagrams of each other
 * Anagram = same characters with same frequencies, different order
 * Examples: "listen" & "silent", "evil" & "vile"
 * 
 * APPROACH: Character frequency counting with fixed array
 * PATTERN: Fixed-size array for known character set (lowercase English)
 * 
 * KEY INSIGHT:
 * 1. Quick validation: s and t must have same length
 * 2. Count character frequencies in s using fixed array (26 letters)
 * 3. Verify t by consuming frequency counts from array
 * 4. If all counts match perfectly = valid anagram
 * 
 * WHY FIXED ARRAY (NOT HASH MAP):
 * - Only 26 possible lowercase English letters = small fixed size
 * - Array access O(1) faster than HashMap hashing
 * - Space: O(26) = O(1) is constant, better than HashMap
 */

/**
 * SOLUTION: Fixed-Size Array (Optimal for lowercase English)
 * 
 * Complexity: Time O(n) where n = string length | Space O(1)
 * 
 * TIME ANALYSIS:
 * - Loop 1: Count chars in s → O(|s|)
 * - Loop 2: Verify chars in t → O(|t|)
 * - Total: O(|s| + |t|) where |s| ≤ |t| or vice versa
 * - Since anagram requires |s| == |t|, simplifies to O(n)
 * 
 * SPACE ANALYSIS:
 * - Fixed array of size 26 for a-z letters
 * - O(26) = O(1) constant space (not O(n))
 * - Much better than HashMap which stores actual characters
 * 
 * WHY THIS IS OPTIMAL:
 * - Must read entire string = O(n) lower bound
 * - This achieves that bound with minimal space overhead
 * - Best solution when character set is fixed and small
 */
class Solution {
    public boolean isAnagram(String s, String t) {
        // QUICK VALIDATION: Different lengths can't be anagrams
        if (s.length() != t.length()) {
            return false;
        }
        
        // STEP 1: Count character frequencies in s
        // Array index: 0='a', 1='b', ... 25='z'
        int[] charCount = new int[26];
        for (char c : s.toCharArray()) {
            charCount[c - 'a']++;  // Convert char to index (0-25)
        }
        
        // STEP 2: Verify each character in t exists with matching frequency
        for (char d : t.toCharArray()) {
            // Check if this character exists in our frequency count
            if (charCount[d - 'a'] <= 0) {
                // Either not in s, or already used up all its occurrences
                return false;
            }
            // Consume one occurrence of this character
            charCount[d - 'a']--;
        }
        
        // If we successfully matched all characters, it's an anagram
        return true;
    }
}

/* ============================================================================
 * ALTERNATIVE APPROACHES
 * ============================================================================
 * 
 * 1. SORT + COMPARE (Simple but slower)
 *    Time: O(n log n)  Space: O(n)
 *    
 *    char[] s1 = s.toCharArray();
 *    char[] s2 = t.toCharArray();
 *    Arrays.sort(s1);
 *    Arrays.sort(s2);
 *    return Arrays.equals(s1, s2);
 * 
 *    Problem: Sorting is slower than counting for this problem
 * 
 * 2. HASH MAP (General-purpose, works for any character set)
 *    Time: O(n)  Space: O(k) where k = unique characters
 *    
 *    Map<Character, Integer> map = new HashMap<>();
 *    for (char c : s.toCharArray()) map.put(c, map.getOrDefault(c, 0) + 1);
 *    for (char c : t.toCharArray()) {
 *        if (!map.containsKey(c) || map.get(c) == 0) return false;
 *        map.put(c, map.get(c) - 1);
 *    }
 *    return true;
 * 
 *    Good when: Character set is unknown or very large
 *    Bad when: Character set is fixed and small (HashMap overhead)
 * 
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Steven Skiena - "The Algorithm Design Manual"
 *    - Character frequency analysis fundamentals
 *    - When to use arrays vs. hash tables
 * 
 * 2. Thomas Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - Counting sort and frequency-based approaches
 *    - O(1) vs O(n) space complexity analysis
 * 
 * 3. University of Illinois Course Materials (CS 225)
 *    - Practical analysis of array vs hash table performance
 *    - Cache locality and real-world timing
 * 
 * RELATED PROBLEMS TO PRACTICE (Similar Pattern):
 * - LeetCode 49 (Group Anagrams) - Group multiple strings by anagram
 * - LeetCode 438 (Find All Anagrams in String) - Sliding window variation
 * - LeetCode 567 (Permutation in String) - Is t a permutation of s?
 * - LeetCode 383 (Ransom Note) - Character frequency matching
 * - LeetCode 205 (Isomorphic Strings) - Character mapping pattern
 * ============================================================================
 */
