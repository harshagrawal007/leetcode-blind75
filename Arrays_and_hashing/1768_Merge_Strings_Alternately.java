/**
 * LeetCode 1768: Merge Strings Alternately
 *
 * PROBLEM: Given two strings word1 and word2, merge them by alternating
 *          characters. If one string is longer, append the remaining part.
 *
 * EXAMPLE:
 *    word1 = "abc", word2 = "pqrs" → "apbqcrs"
 *
 * APPROACH: Two-pointer traversal with StringBuilder
 * PATTERN: Iterate while either string has remaining characters
 *
 * KEY INSIGHT:
 * - Each index i contributes at most two characters (one from each string)
 * - Stop when both strings are exhausted
 * - Append remaining suffix automatically by checking bounds
 */

class Solution {
    public String mergeAlternately(String word1, String word2) {
        if (word1 == null  && word2==null){
            return null;
        }
        if (word1 == null  && word2!=null){
            return word2;
        }
        if(word1 !=null && word2== null){
            return word1;
        }
        StringBuilder sb = new StringBuilder();
        int i;
        for ( i=0; i< word1.length() && i< word2.length();i++){
                sb.append(word1.charAt(i));
                sb.append(word2.charAt(i));
        }
        if(i == word1.length() && i< word2.length()){
            sb.append(word2.substring(i, word2.length()));
        } else if( i== word2.length() && i< word1.length()){
            sb.append(word1.substring(i,word1.length()));
        }
        return sb.toString();
    }
}

//optimized
class Solution {
    public String mergeAlternately(String word1, String word2) {
        // Handle null input gracefully (optional); problem constraints usually avoid null
        if (word1 == null) word1 = "";
        if (word2 == null) word2 = "";

        StringBuilder result = new StringBuilder();
        int i = 0;
        // Continue while either string still has characters
        while (i < word1.length() || i < word2.length()) {
            if (i < word1.length()) {
                result.append(word1.charAt(i));
            }
            if (i < word2.length()) {
                result.append(word2.charAt(i));
            }
            i++;
        }

        return result.toString();
    }
}

/* ============================================================================
 * COMPLEXITY ANALYSIS
 * ============================================================================
 * Time: O(n + m) where n = word1.length(), m = word2.length()
 * Space: O(n + m) output string
 *
 * This is optimal: Must output combined length, so O(n+m) space/time is best.
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. LeetCode discussion: "Merge Strings Alternately" - two-pointer pattern
 * 2. Standard string interleaving / zipper algorithm
 * 3. StringBuilder best practice for concatenation in loops
 */