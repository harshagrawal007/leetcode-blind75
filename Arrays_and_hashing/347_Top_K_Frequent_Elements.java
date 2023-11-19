class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if(nums.length ==0 || nums.length==1 || nums.length ==k){
            return nums;
        }
        
        HashMap<Integer,Integer> mymap = new HashMap<>();
        for (int num : nums){
            mymap.put(num, mymap.getOrDefault(num,0)+1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((e1, e2) -> e1.getValue() -
                e2.getValue());

        // go through all numbers of map & push them in a minHeap 
        // keep the heap size at max K
        // If at any time it has > K elements, then remove the smallest element.
        for(Map.Entry<Integer, Integer> entry : mymap.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Let's create a list of top k elements
        int[] topNumbers = new int[k];
        int i =0;
        while (!minHeap.isEmpty()) {
            topNumbers[i] = minHeap.poll().getKey();
            i++;
        }
        return topNumbers;
    }
}
