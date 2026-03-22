/**
 * LeetCode 345: Reverse Vowels of a String
 * 
 * PROBLEM: Reverse only the vowels in a string, keep consonants in place
 * Example: "hello" → "holle" (e and o swapped, h and l stay)
 * 
 * APPROACH: Two Pointers (Left and Right)
 * PATTERN: Two-pointer technique for swaps/transformations
 * 
 * KEY INSIGHT:
 * - Use pointers from both ends
 * - Move towards center
 * - When both find vowels: swap them
 * - When one finds non-vowel: skip it
 * 
 * WHY TWO POINTERS?
 * - Only one pass through string
 * - O(n) time, O(1) space (excluding input conversion)
 * - Natural for "collect ends" problems
 */

/**
 * SOLUTION: Two Pointers with In-Place Swaps
 * Time: O(n) | Space: O(n) for char array conversion
 * 
 * ALGORITHM:
 * 1. Two pointers: start (left), end (right)
 * 2. Move start forward until finding vowel
 * 3. Move end backward until finding vowel
 * 4. If both found and start < end: swap them
 * 5. Repeat until pointers meet
 * 
 * COMPLEXITY:
 * - Time: O(n)
 *   Each character visited at most twice (once by each pointer)
 *   Finding vowels takes O(n) total
 * 
 * - Space: O(n)
 *   Need char array because String is immutable in Java
 *   Output string also needs O(n) space
 *   So effectively O(n) for practical purposes
 */
class Solution {
    /**
     * Check if character is a vowel (any case)
     * Vowels: a, e, i, o, u (case-insensitive)
     */
    boolean isVowel(char c) {
        return c == 'a' || c == 'i' || c == 'e' || c == 'o' || c == 'u'
            || c == 'A' || c == 'I' || c == 'E' || c == 'O' || c == 'U';
    }
    
    /**
     * Swap two characters in array at indices x and y
     * Helper method for two-pointer swaps
     */
    void swap(char[] chars, int x, int y) {
        char temp = chars[x];
        chars[x] = chars[y];
        chars[y] = temp;
    }
    
    public String reverseVowels(String s) {
        // INITIALIZATION: Two pointers starting from ends
        int start = 0;
        int end = s.length() - 1;
        
        // Convert to char array (Strings are immutable in Java)
        // This allows in-place modifications
        char[] sChar = s.toCharArray();
        
        // PROCESS: Move pointers towards center
        while (start < end) {
            // STEP 1: Find next vowel from left
            // Skip consonants and other characters
            while (start < s.length() && !isVowel(sChar[start])) {
                start++;
            }
            
            // STEP 2: Find next vowel from right
            // Skip consonants and other characters
            while (end >= 0 && !isVowel(sChar[end])) {
                end--;
            }
            
            // STEP 3: Swap vowels if both found and valid
            // if (start < end) ensures pointers haven't crossed
            if (start < end) {
                swap(sChar, start++, end--);  // Swap and move pointers
            }
        }
        
        // Convert char array back to String
        return new String(sChar);
    }
}

/* ============================================================================
 * EXAMPLE SIMULATION
 * ============================================================================
 * Input: "race car"
 * 
 * Initial: start=0, end=7
 * Positions: r(0) a(1) c(2) e(3) (4) c(5) a(6) r(7)
 *            ^                                   ^
 * 
 * Step 1:
 * - Find left vowel: start moves to 1 (a is vowel)
 * - Find right vowel: end moves to 6 (a is vowel)
 * - Swap: a and a → no change
 * - start=2, end=5
 *
 * Step 2:
 * - Find left vowel: start moves to 3 (e is vowel)
 * - Find right vowel: end at 5 not vowel, moves to... nowhere (space not vowel)
 * - Actually end would stay where it is since end >= 0 condition
 * - Let me recount positions: "race car" = r(0) a(1) c(2) e(3) (4) c(5) a(6) r(7)
 * 
 * Actually let's use clearer example: "hello"
 * Positions: h(0) e(1) l(2) l(3) o(4)
 * 
 * Step 1: start finds e at 1, end finds o at 4 → swap
 * Result: "holle", start=2, end=3
 * 
 * Step 2: start finds nothing (consonants), start > end → exit
 * Result: "holle" ✓
 * ============================================================================
 */

/* ============================================================================
 * COMPLEXITY ANALYSIS
 * ============================================================================
 * Time: O(n)
 *   - Two pointers, each moves at most n positions
 *   - Total movement: O(n) + O(n) = O(n)
 *   - Each character examined at most once by each pointer
 * 
 * Space: O(n)
 *   - char[] conversion: O(n)
 *   - Output String: O(n)
 *   - Input String: O(n) (existing)
 *   - Total: O(n) extra space required
 * 
 * CAN WE DO BETTER?
 * Space-wise: No, need char array or other mutable container
 * (Strings are immutable so we must convert)
 * Time-wise: No, must read every character
 * ============================================================================
 */

/* ============================================================================
 * ALTERNATIVE: Using HashSet for Vowels
 * ============================================================================
 * 
 * Set<Character> vowels = new HashSet<>(
 *     Arrays.asList('a','e','i','o','u','A','E','I','O','U')
 * );
 * 
 * while (start < end) {
 *     while (start < s.length() && !vowels.contains(sChar[start])) start++;
 *     while (end >= 0 && !vowels.contains(sChar[end])) end--;
 *     if (start < end) swap(sChar, start++, end--);
 * }
 * 
 * Same complexity but more flexible for different vowel definitions
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - Two-pointer technique and its applications
 *    - In-place operations and space optimization
 * 
 * 2. Kleinberg & Tardos - "Algorithm Design"
 *    - When to use two pointers
 *    - String manipulation problems
 * 
 * 3. LeetCode String Problems Collection
 *    - Standard two-pointer template
 *    - Vowel/consonant classification patterns
 * 
 * RELATED PROBLEMS TO PRACTICE:
 * - LeetCode 167 (Two Sum II) - Two pointer in sorted array
 * - LeetCode 125 (Valid Palindrome) - Two pointer with filtering
 * - LeetCode 680 (Valid Palindrome II) - Variation with allowance
 * - LeetCode 151 (Reverse Words in String) - Similar reversal
 * ============================================================================
 */