/**
 * LeetCode 151: Reverse Words in a String
 * 
 * PROBLEM: Reverse the order of words in a string
 * However, reverse words themselves (e.g., "hello" stays "hello")
 * Example: "  hello world  " → "world hello"
 * 
 * CONSTRAINTS:
 * - Handle multiple spaces between words
 * - Trim leading/trailing spaces from output
 * - Words are sequences of non-space characters
 * 
 * APPROACH: Split, Iterate Backwards, Rebuild
 * PATTERN: String manipulation with StringBuilder
 * 
 * KEY INSIGHT:
 * 1. Split by space delimiter → array of words
 * 2. Iterate array backwards to reverse word order
 * 3. Skip empty strings (from multiple spaces)
 * 4. Use StringBuilder for efficiency
 * 5. Trim final result to remove trailing space
 */

/**
 * SOLUTION: Split, Reverse Order, Rebuild (Simple & Readable)
 * Time: O(n) | Space: O(n) for StringBuilder and array
 * 
 * ALGORITHM:
 * 1. trim() input to remove leading/trailing spaces
 * 2. split(" ") on space delimiter
 *    - Creates array of all words and empty strings
 * 3. Iterate backwards through array
 *    - Skip empty strings (from consecutive spaces)
 *    - Append non-empty words to StringBuilder
 *    - Add space after each word
 * 4. trim() final result to remove trailing space
 * 
 * COMPLEXITY:
 * - Time: O(n) where n = string length
 *   Operations: trim O(n) + split O(n) + loop O(n) + join O(n)
 * - Space: O(n) for array of words + StringBuilder
 * 
 * WHY SUBSTRING-BASED (NOT CHAR-BASED)?
 * - Words are variable-length entities
 * - Easier to think in terms of words
 * - String API handles edge cases (whitespace)
 */
class Solution {
    public String reverseWords(String s) {
        // STEP 1: Extract words (removing extra spaces)
        // split(" ") on single space creates entries for "" between spaces
        // Example: "a  b".split(" ") = ["a", "", "b"]
        String[] strArray = s.trim().split(" ");
        
        // STEP 2: Rebuild string in reverse order
        StringBuilder sb = new StringBuilder();
        
        // ITERATE BACKWARDS from last word to first
        for (int i = strArray.length - 1; i >= 0; i--) {
            // SKIP EMPTY ENTRIES
            // Created by split() when there are multiple consecutive spaces
            if (!strArray[i].isEmpty()) {
                // Append word and space for separation
                sb.append(strArray[i]).append(" ");
            }
        }
        
        // STEP 3: Remove trailing space and return
        // Last word followed by space, so trim() removes it
        return sb.toString().trim();
    }
}

/* ============================================================================
 * EXAMPLE SIMULATION
 * ============================================================================
 * Input: "  hello   world  "
 * 
 * Step 1 (trim):
 * After trim(): "hello   world"
 * 
 * Step 2 (split):
 * split(" ") = ["hello", "", "", "world"]
 * (Empty strings from the three consecutive spaces)
 * 
 * Step 3 (iterate backwards):
 * i=3: strArray[3]="world" ≠ empty → append "world "
 * i=2: strArray[2]="" = empty → skip
 * i=1: strArray[1]="" = empty → skip
 * i=0: strArray[0]="hello" ≠ empty → append "hello "
 * 
 * Step 4 (trim):
 * Result: "world hello " → after trim → "world hello" ✓
 * ============================================================================
 */

/* ============================================================================
 * ALTERNATIVE APPROACHES
 * ============================================================================
 * 
 * 1. SPLIT WITH REGEX (More Robust for Whitespace)
 *    split("\\s+") matches one or more spaces
 *    
 *    String[] words = s.trim().split("\\s+");
 *    StringBuilder sb = new StringBuilder();
 *    for (int i = words.length - 1; i >= 0; i--) {
 *        if (i > 0) sb.append(" ");
 *        sb.append(words[i]);
 *    }
 *    return sb.toString();
 *    
 *    Better: No empty check needed!
 *    Problem: Regex slower than simple split for this case
 * 
 * 2. DEQUE APPROACH (Also O(n) time)
 *    
 *    Deque<String> deque = new LinkedList<>(
 *        Arrays.asList(s.trim().split("\\s+"))
 *    );
 *    return String.join(" ", deque);
 *    
 *    Better: Can reverse naturally
 *    Problem: More memory, Collections overhead
 * 
 * 3. CHARACTER-LEVEL MANIPULATION (Low-level)
 *    Reverse in-place, then reverse words back
 *    
 *    Time: O(n) | Space: O(1) if allowed to modify input
 *    Problem: Complex, not worth it for string problems
 * ============================================================================
 */

/* ============================================================================
 * COMPLEXITY ANALYSIS
 * ============================================================================
 * Time: O(n)
 *   - trim(): O(n) scan from ends
 *   - split(): O(n) create array
 *   - backward loop: O(n) iterate and append
 *   - Total: 3*O(n) = O(n)
 * 
 * Space: O(n)
 *   - Input string: O(n) (given)
 *   - String array: O(n) max (all words)
 *   - StringBuilder: O(n) max (output)
 *   - Total: O(n) required
 * 
 * CANNOT DO BETTER:
 * - Must read entire string = O(n) lower bound
 * - Output requires O(n) space
 * - This is optimal
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Joshua Bloch et al. - "Effective Java"
 *    - When and how to use StringBuilder
 *    - String splitting and manipulation patterns
 * 
 * 2. Oracle Java Documentation
 *    - String.split() behavior with edge cases
 *    - StringBuilder performance characteristics
 * 
 * 3. LeetCode String Problems
 *    - Common string manipulation patterns
 *    - When to use built-in methods vs custom logic
 * 
 * RELATED PROBLEMS TO PRACTICE:
 * - LeetCode 157 (Read N Characters Given Read4) - String I/O
 * - LeetCode 58 (Length of Last Word) - Simpler variant
 * - LeetCode 1421 (NPV of String) - Parsing variant
 * - LeetCode 2000 (Reverse Prefix) - Substring reversal
 * ============================================================================
 */