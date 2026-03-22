/**
 * LeetCode 1094: Car Pooling
 *
 * PROBLEM: Given trips with (passengers, start, end) and vehicle capacity,
 * determine if it's possible to pick up and drop off all passengers without
 * exceeding capacity at any point.
 *
 * KEY INSIGHT:
 * - The car only moves east, so locations are strictly increasing.
 * - We can treat passenger changes as + passengers at pickup location and
 *   - passengers at drop-off location.
 * - Use a "difference array" (prefix sum technique) over the location range.
 *
 * APPROACH:
 * 1) Use an array `locations` indexed by kilometer (0..1000).
 * 2) For each trip: locations[start] += passengers; locations[end] -= passengers;
 * 3) Scan from left to right, maintaining current passenger count.
 *    If it ever exceeds capacity, return false.
 */

class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        // Constraint: 0 <= location <= 1000 (per problem statement)
        int[] locations = new int[1001];

        // Build the difference array: add when boarding, subtract when leaving
        for (int[] trip : trips) {
            int passengers = trip[0];
            int start = trip[1];
            int end = trip[2];

            locations[start] += passengers;
            locations[end] -= passengers;
        }

        // Sweep from left to right to compute current passenger count
        int currentPassengers = 0;
        for (int loc = 0; loc <= 1000; loc++) {
            currentPassengers += locations[loc];
            if (currentPassengers > capacity) {
                return false;
            }
        }

        return true;
    }
}

/* ============================================================================
 * COMPLEXITY ANALYSIS
 * ============================================================================
 * Time: O(n + m) where n = trips.length, m = max location range (1000)
 * Space: O(m) = O(1000) = O(1) constant
 *
 * Note: The problem guarantees locations are <= 1000, allowing fixed-size array.
 * If the range were larger, we would use a TreeMap or coordinate compression.
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Difference Array / Prefix Sum technique (common in range update problems)
 * 2. LeetCode discussion: "Car Pooling solution using prefix sum"
 * 3. Competitive programming: "scanline / sweep line" for interval events
 */
