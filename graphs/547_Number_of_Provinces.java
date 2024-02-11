public class Solution {
  
    public int findCircleNum(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }

      public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }
}

// public class Solution {
//     public int findCircleNum(int[][] M) {
//         boolean[] visited = new boolean[M.length]; //visited[i] means if ith person is visited in this algorithm
//         int count = 0;
//         for(int i = 0; i < M.length; i++) {
//             if(!visited[i]) {
//                 dfs(M, visited, i);
//                 count++;
//             }
//         }
//         return count;
//     }
//     private void dfs(int[][] M, boolean[] visited, int person) {
//         for(int other = 0; other < M.length; other++) {
//             if(M[person][other] == 1 && !visited[other]) {
//                 //We found an unvisited person in the current friend cycle 
//                 visited[other] = true;
//                 dfs(M, visited, other); //Start DFS on this new found person
//             }
//         }
//     }
// }