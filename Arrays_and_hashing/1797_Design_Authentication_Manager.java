class AuthenticationManager{
    private Map<String, Integer> expiry = new LinkedHashMap<>(16, 0.75F, true); 
    // here true indicates the map entries are in access order.
    // false means that insert order 
    //0.75F means if the elements are 12/16 it will make the size double 
    private int life;
    
    public AuthenticationManager(int timeToLive) {
        life = timeToLive;
    }
    public void generate(String tokenId, int currentTime) {
        evictExpired(currentTime);
        expiry.put(tokenId, life + currentTime);
    }
    public void renew(String tokenId, int currentTime) {
        evictExpired(currentTime);
        if (expiry.containsKey(tokenId)) {
            expiry.put(tokenId, life + currentTime);
        }
    }
    
    public int countUnexpiredTokens(int currentTime) {
        evictExpired(currentTime);
        return expiry.size();
    }
    
    private void evictExpired(int currentTime) {
        var iter = expiry.entrySet().iterator();
        while (iter.hasNext() && iter.next().getValue() <= currentTime) {
            iter.remove();
        }
    }
   }
/**
 * Your AuthenticationManager object will be instantiated and called as such:
 * AuthenticationManager obj = new AuthenticationManager(timeToLive);
 * obj.generate(tokenId,currentTime);
 * obj.renew(tokenId,currentTime);
 * int param_3 = obj.countUnexpiredTokens(currentTime);
 */