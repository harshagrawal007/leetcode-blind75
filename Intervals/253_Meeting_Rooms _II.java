class Solution {
    public int minMeetingRooms(int[][] intervals) {
         Arrays.sort(intervals,(a,b)->a[0]-b[0]); //Sort the given meetings by their start time.
          PriorityQueue<Integer> pq=new PriorityQueue<>();//Initialize a new min-heap 
          int rooms=0;//total rooms
          for(int[] in:intervals)//For every meeting room check if the minimum element of the heap i.e. the room at the top of the heap is free or not.
          {
              if(pq.isEmpty() || pq.peek()<=in[0])
              {
			  //If the room is free, then we extract the topmost element and add it back with the ending time of the current meeting we are processing.
			  //we are here using a already available room again when a room get free we can use it again
                  pq.poll();
                  pq.add(in[1]);
              }
              else
              {//If not, then we allocate a new room and add it to the heap.
                  pq.add(in[1]);
              }
             // System.out.println(pq);
			 //the size of the heap will tell us the number of rooms allocated. 
              rooms=Math.max(pq.size(),rooms);
          }
        return rooms;
        
    }
}
