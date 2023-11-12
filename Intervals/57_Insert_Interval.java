class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        
    // init data
    int newStart = newInterval[0], newEnd = newInterval[1];
    int idx = 0, n = intervals.length;
    ArrayList<int[]> output = new ArrayList<int[]>();

    // add all intervals before newInterval
    while (idx < n && intervals[idx][1] < newStart)
      output.add(intervals[idx++]);

    // merge newInterval
    int[] interval = new int[2];
    while(idx < n && intervals[idx][0] <= newEnd)
    {
        newStart = Math.min(newStart, intervals[idx][0]);
        newEnd = Math.max(newEnd, intervals[idx][1]);
        ++idx;
    }   
    output.add(new int[]{newStart, newEnd});
      
    // add all intervals after newInterval  
    while (idx < n)
      output.add(intervals[idx++]);
      
    return output.toArray(new int[0][0]);
  }
}
