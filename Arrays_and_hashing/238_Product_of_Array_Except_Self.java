/**
 * LeetCode 238: Product of Array Except Self
 * 
 * PROBLEM: For each element, compute product of all OTHER elements
 * Example: [1,2,3,4] → [24,12,8,6]
 *          - Index 0: 2*3*4=24
 *          - Index 1: 1*3*4=12
 *          - Index 2: 1*2*4=8
 *          - Index 3: 1*2*3=6
 * 
 * CONSTRAINT: O(n) time, O(1) space (excluding output array)
 * NO division allowed
 * 
 * APPROACH: Prefix & Postfix Products (Left and Right traversal)
 * PATTERN: Split problem into left products and right products
 * 
 * KEY INSIGHT:
 * - For index i: answer = (product of all left) * (product of all right)
 * - Precompute left products: leftProd[i] = product of [0...i-1]
 * - Precompute right products: rightProd[i] = product of [i+1...n-1]
 * - Result[i] = leftProd[i] * rightProd[i]
 */

/**
 * SOLUTION 1: Two-Pass Approach with Single Array
 * Time: O(n) | Space: O(1) extra (excluding output)
 * 
 * ALGORITHM:
 * PASS 1 (Left → Right): Store left products in result array
 *   result[i] = product of all elements to the left of i
 *   result[0] = 1 (nothing to left)
 *   result[1] = nums[0]
 *   result[2] = nums[0] * nums[1]
 *   ...
 * 
 * PASS 2 (Right → Left): Multiply by right products
 *   Use postfix variable to track right product
 *   result[i] *= postfix
 *   postfix *= nums[i]
 * 
 * SPACE EFFICIENCY: Reuse result array, don't need separate left/right arrays
 */
public class ProductOfArrayExceptSelf {
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // PASS 1: Compute left products (product of all elements to the left)
        // result[i] = product of nums[0...i-1]
        int prefix = 1;
        for (int i = 0; i < n; i++) {
            result[i] = prefix;      // Store product of all left elements
            prefix *= nums[i];        // Update prefix for next iteration
        }
        // After pass 1: result = [1, nums[0], nums[0]*nums[1], ...]

        // PASS 2: Compute right products and multiply (product of all elements to the right)
        // Traverse right to left, multiply result[i] by product of all right elements
        int postfix = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= postfix;    // Multiply left product by right product
            postfix *= nums[i];       // Update postfix for next iteration
        }
        
        return result;
    }
}

/**
 * SOLUTION 2: Alternative Implementation (Functionally Identical)
 * Time: O(n) | Space: O(1) extra
 * 
 * Same algorithm, slightly different variable naming
 */
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        
        // PASS 1 (Left → Right): Compute left products
        result[0] = 1;  // Base case: nothing to the left of index 0
        for (int i = 1; i < n; i++) {
            // result[i] = product of all elements before index i
            result[i] = nums[i - 1] * result[i - 1];
        }
        // After pass 1: result[i] = product of nums[0...i-1]

        // PASS 2 (Right → Left): Compute right products and combine
        int rightProduct = 1;  // Track product of all elements to the right
        for (int i = n - 1; i >= 0; i--) {
            // result[i] = (product of left) * (product of right)
            result[i] = result[i] * rightProduct;
            rightProduct = rightProduct * nums[i];
        }
        
        return result;
    }
}

/* ============================================================================
 * EXAMPLE SIMULATION
 * ============================================================================
 * Input: [1, 2, 3, 4]
 * 
 * PASS 1 (Left Products):
 * i=0: result[0]=1, prefix=1*1=1
 * i=1: result[1]=1, prefix=1*2=2
 * i=2: result[2]=2, prefix=2*3=6
 * i=3: result[3]=6, prefix=6*4=24
 * Result after pass 1: [1, 1, 2, 6]
 * 
 * PASS 2 (Right Products):
 * i=3: result[3]=6*1=6, postfix=1*4=4
 * i=2: result[2]=2*4=8, postfix=4*3=12
 * i=1: result[1]=1*12=12, postfix=12*2=24
 * i=0: result[0]=1*24=24, postfix=24*1=24
 * Result after pass 2: [24, 12, 8, 6] ✓
 * ============================================================================
 */

/* ============================================================================
 * COMPLEXITY BREAKDOWN
 * ============================================================================
 * Time: O(n)
 *   - Pass 1: n iterations
 *   - Pass 2: n iterations
 *   - Total: 2n = O(n)
 * 
 * Space: O(1)
 *   - Output array doesn't count (required for output)
 *   - Extra variables: prefix, postfix, i, n = O(1)
 *   - No additional data structures
 * 
 * WHY THIS IS OPTIMAL:
 * - Must examine each element at least once = O(n) lower bound
 * - This achieves that bound
 * - Space: O(1) is best possible
 * ============================================================================
 */

/* ============================================================================
 * WHY NOT USE DIVISION?
 * ============================================================================
 * The problem explicitly forbids division. Why?
 * 
 * Bad approach (won't work):
 * ```
 * int totalProduct = 1;
 * for (int num : nums) totalProduct *= num;
 * for (int i = 0; i < n; i++) {
 *     result[i] = totalProduct / nums[i];  // FORBIDDEN
 * }
 * ```
 * Problems:
 * 1. What if totalProduct = 0? (Division by zero)
 * 2. What if array has a zero element?
 * 3. Integer division loses precision
 * 4. Constraint explicitly forbids it!
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - Dynamic programming prefix/suffix products
 *    - Space-efficient array processing
 * 
 * 2. Kleinberg & Tardos - "Algorithm Design"
 *    - Left and right traversal techniques
 *    - Two-pass algorithms for property computation
 * 
 * 3. LeetCode Discussion Forums
 *    - Elegant two-pass solution patterns
 *    - Common interview gotchas (division, zeros)
 * 
 * RELATED PROBLEMS TO PRACTICE:
 * - LeetCode 152 (Maximum Product Subarray) - Max product of contiguous
 * - LeetCode 31 (Next Permutation) - Left-right traversal pattern
 * - LeetCode 334 (Increasing Triplet) - Left-right scanning
 * - LeetCode 406 (Queue Reconstruction) - Order-dependent processing
 * ============================================================================
 */
