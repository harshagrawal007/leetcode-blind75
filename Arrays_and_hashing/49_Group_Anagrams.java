/**
 * LeetCode 49: Group Anagrams
 * 
 * PROBLEM: Group strings that are anagrams of each other
 * Anagrams = same characters with same frequencies, different order
 * Example: ["eat", "tea", "ate"] → should be in same group
 *          ["bat"] → in different group
 * 
 * APPROACH 1: Character Frequency as Hash Key (More Efficient)
 * APPROACH 2: Sorted String as Hash Key (Simpler to understand)
 * 
 * KEY INSIGHT:
 * - Anagrams have same character frequencies (but different order)
 * - Create a unique "signature" for each anagram group
 * - Use HashMap to group strings with same signature
 * - Both approaches create different signatures for same anagram
 */

/**
 * SOLUTION 1: Character Frequency Signature (Optimal)
 * 
 * Complexity: Time O(n*k) where n=array length, k=avg string length
 *             Space O(n*k) for storing strings in HashMap
 * 
 * TIME BREAKDOWN:
 * - For each of n strings:
 *   - Count characters: O(k)
 *   - Build signature: O(26) = O(1) constant
 *   - HashMap operations: O(1) average
 * - Total: O(n*k)
 * 
 * SPACE BREAKDOWN:
 * - HashMap stores: all n strings = O(n*k)
 * - count array: fixed size 26 = O(1)
 * - StringBuilder: O(26) = O(1) per iteration
 * - Total: O(n*k)
 * 
 * WHY THIS IS BETTER:
 * - Avoids sorting each string (which costs O(k log k))
 * - Uses character frequency (O(k)) instead
 * - Better for large strings where k log k matters
 * 
 * SIGNATURE EXAMPLE:
 * "eat" → [1,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0]
 *         → "#1#0#0#0#1#0...#1#0#0#0"
 * "tea" → same frequency, same signature!
 * "ate" → same frequency, same signature!
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        // EDGE CASE: Empty input
        if (strs.length == 0) return new ArrayList();
        
        // KEY INSIGHT: Group strings by their character frequency signature
        // Anagrams have identical frequency signatures
        Map<String, List> ans = new HashMap<String, List>();
        
        // STEP 1: For each string, create a frequency-based signature
        int[] count = new int[26];  // Track frequency of 'a' through 'z'
        
        for (String s : strs) {
            // Reset count array for this string
            Arrays.fill(count, 0);
            
            // Count frequency of each character
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }

            // STEP 2: Create unique signature from frequencies
            // Format: "#1#0#0#5#2..." represents the frequency array
            // This is a canonical form for anagram signatures
            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append("#");  // Delimiter to separate counts
                sb.append(count[i]);
            }
            
            String key = sb.toString();
            
            // STEP 3: Group strings with same signature
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList());
            }
            ans.get(key).add(s);
        }
        
        // Return all groups (order doesn't matter)
        return new ArrayList(ans.values());
    }
}

/**
 * SOLUTION 2: Sorted String as Key (Simpler but Slower)
 * 
 * Complexity: Time O(n*k log k) where n=array length, k=avg string length
 *             Space O(n*k) for storing strings
 * 
 * TIME BREAKDOWN:
 * - For each of n strings:
 *   - Sort characters: O(k log k) - THIS IS THE BOTTLENECK
 *   - HashMap operations: O(1) average
 * - Total: O(n*k log k)
 * 
 * ADVANTAGE: Much simpler to understand - just sort and compare
 * DISADVANTAGE: Slower due to sorting overhead
 * 
 * WHEN TO USE:
 * - When code clarity matters more than performance
 * - When string length k is small (sorting overhead is negligible)
 * - Interview when you want to show you know sorting
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        
        // STEP 1: For each string, sort its characters to get canonical form
        for (String word : strs) {
            // Key insight: Anagrams become identical after sorting
            // "eat" → "aet", "tea" → "aet", "ate" → "aet"
            char[] chars = word.toCharArray();
            Arrays.sort(chars);  // O(k log k) - expensive operation
            String sortedWord = new String(chars);  // Canonical form
            
            // STEP 2: Group by sorted form
            if (!map.containsKey(sortedWord)) {
                map.put(sortedWord, new ArrayList<>());
            }
            
            map.get(sortedWord).add(word);  // Store original string
        }
        
        // Return all groups
        return new ArrayList<>(map.values());
    }
}

/* ============================================================================
 * COMPLEXITY COMPARISON
 * ============================================================================
 * Solution 1 (Frequency Signature):
 *   Time: O(n*k)      Space: O(n*k)  Recommendation: BEST
 *   Better for large strings (sorting cost avoided)
 * 
 * Solution 2 (Sort String):
 *   Time: O(n*k log k) Space: O(n*k)  Recommendation: Interview alternative
 *   Simpler to write and understand
 * 
 * Where n = number of strings, k = average string length
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Donald Knuth - "Concrete Mathematics" & "The Art of Computer Programming"
 *    - Hash function design principles
 *    - How to create canonical forms for equivalence classes
 * 
 * 2. Thomas Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - HashMap performance analysis
 *    - Sorting vs. counting-based approaches comparison
 * 
 * 3. Sedgewick & Wayne - "Algorithms 4th Edition"
 *    - Practical analysis of sorting overhead
 *    - When to prefer non-comparison-based approaches
 * 
 * RELATED PROBLEMS TO PRACTICE (Similar Pattern):
 * - LeetCode 242 (Valid Anagram) - Check if two strings are anagrams
 * - LeetCode 438 (Find All Anagrams) - Find all anagram indices in string
 * - LeetCode 567 (Permutation in String) - Is one string a permutation of other?
 * - LeetCode 1496 (Path Crossing) - Group by equivalence classes
 * - LeetCode 1426 (Counting Elements) - Count elements in same class
 * ============================================================================
 */

