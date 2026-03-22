/**
 * LeetCode 1797: Design Authentication Manager
 *
 * PROBLEM: Build a token manager supporting:
 *   - generate(tokenId, currentTime): issue token with TTL
 *   - renew(tokenId, currentTime): extend token expiration if unexpired
 *   - countUnexpiredTokens(currentTime): count tokens not expired
 *
 * KEY INSIGHT:
 * - Use a map to track tokenId → expiryTime
 * - Maintain tokens ordered by expected expiration so we can evict quickly
 * - currentTime is guaranteed to be non-decreasing across calls
 *
 * APPROACH: LinkedHashMap in access-order + explicit eviction scan
 * - Use access-order to move recently renewed tokens to tail
 * - Evict from head while the earliest expiry is <= currentTime
 * - This works because expiry times never decrease as time advances
 */

import java.util.LinkedHashMap;
import java.util.Map;

class AuthenticationManager {
    private final Map<String, Integer> expiry;
    private final int timeToLive;

    public AuthenticationManager(int timeToLive) {
        this.timeToLive = timeToLive;

        // LinkedHashMap<K, V>(initialCapacity, loadFactor, accessOrder)
        // Parameter explanation:
        //   - initialCapacity (16): Initial hash table size. Nodes array starts with 16 slots.
        //     Higher initial capacity reduces collisions/resizing, but uses more memory.
        //   - loadFactor (0.75f): Threshold ratio for resizing. When size > capacity * loadFactor,
        //     the hash table doubles. 0.75 is default—balances memory and performance.
        //   - accessOrder (true): If true, iteration order = access order (LRU style).
        //     If false, iteration order = insertion order.
        //
        // With accessOrder=true:
        //   - Most recently accessed entries move to the tail of the linked list
        //   - We can efficiently iterate from head (oldest tokens) to tail (newest)
        //   - When we renew a token (call put), it moves to the tail
        //   - Expired tokens cluster at the head for fast eviction
        expiry = new LinkedHashMap<>(16, 0.75F, true);
    }

    public void generate(String tokenId, int currentTime) {
        evictExpired(currentTime);
        expiry.put(tokenId, currentTime + timeToLive);
    }

    public void renew(String tokenId, int currentTime) {
        evictExpired(currentTime);
        // Only renew if token still exists (i.e., unexpired)
        if (expiry.containsKey(tokenId)) {
            expiry.put(tokenId, currentTime + timeToLive);
        }
    }

    public int countUnexpiredTokens(int currentTime) {
        evictExpired(currentTime);
        return expiry.size();
    }

    private void evictExpired(int currentTime) {
        // Eviction is done by scanning from the oldest entry (head).
        // Because currentTime is non-decreasing and expiry times increase with
        // time, expired tokens will always appear at the front.
        var iter = expiry.entrySet().iterator();
        while (iter.hasNext()) {
            var entry = iter.next();
            if (entry.getValue() <= currentTime) {
                iter.remove();
            } else {
                break; // remaining entries are newer and not expired
            }
        }
    }
}

/* ============================================================================
 * COMPLEXITY ANALYSIS
 * ============================================================================
 * Time: Amortized O(1) per operation
 *   - generate/renew: O(1) map update + eviction scan (amortized)
 *   - countUnexpiredTokens: O(1) amortized due to eviction scan shared
 * Space: O(n) where n = number of active tokens
 *
 * Note: Eviction scan is amortized O(1) because each token is removed once.
 *
 * Assumption: currentTime is non-decreasing, which ensures eviction order matches time.
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. LeetCode 1797 discussion: Using LinkedHashMap for LRU-style eviction
 * 2. JavaDocs: LinkedHashMap(accessOrder=true) for access-order iteration
 * 3. Common design pattern: "Time-based key-value cache".
 */