import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * LeetCode 2099: Find Subsequence of Length K With the Largest Sum
 *
 * PROBLEM: Given an integer array, return a subsequence of length k that has the
 * largest possible sum, while preserving the original order of elements.
 *
 * KEY INSIGHT:
 * - The subsequence should contain the k largest elements by value, but must
 *   preserve the original relative order.
 * - We can identify which indices belong to the top-k values using a min-heap
 *   of size k, then iterate through the original array and collect elements in order.
 */
class Solution {
    public int[] maxSubsequence(int[] nums, int k) {
    // Create proirity queue with min priority queue so that min element will be removed first, with index
    // Add those unique index in a set
    // Loop from 0 to n-1 and add element in result if set contains those index
    // For ex. set has index 3,5,6 Just add those element. Order will be maintained
    // We are defining the min priority queue, sorting based of number in array
    PriorityQueue<int[]> q = new PriorityQueue<>((a,b)-> (a[0]-b[0])); 
    // Add element with index to priority queue
    for(int i=0; i<nums.length; i++) {
        q.offer(new int[]{nums[i],i});
        if(q.size()> k) {
            q.poll();
        }
    }
    
    // Set to keep index, // Add the index in the set since index are unique 
    Set<Integer> indexSet = new HashSet<>();
    while(!q.isEmpty()){
        int[] top = q.poll();
        indexSet.add(top[1]);
    }
    
    // Final result add here
    // Just add the element in the result for those index present in SET
    int[] result = new int[k];
    int p =0;
    for(int i=0; i< nums.length; i++) {
        if(indexSet.contains(i))  {
            result[p] = nums[i];
            ++p;
        }
    }
    return result;
    }
}

/* ==========================================================================
 * COMPLEXITY ANALYSIS
 * ==========================================================================
 * Time: O(n log k)
 * Space: O(k)
 *
 * - Building the heap: O(n log k) (each of the n elements is pushed/popped at most once)
 * - Collecting the result in order: O(n)
 *
 * Note: Alternative approaches include selecting the k-th largest value via Quickselect
 * and then scanning to build the subsequence in order.
 * ========================================================================== */

/* ==========================================================================
 * KEY CITATIONS & REFERENCES
 * ==========================================================================
 * 1. LeetCode discussion: "Find Subsequence of Length K With the Largest Sum".
 * 2. Heap-based selection pattern (maintain top-k elements).
 * 3. Related problem: "Find Kth Largest Element" (quickselect / heap).
 * ========================================================================== */
