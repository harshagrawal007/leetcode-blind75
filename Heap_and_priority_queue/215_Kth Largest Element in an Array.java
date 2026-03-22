/**
 * LeetCode 215: Kth Largest Element in an Array
 * 
 * PROBLEM: Find the kth largest element (NOT kth distinct, kth if sorted descending)
 * Example: [3,2,1,5,6,4], k=2 → 5 (descending: [6,5,4,3,2,1])
 * 
 * APPROACH: Min-Heap of size k
 * PATTERN: Maintain top-k elements using heap
 * 
 * KEY INSIGHT:
 * - Use a MIN-HEAP to track k largest elements
 * - When heap size exceeds k, remove minimum (smallest of the k)
 * - Final top of heap is kth largest overall
 * - Never need to store more than k elements at once
 * 
 * WHY MIN-HEAP (NOT MAX-HEAP)?
 * - Min-heap top = smallest element in heap
 * - If we maintain k elements, smallest = kth largest overall
 * - Easy to identify what to remove: heap.peek() when overflow
 */

/**
 * SOLUTION: Min-Heap to Maintain Top-K
 * Time: O(n log k) | Space: O(k)
 * 
 * ALGORITHM:
 * For each element:
 * 1. Add to heap: O(log k)
 * 2. If heap size > k: remove minimum: O(log k)
 * 3. After n elements: heap contains k largest
 * 4. Root of min-heap = kth largest!
 * 
 * TIME BREAKDOWN:
 * - Add all n elements: n * O(log k)
 * - Each remove (up to n times): O(log k)
 * - Total: O(n log k)
 * 
 * SPACE BREAKDOWN:
 * - Min-heap size never exceeds k
 * - Space: O(k)
 * 
 * WHEN IS THIS BETTER THAN SORTING?
 * - Sort: O(n log n) time, O(n) space
 * - This: O(n log k) time, O(k) space
 * - If k << n: Much faster! (e.g., k=10, n=1_000_000)
 */
class Solution {
    // Use PriorityQueue as min-heap (default behavior in Java)
    // Min-heap: smallest element at top
    final PriorityQueue<Integer> heap = new PriorityQueue<>();
 
    public int findKthLargest(int[] nums, int k) {
        // STEP 1: Maintain heap of size k (the k largest elements)
        for (int n : nums) {
            // Add current element to heap
            heap.add(n);  // O(log k) average case
            
            // If heap grew too large, remove smallest
            // This ensures we keep only the k largest
            if (heap.size() > k) {
                // Remove the minimum element (smallest of the k)
                // After removal, heap contains the k largest elements
                heap.remove();  // O(log k)
            }
        }
        
        // STEP 2: Root of min-heap = kth largest element
        // Why? We maintain exactly k largest elements
        // The smallest of these k is the kth largest overall
        return heap.peek();
    }
}

/* ============================================================================
 * EXAMPLE SIMULATION
 * ============================================================================
 * Input: [3, 2, 1, 5, 6, 4], k = 2
 * Want: 2nd largest = 5 (after [6, 5, 4, 3, 2, 1])
 * 
 * Process:
 * n=3: heap=[3], size=1 ≤ 2, keep it
 * n=2: heap=[2,3], size=2 ≤ 2, keep both
 * n=1: heap=[1,3], size=2 ≤ 2, removed 2 (min), kept larger ones
 * n=5: heap=[3,5], size=2 ≤ 2, removed 1, added 5
 * n=6: heap=[5,6], size=2 ≤ 2, removed 3, added 6
 * n=4: heap=[5,6], size=2 ≤ 2, removed 4? No! 4 < 5, wouldn't add
 * 
 * Wait, let me redo:
 * n=3: heap=[3] (size=1)
 * n=2: heap=[2,3] (size=2)
 * n=1: heap=[1,3] after add, size=3>2, remove min=1, heap=[2,3]
 * n=5: heap=[2,3,5] after add, size=3>2, remove min=2, heap=[3,5]
 * n=6: heap=[3,5,6] after add, size=3>2, remove min=3, heap=[5,6]
 * n=4: heap=[4,5,6] after add, size=3>2, remove min=4, heap=[5,6]
 * 
 * Final heap: [5,6] with min at top = 5 ✓
 * ============================================================================
 */

/* ============================================================================
 * COMPLEXITY BREAKDOWN
 * ============================================================================
 * Time: O(n log k)
 *   where n = array length, k = k value
 *   - For each element: add (log k) + possible remove (log k)
 *   - Total: n * O(log k) = O(n log k)
 * 
 * Space: O(k)
 *   - Heap never grows beyond k elements
 *   - O(k) is much better than O(n) if k is small
 * 
 * COMPARISON WITH ALTERNATIVES:
 * 
 * 1. Sorting:
 *    Time: O(n log n)  Space: O(1) or O(n)
 *    Better if k ≈ n
 * 
 * 2. QuickSelect:
 *    Time: O(n) average, O(n²) worst
 *    Space: O(log n) recursion
 *    Better for average case, but less predictable
 * 
 * 3. Min-Heap (this solution):
 *    Time: O(n log k)  Space: O(k)
 *    Best when k << n
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - Heaps and priority queues
 *    - Selection problems (finding kth element)
 * 
 * 2. Blum, Floyd, Pratt, Rivest, Tarjan - "Time Bounds for Selection"
 *    - Theoretical analysis of selection algorithms
 *    - Proof that O(n) is achievable (quickselect)
 * 
 * 3. Kleinberg & Tardos - "Algorithm Design"
 *    - When to use heaps vs sorting vs quickselect
 *    - Practical considerations
 * 
 * RELATED PROBLEMS TO PRACTICE:
 * - LeetCode 347 (Top K Frequent Elements) - Frequency-based kth
 * - LeetCode 692 (Top K Frequent Words) - With strings and ties
 * - LeetCode 703 (Kth Largest in Stream) - Streaming version
 * - LeetCode 1985 (Find the Kth Largest Integer) - K-way comparison
 * ============================================================================
 */