/**
 * LeetCode 134: Gas Station
 * 
 * PROBLEM: Start at some gas station, complete circular route visiting all stations
 * At station i: gain gas[i], consume cost[i] to reach next station
 * Find starting station index, or -1 if impossible
 * 
 * EXAMPLE:
 * gas = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * Answer: 3 (start at station 3)
 * - Station 3: 4 gas, 1 cost, tank = 3
 * - Station 4: 5 gas, 2 cost, tank = 3+5-2 = 6
 * - Station 0: 1 gas, 3 cost, tank = 6+1-3 = 4
 * - Station 1: 2 gas, 4 cost, tank = 4+2-4 = 2
 * - Station 2: 3 gas, 5 cost, tank = 2+3-5 = 0 ✓
 * 
 * KEY INSIGHTS:
 * 1. If total_gas < total_cost: no solution exists
 * 2. If solution exists, it's UNIQUE (the algorithm finds it)
 * 3. Greedy approach: if curr_tank negative, start from next station
 * 
 * WHY GREEDY WORKS:
 * - If we can't reach from A to B, starting before A won't help
 * - So move start forward when we hit negative
 * - If total is non-negative, greedy start will complete circuit
 */

/**
 * SOLUTION: Greedy Two-Pass Approach
 * Time: O(n) | Space: O(1)
 * 
 * ALGORITHM:
 * 1. Track two things while iterating:
 *    - total_tank: cumulative gas - cost (tells if solution exists)
 *    - curr_tank: tank from current start (tells if start point works)
 * 
 * 2. If curr_tank < 0: can't reach from this start
 *    → Move start to next station, reset curr_tank
 * 
 * 3. At end: if total_tank >= 0, return start; else -1
 * 
 * WHY THIS WORKS:
 * - If we fail at position i, we can't start anywhere before i+1
 * - (Because starting before and failing at i means insufficient gas)
 * - If total is non-negative and we found a valid start, we can complete circuit
 */
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // TRACK TWO VALUES:
        // total_tank: is a solution even possible?
        // curr_tank: can we complete from current starting point?
        int total_tank = 0;
        int curr_tank = 0;
        int start = 0;
        
        // ITERATE through all stations
        for (int i = 0; i < gas.length; i++) {
            // At station i: gain gas[i], use cost[i] to reach next station
            int net = gas[i] - cost[i];
            
            total_tank += net;  // Update total: can solution exist?
            curr_tank += net;   // Update current: can we reach from start?
            
            // KEY GREEDY CHOICE:
            // If curr_tank becomes negative, current starting point won't work
            // Why? We need gas to continue, but ran out
            // So next valid start is station i+1
            if (curr_tank < 0) {
                start = i + 1;      // Move start forward
                curr_tank = 0;      // Reset tank for new start
            }
        }
        
        // FINAL CHECK:
        // If total_tank >= 0: we found a valid starting point (proven by greedy)
        // If total_tank < 0: no solution exists (more cost than gas overall)
        return total_tank >= 0 ? start : -1;
    }
}

/* ============================================================================
 * WHY GREEDY CHOICE IS CORRECT (Mathematical Proof)
 * ============================================================================
 * 
 * CLAIM: If solution exists (total_gas >= total_cost),
 *        the greedy starting point will complete the circuit
 * 
 * PROOF:
 * Suppose we try starting at A, fail at B (can't reach C)
 * Let's try starting at all positions between A and B...
 * 
 * At any position X between A and B:
 * - tank(X to B) = tank(A to B) - sum(A to X)
 * - Since tank(A to X) has been accumulated since A, it's ≥ 0
 * - So tank(X to B) ≤ tank(A to B) < 0
 * - Thus starting at X will also fail by B!
 * 
 * Therefore: If we fail at B starting from A,
 *           we should try B+1, not anything between A and B
 * 
 * This is why greedy works!
 * ============================================================================
 */

/* ============================================================================
 * SIMULATION EXAMPLE
 * ============================================================================
 * gas = [1, 2, 3, 4, 5]
 * cost = [3, 4, 5, 1, 2]
 * 
 * i=0: net=1-3=-2, total_tank=-2, curr_tank=-2<0 → start=1, curr_tank=0
 * i=1: net=2-4=-2, total_tank=-4, curr_tank=-2<0 → start=2, curr_tank=0
 * i=2: net=3-5=-2, total_tank=-6, curr_tank=-2<0 → start=3, curr_tank=0
 * i=3: net=4-1=3,  total_tank=-3, curr_tank=3
 * i=4: net=5-2=3,  total_tank=0,  curr_tank=6
 * 
 * total_tank >= 0? YES, return start=3 ✓
 * ============================================================================
 */

/* ============================================================================
 * COMPLEXITY ANALYSIS
 * ============================================================================
 * Time: O(n)
 *   - Single pass through n stations
 *   - Constant work per station (addition)
 *   - No inner loops
 * 
 * Space: O(1)
 *   - Only tracking three variables
 *   - No additional data structures
 * 
 * WHY OPTIMAL:
 * - Must examine every station at least once = O(n) lower bound
 * - This achieves it
 * - Can't do better than O(n)
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - Greedy algorithms and exchange arguments
 *    - Proving correctness of greedy choices
 * 
 * 2. Kleinberg & Tardos - "Algorithm Design"
 *    - Greedy interval/circular scheduling problems
 *    - When greedy works and why
 * 
 * 3. LeetCode Discussions
 *    - Community proofs of why greedy works
 *    - Visual explanations of the algorithm
 * 
 * RELATED PROBLEMS TO PRACTICE:
 * - LeetCode 55 (Jump Game) - Another greedy reachability
 * - LeetCode 45 (Jump Game II) - Min jumps variation
 * - LeetCode 406 (Queue Reconstruction) - Greedy ordering
 * - LeetCode 435 (Non-overlapping Intervals) - Greedy selection
 * ============================================================================
 */