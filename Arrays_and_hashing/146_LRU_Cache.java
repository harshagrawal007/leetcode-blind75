class LRUCache {
    public int capacity;
    private LinkedHashMap<Integer, Integer> cache;

    public LRUCache(int capacity) {
        cache = new LinkedHashMap<>();
        this.capacity = capacity;
    }

    private boolean isFull() {
        return capacity == cache.size();
    }

    public int get(int key) {
        //remove from the cache first
        if (cache.containsKey(key)) {
            int value = cache.get(key);
            cache.remove(key);
            cache.put(key,value);
            return value;
        }
        return -1;
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)) {
            //remove, then put back (this will affect order)
            cache.remove(key);
        } else if(isFull()) {
                //remove first item
                int firstItemKey = cache.entrySet().iterator().next().getKey();
                cache.remove(firstItemKey);
            }
        
        cache.put(key, value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

 // another way 
 class LRUCache {
    int capacity;
    LinkedHashMap<Integer, Integer> dic;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        // here true indicates the map entries are in access order.
        // false means that insert order 
        //0.75F means if the elements are 12/16 it will make the size double 
        dic = new LinkedHashMap<>(5, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
            //Returns true if this map should remove its eldest entry. 
            //This method is invoked by put and putAll after inserting a 
            //new entry into the map.
        };
    }
    
    public int get(int key) {
        return dic.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        dic.put(key, value);
    }

}

