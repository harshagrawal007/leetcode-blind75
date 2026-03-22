/**
 * LeetCode 56: Merge Intervals
 * 
 * PROBLEM: Merge overlapping intervals
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * 
 * APPROACH: Sort by start time, then merge overlapping ones
 * PATTERN: Greedy + sorting for interval problems
 * 
 * KEY INSIGHT:
 * - Sort by start time first
 * - Iterate through sorted intervals
 * - If current start ≤ prev end: overlap! Merge by extending prev end
 * - Else: no overlap, add as new interval
 */

/**
 * SOLUTION 1: ArrayList (Recommended)
 * Time: O(n log n) for sorting | Space: O(n) for result
 * 
 * Why sort?: Overlaps are only detectable when sorted by start time
 * After sort, adjacent intervals determine if they overlap
 */
class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> ans = new ArrayList<>();
        
        // STEP 1: Sort by start time
        // After sort: intervals[i][0] is non-decreasing
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        // STEP 2: Add first interval
        ans.add(intervals[0]);
        
        // STEP 3: Merge remaining intervals
        for (int i = 1; i < intervals.length; i++) {
            int curStart = intervals[i][0];
            int prevEnd = ans.get(ans.size() - 1)[1];
            
            // Check if current overlaps with previous
            // Overlap if: curStart <= prevEnd
            if (curStart <= prevEnd) {
                // Merge: extend previous interval's end to max of both ends
                ans.get(ans.size() - 1)[1] =
                    Math.max(prevEnd, intervals[i][1]);
            } else {
                // No overlap: add as new interval
                ans.add(intervals[i]);
            }
        }
        
        return ans.toArray(new int[ans.size()][]);
    }
}



/**
 * SOLUTION 2: Cleaner ArrayList Implementation
 * Time: O(n log n) | Space: O(n)
 * 
 * Same complexity, slightly cleaner code
 */
class Solution2 {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) return new int[0][];
        
        List<int[]> ans = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        ans.add(intervals[0]);
        
        for (int i = 1; i < intervals.length; i++) {
            int[] last = ans.get(ans.size() - 1);
            // Clearer variable names: current vs last
            if (intervals[i][0] <= last[1]) {
                // Merge by updating last interval's end
                last[1] = Math.max(last[1], intervals[i][1]);
            } else {
                // No overlap, add new interval
                ans.add(intervals[i]);
            }
        }
        
        return ans.toArray(new int[ans.size()][]);
    }
}

/* 
Both solutions correctly solve the Merge Intervals problem with the same time 
complexity (O(n log n) due to sorting) and space complexity (O(n) for the output list). 
However, there are subtle differences in code clarity, efficiency, and style.
*/

/**
 * SOLUTION 3: LinkedList Implementation
 * Time: O(n log n) | Space: O(n)
 * 
 * ADVANTAGE: getLast() semantically clearer than get(size()-1)
 * DISADVANTAGE: LinkedList slightly slower than ArrayList for random access
 * 
 * When to use LinkedList: When code clarity is more important than raw speed
 */
class Solution3 {
    public int[][] merge(int[][] intervals) {
        // STEP 1: Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        
        // STEP 2: Use LinkedList for cleaner last element access
        LinkedList<int[]> merged = new LinkedList<>();
        
        for (int[] interval : intervals) {
            // Check if list is empty OR if current doesn't overlap with last
            // Overlap condition: current.start <= last.end
            // Non-overlap if: current.start > last.end
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                // No overlap: append new interval
                merged.add(interval);
            } else {
                // Overlap: merge by extending last interval's end
                merged.getLast()[1] = Math.max(
                    merged.getLast()[1],
                    interval[1]
                );
            }
        }
        
        return merged.toArray(new int[merged.size()][]);
    }
}

/* ============================================================================
 * COMPLEXITY ANALYSIS
 * ============================================================================
 * All Solutions:
 *   Time: O(n log n) dominated by sorting
 *     - Sort: O(n log n)
 *     - Merge loop: O(n)
 *     - Total: O(n log n)
 *   
 *   Space: O(n) for output list
 *     - Some sort implementations use O(log n) extra space
 *     - Result array: O(n) merged intervals
 * 
 * OPTIMAL?
 * - Must examine every interval = O(n) lower bound
 * - Must sort to enable merging = O(n log n) for comparison sort
 * - Can't do better than O(n log n) with comparison-based sort
 * - Therefore: this is OPTIMAL
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Cormen, Leiserson, Rivest, Stein - "Introduction to Algorithms (CLRS)"
 *    - Greedy algorithms and correctness proofs
 *    - Sorting and interval problems
 * 
 * 2. Steven Skiena - "The Algorithm Design Manual"
 *    - Interval scheduling and greedy approaches
 *    - When sorting is necessary for correctness
 * 
 * 3. Kleinberg & Tardos - "Algorithm Design"
 *    - Interval graph problems
 *    - Greedy interval scheduling proofs
 * 
 * RELATED PROBLEMS TO PRACTICE:
 * - LeetCode 57 (Insert Interval) - Insert into merged intervals
 * - LeetCode 252 (Meeting Rooms) - Can attend all meetings?
 * - LeetCode 253 (Meeting Rooms II) - Min rooms needed
 * - LeetCode 435 (Non-overlapping Intervals) - Max non-overlapping
 * - LeetCode 436 (Find Right Interval) - Binary search variant
 * ============================================================================
 */

