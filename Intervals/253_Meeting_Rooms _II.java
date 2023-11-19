class Solution {
    public int minMeetingRooms(int[][] intervals) {
        //Sort the given meetings by their start time.
        Arrays.sort(intervals,(a,b)->a[0]-b[0]); 
        //Initialize a new min-heap 
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        // max rooms used
        int rooms=0;
        //For every meeting room check if the minimum element of the heap 
        //i.e. the room at the top of the heap is free or not.
        for(int[] in:intervals) {
            int startTime = in[0];
            int endTime = in[1];
            // keep removing till endtimes are lower the start time current interval
            while(pq.size() >0 && pq.peek()<=startTime) {
                pq.poll();
            }
            pq.add(endTime);
            // the size of the heap will tell us the number of rooms allocated at the moment
            rooms=Math.max(pq.size(),rooms);
        }
        return rooms;
    }
}
