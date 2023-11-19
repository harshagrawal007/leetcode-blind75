class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
   
        //optimal solution for its the m and n are small
        // O(m+n)
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;

        int i = 0, j = matrix[0].length - 1;
        while(i < matrix.length && j >= 0) {
            //matrix [0][3]
            if(matrix[i][j] == target) {
                return true;
            } else if(matrix[i][j] > target){
                j --;
            } else {
                i ++;
            }
        
        }
        return false;
    }

    // this reduced the time exponentially so works great when rows and columns are huge or matrix is huge
     public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix.length == 0) return false;

        int rows = matrix.length;
        int columns = matrix[0].length;

        int low = 0;
        int high = rows * columns;

        while(low < high) {
            int mid = (low+high)/2;

            if(matrix[mid/columns][mid%columns] == target) {
                return true;
            } else if (matrix[mid/columns][mid%columns] < target) {
                low = mid+1;
            } else {
                high = mid;
            }
        }
        return false;
    }
}