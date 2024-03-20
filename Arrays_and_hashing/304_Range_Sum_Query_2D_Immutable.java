class NumMatrix {
    private int[][] sum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        sum = new int[m + 1][n + 1]; // sum[i][j] is sum of all elements inside the rectangle [0,0,i,j]
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i+1][j+1] = sum[i+1][j] + sum[i][j+1] - sum[i][j] + matrix[i][j];
            }
        }
    }
    public int sumRegion(int r1, int c1, int r2, int c2) {
        return sum[r1][c1] + sum[r2+1][c2+1] - sum[r2+1][c1] - sum[r1][c2+1] ;
       
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */


/*
Given a 2D matrix matrix, handle multiple queries of the following type:
Calculate the sum of the elements of matrix inside the rectangle defined by 
its upper left corner (row1, col1) and lower right corner (row2, col2).
Implement the NumMatrix class:
NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the 
elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
You must design an algorithm where sumRegion works on O(1) time complexity.
*/