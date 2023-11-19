class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,(a,b)->a[0]-b[0]);
         ArrayList<int[]> list=new ArrayList<>();
      list.add(intervals[0]);
        
        for(int i=1;i<intervals.length;i++){
            int[] top=list.get(list.size()-1);
            if(top[1]>=intervals[i][0]){
                top[1]=Math.max(top[1],intervals[i][1]);
            }else{
                list.add(intervals[i]);
            }
        }
        return list.toArray(new int[list.size()][2]);
    
}
}


class Solution {

    public int[][] merge(int[][] intervals) {
        ArrayList<int[]> ans = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        ans.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            //comparing the values of prevEnd and curStart
            int curStart = intervals[i][0];
            if (curStart <= ans.get(ans.size() - 1)[1]) {
                //do the merging
                ans.get(ans.size() - 1)[1] =
                    Math.max(ans.get(ans.size() - 1)[1], intervals[i][1]);
            } else {
                ans.add(intervals[i]);
            }
        }
        int[][] res = new int[ans.size()][2];
        ans.toArray(res);
        return res;
    }
}

