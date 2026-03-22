/**
 * LeetCode 21: Merge Two Sorted Lists
 * <p>
 * PROBLEM: Merge two sorted linked lists into one sorted linked list.
 * Return the head of the merged linked list.
 * <p>
 * Example: list1 = [1,2,4], list2 = [1,3,4] → [1,1,2,3,4,4]
 * <p>
 * APPROACH: Iterative merge with dummy node
 * PATTERN: Two-pointer merge for sorted sequences
 * <p>
 * KEY INSIGHT:
 * - Use dummy node to simplify head handling
 * - Compare current nodes, append smaller one
 * - When one list exhausted, append remaining of other
 * - Maintain sorted order throughout merge
 */


/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

/**
 * SOLUTION: Iterative Merge with Dummy Node
 * Time: O(m + n) | Space: O(1) excluding output
 *
 * ALGORITHM:
 * 1. Create dummy node to handle head edge case
 * 2. Use pointer to build merged list
 * 3. Compare current nodes from both lists
 * 4. Append smaller node, advance that list's pointer
 * 5. When one list empty, append remaining of other
 * 6. Return dummy.next (skip dummy node)
 *
 * WHY DUMMY NODE:
 * - Avoids special case for first node
 * - Pointer starts at dummy, builds from dummy.next
 * - Clean, no null checks for head
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // DUMMY NODE: Simplifies head handling
        ListNode result = new ListNode(0);
        ListNode pointer = result;

        while (list1 != null && list2 != null) {

            if (list1.val <= list2.val) {
                //assing next of pointer, remember your are not moving pointer here 
                pointer.next = list1;
                list1 = list1.next;
            } else {
                //assing next of pointer, remember your are not moving pointer here 
                pointer.next = list2;
                list2 = list2.next;
            }
            // next of pointer becomes current for pointer
            pointer = pointer.next;
        }
        // APPEND REMAINING NODES: One list exhausted
        if (list2 != null) {
            pointer.next = list2;
            list2 = list2.next;
        }
        if (list1 != null) {
            pointer.next = list1;
            list1 = list1.next;
        }
        // RETURN MERGED LIST: Skip dummy node
        return result.next;
    }
}


/* ============================================================================
 * ALGORITHM WALKTHROUGH
 * ============================================================================
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 *
 * Dummy: result(0) → pointer
 *
 * Iteration 1: list1=1, list2=1
 *   1 <= 1: pointer.next = list1(1), list1 = 2
 *   pointer moves to 1
 *   List: 0 → 1
 *
 * Iteration 2: list1=2, list2=1
 *   2 > 1: pointer.next = list2(1), list2 = 3
 *   pointer moves to 1
 *   List: 0 → 1 → 1
 *
 * Iteration 3: list1=2, list2=3
 *   2 <= 3: pointer.next = list1(2), list1 = 4
 *   pointer moves to 2
 *   List: 0 → 1 → 1 → 2
 *
 * Iteration 4: list1=4, list2=3
 *   4 > 3: pointer.next = list2(3), list2 = 4
 *   pointer moves to 3
 *   List: 0 → 1 → 1 → 2 → 3
 *
 * Iteration 5: list1=4, list2=4
 *   4 <= 4: pointer.next = list1(4), list1 = null
 *   pointer moves to 4
 *   List: 0 → 1 → 1 → 2 → 3 → 4
 *
 * list1 exhausted: pointer.next = list2(4)
 * Final: 0 → 1 → 1 → 2 → 3 → 4 → 4
 *
 * Return result.next = [1,1,2,3,4,4] ✓
 * ============================================================================
 */

/* ============================================================================
 * COMPLEXITY ANALYSIS
 * ============================================================================
 * TIME COMPLEXITY: O(m + n)
 * - Traverse both lists exactly once
 * - Each node visited exactly once
 * - Linear in total number of nodes
 *
 * SPACE COMPLEXITY: O(1) excluding output
 * - Only dummy node + few pointers
 * - No extra data structures
 * - Output list reuses existing nodes
 *
 * WHY EFFICIENT:
 * - Optimal time: Must visit all nodes
 * - Minimal space: In-place merge
 * - No sorting needed (lists already sorted)
 * ============================================================================
 */

/* ============================================================================
 * KEY INSIGHTS & EDGE CASES
 * ============================================================================
 * 1. DUMMY NODE PATTERN:
 *    - Simplifies edge cases (empty lists, single nodes)
 *    - Pointer starts at dummy, builds from dummy.next
 *    - Return dummy.next skips the dummy
 *
 * 2. POINTER MANAGEMENT:
 *    - 'pointer' builds the result list
 *    - Always move pointer after appending: pointer = pointer.next
 *    - list1/list2 pointers advance only when their node is used
 *
 * 3. EQUALITY HANDLING:
 *    - list1.val <= list2.val ensures stable sort
 *    - When equal, list1 node comes first
 *    - Maintains relative order of equal elements
 *
 * 4. EDGE CASES:
 *    - Both lists empty: return null
 *    - One list empty: return other list
 *    - One list single node: merge correctly
 *    - All elements equal: stable merge
 *    - Different list lengths: handles automatically
 *
 * 5. ALTERNATIVE APPROACHES:
 *    - Recursive: O(m+n) time, O(m+n) space (stack)
 *    - Priority Queue: Overkill for 2 lists
 *    - Iterative with no dummy: More complex null checks
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - Chapter 10: Elementary Data Structures (Lists)
 *    - Merge algorithms for sorted sequences
 *
 * 2. Sedgewick & Wayne - "Algorithms (4th Edition)"
 *    - Merging sorted lists and linked list operations
 *    - Dummy node pattern for list manipulations
 *
 * 3. Skiena - "The Algorithm Design Manual"
 *    - List merging algorithms
 *    - Handling edge cases in linked list problems
 *
 * RELATED PROBLEMS:
 * - LeetCode 23 (Merge K Sorted Lists) - Extend to K lists
 * - LeetCode 88 (Merge Sorted Array) - Same idea, arrays
 * - LeetCode 21 (This problem) - Current implementation
 * - LeetCode 148 (Sort List) - Merge sort on single list
 * ============================================================================
 */