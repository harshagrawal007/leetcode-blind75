// faster on leetcode
// we are navigating only once through the list of interval 
//time complexity = O(n)
// space complexity = O(n) we created extra space arraylist which stores the result
class Solution {

  public int[][] insert(int[][] intervals, int[] newInterval) {
      List<int[]> res = new ArrayList<>();
      int newStart = newInterval[0], newEnd = newInterval[1];
      for (int[] interval : intervals) {
          // current end is less than new Start
          // or the new interval is null since its been already added
          if (newInterval == null || interval[1] < newStart) {
              res.add(interval); 

          } else if (interval[0] > newEnd) {
              // current start is greater than new end
              res.add(newInterval);
              res.add(interval);
              // assign new interval as null, 
              //to mark that new interval is added to the result
              newInterval = null;
          } else {
              newInterval[0] = Math.min(interval[0], newInterval[0]);
              newInterval[1] = Math.max(interval[1], newInterval[1]);
          }
      }
      // if new interval is not null that means its not added to result 
      if (newInterval != null) res.add(newInterval);
      return res.toArray(new int[res.size()][]);
  }
}


// below solution is slower on leetcode
// complexity time: 0(n) since we are only visiting each interval once
// space complexity 0(n) we created extra space arraylist
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
