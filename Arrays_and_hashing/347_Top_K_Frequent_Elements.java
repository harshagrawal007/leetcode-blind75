/**
 * LeetCode 347: Top K Frequent Elements
 * 
 * PROBLEM: Find the k most frequent elements in an array
 * Example: [1,1,1,2,2,3], k=2 → [1,2]
 * 
 * APPROACH: Min-Heap to maintain top K elements
 * PATTERN: Frequency counting + heap for top-K elements
 * 
 * KEY INSIGHT:
 * 1. Count frequency of each number using HashMap
 * 2. Use a min-heap of size k to track k most frequent
 * 3. When heap exceeds size k, remove minimum (least frequent)
 * 4. Final heap contains the k most frequent elements
 * 
 * WHY MIN-HEAP (NOT MAX-HEAP):
 * - Min-heap lets us easily identify and remove the least frequent
 * - We maintain exactly k elements in heap
 * - When new element comes, if freq > min in heap, replace min
 * - This is more efficient than building full max-heap
 */

/**
 * SOLUTION: Min-Heap (Optimal for large arrays)
 * 
 * Complexity: Time O(n log k) | Space O(n) for HashMap, O(k) for heap
 * 
 * TIME BREAKDOWN:
 * - Build frequency map: O(n) to iterate and count
 * - Insert/maintain heap: O(m log k)
 *   where m = unique numbers (≤ n)
 *   Each insert: O(log k) since heap size ≤ k
 * - Extract result: O(k log k) to pop k elements from heap
 * - Total: O(n + m log k + k log k) = O(n log k)
 *
 * SPACE BREAKDOWN:
 * - HashMap: O(m) = O(n) worst case (all unique)
 * - Min-heap: O(k) fixed size
 * - Result array: O(k)
 * - Total: O(n + k)
 * 
 * WHY THIS IS OPTIMAL FOR LARGE ARRAYS:
 * - If k << n: heap operations dominate, giving us O(n log k)
 * - Space: only O(n + k) instead of O(n) for full sort
 * - Perfect for streaming data where n is unknown
 */
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        // EDGE CASES: Input array has k or fewer elements
        // All elements are in top k
        if (nums.length == 0 || nums.length == 1 || nums.length == k) {
            return nums;
        }
        
        // STEP 1: Count frequency of each number
        // HashMap<number, frequency>
        HashMap<Integer, Integer> mymap = new HashMap<>();
        for (int num : nums) {
            mymap.put(num, mymap.getOrDefault(num, 0) + 1);
        }

        // STEP 2: Use a MIN-HEAP to track top K frequent elements
        // MinHeap with custom comparator based on frequency
        // Comparator: e1.getValue() - e2.getValue()
        // This creates a min-heap (smallest frequency at top)
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = 
            new PriorityQueue<>((e1, e2) -> e1.getValue() - e2.getValue());

        // STEP 3: Maintain heap size = K
        // Strategy: Go through all numbers and push them into heap
        // If heap size exceeds K, remove element with minimum frequency
        // This ensures heap always contains K most frequent elements
        for (Map.Entry<Integer, Integer> entry : mymap.entrySet()) {
            minHeap.add(entry);  // Add current entry
            
            // If heap grew too large, remove least frequent
            if (minHeap.size() > k) {
                // minHeap.peek() = minimum frequency element
                // minHeap.poll() = remove and return it
                minHeap.poll();
            }
        }

        // STEP 4: Extract top k elements from heap
        // Heap contains the k most frequent elements (order doesn't matter)
        int[] topNumbers = new int[k];
        int i = 0;
        while (!minHeap.isEmpty()) {
            topNumbers[i] = minHeap.poll().getKey();
            i++;
        }
        
        return topNumbers;
    }
}

/* ============================================================================
 * ALTERNATIVE APPROACHES
 * ============================================================================
 * 
 * 1. MAX-HEAP (Less Efficient)
 *    Time: O(n log m)  where m = unique elements
 *    Space: O(m + k)
 *    
 *    Problem: We'd build full heap of all m elements, then extract k times
 *    Heap building: O(m)
 *    Extracting k elements: O(k log m)
 *    Total: O(m + k log m) = O(n log n) worst case
 *    Worse than min-heap approach when k << n
 * 
 * 2. SORT (Simple but Slower)
 *    Time: O(m log m) where m = unique elements
 *    Space: O(m + k)
 *    
 *    freq_map → sorted by frequency → take top k
 *    Problem: Sorting all m frequencies wastes time if k << m
 *    Good only when k is close to m
 * 
 * 3. BUCKET SORT (Alternative Optimal)
 *    Time: O(n)  Space: O(n + k)
 *    
 *    Use array buckets indexed by frequency (0 to n)
 *    bucket[freq] = list of elements with that frequency
 *    Collect from highest bucket: O(n)
 *    
 *    Advantages: O(n) true linear time
 *    Disadvantages: More space, less cache friendly
 * 
 * ============================================================================
 */

/* ============================================================================
 * COMPLEXITY ANALYSIS SUMMARY
 * ============================================================================
 * 
 * The given solution:
 * Time: O(n) to build the HashMap 
 *     + O(m log k) to push m entries into a heap of size ≤ k
 *     → O(n + m log k)
 *     → In the worst case m = n, so O(n log k)
 * 
 * Space: O(m) for the HashMap 
 *      + O(k) for the heap 
 *      + O(k) output 
 *      → O(m + k), typically O(n) in worst case
 * 
 * WHEN k IS SMALL (k << n):
 * - This is MUCH better than sorting: O(n log k) vs O(n log n)
 * - For example, n=1,000,000, k=10
 *   - This approach: 1,000,000 * log(10) ≈ 3.3M operations
 *   - Sort approach: 1,000,000 * log(1,000,000) ≈ 20M operations
 * 
 * WHEN k ≈ n:
 * - Difference becomes negligible
 * - Both approaches become O(n log n)
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Cormen, Leiserson, Rivest, Stein - "Introduction to Algorithms (CLRS)"
 *    - Heaps and priority queues chapter
 *    - Selection problem and top-k algorithms
 * 
 * 2. Skiena, Steven S. - "The Algorithm Design Manual"
 *    - Practical applications of heaps
 *    - When to use different heap strategies
 * 
 * 3. Timothy H. Cormen et al. - "Introduction to Algorithms"
 *    - Detailed analysis of selection algorithms
 *    - Heap-based approach for top-k elements
 * 
 * RELATED PROBLEMS TO PRACTICE (Top-K Patterns):
 * - LeetCode 215 (Kth Largest Element) - Single element version
 * - LeetCode 692 (Top K Frequent Words) - With strings and ties
 * - LeetCode 997 (Find Judge of Town) - Degree-based filtering
 * - LeetCode 1030 (Matrix Cells in Distance Order) - Sorting by distance
 * - LeetCode 1094 (Car Pooling) - Capacity constraint variant
 * ============================================================================
 */


class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length == 0) return new int[0];

        // count frequencies
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int x : nums) freq.put(x, freq.getOrDefault(x, 0) + 1);

        // bucket by frequency: index = frequency, size = nums.length + 1
        List<Integer>[] buckets = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            int f = e.getValue();
            if (buckets[f] == null) buckets[f] = new ArrayList<>();
            buckets[f].add(e.getKey());
        }

        // collect top k from highest frequency down
        int[] res = new int[k];
        int idx = 0;
        for (int i = buckets.length - 1; i >= 0 && idx < k; i--) {
            if (buckets[i] == null) continue;
            for (int val : buckets[i]) {
                res[idx++] = val;
                if (idx == k) return res;
            }
        }
        return res;
    }
}

/* 
Bucket-by-frequency solution I provided

Time: O(n) to count frequencies + O(n) to place items into buckets + O(n) to scan buckets → O(n).
Space: O(n) for the buckets array (size n+1) + O(m) for the frequency map + O(k) output → O(n + m) (which is O(n)).

*/