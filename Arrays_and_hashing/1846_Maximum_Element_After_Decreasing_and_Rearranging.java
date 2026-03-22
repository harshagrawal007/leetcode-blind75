/**
 * LeetCode 1846: Maximum Element After Decreasing and Rearranging
 *
 * PROBLEM: Given an array, you may decrease any element to any smaller positive integer
 * and rearrange the array. Return the maximum possible value of the maximum element.
 *
 * KEY INSIGHT:
 * - The optimal arrangement is to create [1, 2, 3, ..., k] for largest k.
 * - Any number > n can be reduced to n; anything < 1 is invalid.
 * - Use counting to determine how far you can extend the sequence.
 */

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;

        // counts[x] = number of elements that can be turned into x (x <= n)
        int[] counts = new int[n + 1];
        for (int num : arr) {
            counts[Math.min(num, n)]++;
        }

        // Try to build values 1, 2, 3, ... in order
        int maxValue = 1;
        for (int value = 2; value <= n; value++) {
            maxValue = Math.min(maxValue + counts[value], value);
        }

        return maxValue;
        }
}
// Initialize an array counts with length n + 1 and values of 0.
// Iterate over each num in arr:
// Increment counts[min(num, n)].
// Initialize ans = 1.
// Iterate num from 2 to n:
// Set ans = min(ans + counts[num], num).
// Return ans.

/* ==========================================================================
 * EXPLANATION (step-by-step)
 * --------------------------------------------------------------------------
 * 1) Any valid rearranged array must have its maximum element <= n, because
 *    we can always decrease larger values down to at most n (array size).
 * 2) The best possible maximum is achieved by forming the longest prefix
 *    [1, 2, 3, ..., k] (because each position must be at least 1 higher than the
 *    previous to allow the maximum to grow).
 * 3) We count how many numbers can contribute to each target value x (after
 *    decreasing): any arr[i] >= x can be reduced to x. We bucket those counts
 *    using the min(arr[i], n) trick to cap values at n.
 * 4) Then we scan values from 1..n, greedily using as many available numbers as
 *    possible to build the increasing sequence. The sequence can only extend to
 *    `value` if we have at least 1 number available at that `value` (or higher).
 *
 * The algorithm is effectively a prefix-sum scan over the frequency buckets.
 * ========================================================================== */

/* ==========================================================================
 * COMPLEXITY ANALYSIS
 * ==========================================================================
 * Time: O(n)
 * Space: O(n)
 *
 * - Building the frequency buckets: O(n)
 * - Scanning values 1..n to determine the maximum achievable value: O(n)
 *
 * This is optimal since we must inspect each input element at least once.
 * ========================================================================== */

/* ==========================================================================
 * KEY CITATIONS & REFERENCES
 * ==========================================================================
 * 1. LeetCode discussion: "Maximum Element After Decreasing and Rearranging"
 * 2. Greedy counting strategies for building 1..k sequences (common in contest
 *    problems such as “maximize smallest value” and “make array consecutive”).
 * 3. Competitive programming pattern: bucket counting + prefix accumulation.
 * ========================================================================== */




/* 
Given nnn as the length of arr,

Time complexity: O(n)

To calculate counts, we iterate over arr once which costs O(n).
Then, we iterate between 2 and n. which costs O(n).

Space complexity: O(n)
counts has a length of n+1 .
*/

//with sorting

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int ans = 1;
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= ans + 1) {
                ans++;
            }
        }
        
        return ans;
    }
}
