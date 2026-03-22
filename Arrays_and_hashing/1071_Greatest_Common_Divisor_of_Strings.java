/**
 * LeetCode 1071: Greatest Common Divisor of Strings
 *
 * PROBLEM: Find the largest string X such that X is a prefix of both str1 and str2,
 * and str1 and str2 can be formed by repeating X multiple times.
 *
 * KEY INSIGHT:
 * - If str1 + str2 != str2 + str1, there is no such common divisor string.
 * - If they are equal, the GCD string length is gcd(len(str1), len(str2)).
 * - Then return the prefix of that length.
 *
 * WHY THE CONCATENATION CHECK WORKS:
 * - If both strings are composed of the same repeating pattern, concatenation in either
 *   order yields the same combined string.
 * - Otherwise, the patterns are incompatible.
 *
 * EXAMPLE:
 * str1 = "ABCABC", str2 = "ABC"
 * str1 + str2 = "ABCABCABC"
 * str2 + str1 = "ABCABCABC" → EQUAL
 * gcd(6, 3) = 3
 * Result: str1.substring(0, 3) = "ABC" ✓
 */

/**
 * SOLUTION 1: Concatenation Check + GCD (Optimal)
 * Time: O(n + m) | Space: O(n + m)
 *
 * APPROACH:
 * 1) Check concatenation equality: (str1 + str2).equals(str2 + str1)
 * 2) Compute gcd(len1, len2) using Euclidean algorithm
 * 3) Return prefix of length gcd
 */
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        // Step 1: Quick rejection using concatenation property
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        // Step 2: Compute gcd of lengths
        int gcdLen = gcd(str1.length(), str2.length());

        // Step 3: Return prefix of length gcdLen
        return str1.substring(0, gcdLen);
    }

    // Euclidean algorithm for gcd (recursive)
    private int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }
}

/**
 * SOLUTION 2: Brute Force Approach (For Understanding)
 * Time: O(n * (n + m)) | Space: O(1)
 *
 * APPROACH:
 * - Try every prefix of str1 (from longest to shortest)
 * - Check if this prefix divides both str1 and str2 evenly
 * - Return the first (longest) valid prefix found
 *
 * WHY THIS WORKS:
 * - If a string X divides both str1 and str2, then:
 *   - str1 can be formed by repeating X: str1 = X + X + ... + X
 *   - str2 can be formed by repeating X: str2 = X + X + ... + X
 * - We find the longest such X by searching from longest prefixes first
 *
 * DRAWBACK:
 * - Much slower than GCD approach (O(n²) vs O(n))
 * - Good for understanding the problem
 * - Educational: Shows necessity of the GCD/concatenation trick
 */
class Solution2 {
    public String gcdOfStrings(String str1, String str2) {
        // Ensure str1 is the longer string
        if (str1.length() < str2.length()) {
            return gcdOfStrings(str2, str1);
        }

        // Base case: if str2 is empty, str1 is the GCD
        if (str2.isEmpty()) {
            return str1;
        }

        // Check if str1 starts with str2
        // If not, they can't share a common divisor
        if (!str1.startsWith(str2)) {
            return "";
        }

        // Recursively check: remove str2 from the beginning of str1
        return gcdOfStrings(str2, str1.substring(str2.length()));
    }
}


class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        int gcdLength = gcd(str1.length(), str2.length());
        return str1.substring(0, gcdLength);
    }
    
    public int gcd(int x, int y) {
            if (y == 0) {
                return x;
            } else {
                return gcd(y, x % y);
            }    
    }
}
/**
 * SOLUTION 3: Iterative GCD (Alternative Implementation)
 * Time: O(n + m) | Space: O(n + m)
 *
 * Same logic as Solution 1, but using iterative GCD instead of recursive
 * - Both achieve the same time complexity
 * - Iterative avoids potential stack overflow on very long strings
 */
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        // Compute GCD iteratively (Euclidean algorithm)
        int len1 = str1.length();
        int len2 = str2.length();
        while (len2 != 0) {
            int temp = len2;
            len2 = len1 % len2;
            len1 = temp;
        }

        return str1.substring(0, len1);
    }
}


/* ============================================================================
 * COMPLEXITY COMPARISON: ALL SOLUTIONS
 * ============================================================================
 * Solution 1 (Concatenation + Recursive GCD):
 *   Time: O(n + m)  | Space: O(n + m) for concatenated strings
 *   BEST APPROACH - Fast and elegant
 *
 * Solution 2 (Brute Force Recursive):
 *   Time: O(n * (n + m))  | Space: O(1)
 *   Recursive calls can stack up
 *   Educational: Shows why concatenation trick is smart
 *
 * Solution 3 (Concatenation + Iterative GCD):
 *   Time: O(n + m)  | Space: O(n + m)
 *   Same complexity as Solution 1
 *   Slightly more explicit GCD computation
 *   Better for very long strings (avoids stack overflow)
 * ============================================================================
 */

/* ============================================================================
 * SIMULATION: How the Solutions Work
 * ============================================================================
 * Input: str1 = "ABABAB", str2 = "AB"
 *
 * Solution 1 (Concatenation + GCD):
 * - str1 + str2 = "ABABAB" + "AB" = "ABABAB|AB"
 * - str2 + str1 = "AB" + "ABABAB" = "AB|ABABAB"
 * - Are they equal? NO (vertical bars show position)
 * - Wait, let me recalculate: "ABABAB" + "AB" = "ABABABAB"
 *                            "AB" + "ABABAB" = "ABABAB"
 * - Actually let me use a clearer example:
 *
 * Input: str1 = "ABAB", str2 = "AB"
 * - str1 + str2 = "ABAB" + "AB" = "ABABAB"
 * - str2 + str1 = "AB" + "ABAB" = "ABABAB" ✓ EQUAL
 * - gcd(4, 2) = 2
 * - str1.substring(0, 2) = "AB" ✓
 *
 * Solution 2 (Brute Force):
 * - Try prefix "ABAB": divides ABAB? Yes. Divides AB? NO - skip
 * - Try prefix "ABA": divides ABAB? NO - skip
 * - Try prefix "AB": divides ABAB? Yes (ABAB = AB + AB). Divides AB? Yes - FOUND!
 * - Return "AB" ✓
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. LeetCode 1071: Greatest Common Divisor of Strings
 *    - Common insight: concatenation property for repeating patterns
 *
 * 2. Euclid's Algorithm (Ancient Greek Mathematics)
 *    - Efficient GCD computation (3rd century BC)
 *    - Foundation for many number theory algorithms
 *
 * 3. String Pattern Problems
 *    - Related: LeetCode 28 (Strstr), 459 (Repeated Substring Pattern)
 *    - Pattern matching and repeating structures
 *
 * RELATED PROBLEMS TO PRACTICE:
 * - LeetCode 459 (Repeated Substring Pattern) - Similar GCD string concept
 * - LeetCode 267 (Palindrome Permutation II) - String manipulation
 * - LeetCode 387 (First Unique Character) - Frequency-based string problems
 * ============================================================================
 */
