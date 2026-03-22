/**
 * LeetCode 146: LRU Cache
 *
 * REQUIREMENTS:
 * - get(key): return value if key exists, else -1
 * - put(key, value): insert or update value
 * - If capacity exceeded, evict least recently used (LRU) item
 * - All operations must be O(1) time
 *
 * APPROACH: Java LinkedHashMap with access-order iteration
 * PATTERN: Use built-in ordered map to track recency
 *
 * KEY INSIGHT:
 * - LinkedHashMap can operate in access-order mode by passing `true`
 *   to the constructor.
 * - Override removeEldestEntry to automatically evict once capacity is exceeded.
 * - get() and put() both update access order in O(1).
 */


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

import java.util.LinkedHashMap;
import java.util.Map;
class LRUCache {
    private final int capacity;
    private final LinkedHashMap<Integer, Integer> cache;

    public LRUCache(int capacity) {
        this.capacity = capacity;

        // LinkedHashMap with accessOrder=true makes it LRU-aware.
        // When a key is accessed (get/put), it moves to the end of the linked list.
        // removeEldestEntry is called after each put operation.
        this.cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                // When true, the eldest entry (least recently used) is removed.
                return size() > LRUCache.this.capacity;
            }
            //Returns true if this map should remove its eldest entry. 
            //This method is invoked by put and putAll after inserting a 
            //new entry into the map.        
            };
    }

    public int get(int key) {
        // get() updates access order automatically in LinkedHashMap
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value); // updates access order and may evict eldest
    }
}

/* ============================================================================
 * COMPLEXITY ANALYSIS
 * ============================================================================
 * Time: O(1) average for get() and put()
 * Space: O(capacity)
 *
 * - LinkedHashMap provides constant-time access and update for both operations.
 * - Eviction happens automatically when size exceeds capacity.
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. JavaDoc for LinkedHashMap (accessOrder mode)
 * 2. LeetCode discuss: "LRU Cache Java solution using LinkedHashMap" 
 * 3. Classic cache design patterns: LRU cache using doubly-linked list + hash map
 */

