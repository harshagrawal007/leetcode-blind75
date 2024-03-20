class Solution {

    final PriorityQueue<Integer> heap = new PriorityQueue<>();
 
    public int findKthLargest(int[] nums, int k) {
      
    for (int n : nums) {
        heap.add(n); //for adding the values of the array
        if ( heap.size() > k) {
            heap.remove();
        }
    }
    return heap.peek();
}
}