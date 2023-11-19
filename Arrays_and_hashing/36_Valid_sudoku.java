class Solution {
    public boolean isValidSudoku(char[][] board) {
        //9 row , 9 column, 9 boxes
        int N = 9;
        // Use hash set to record the status
        // hashset array of size 9 for 9 row 
        HashSet<Character>[] rows = new HashSet[N];
        //hashset array of size 9 for 9 columns 
        HashSet<Character>[] cols = new HashSet[N];
        //hashset array of size 9 for 9 boxes 
        HashSet<Character>[] boxes = new HashSet[N];
        
        for (int r = 0; r < N; r++) {
            //intialize hashset for each row , column and box
            rows[r] = new HashSet<Character>();
            cols[r] = new HashSet<Character>();
            boxes[r] = new HashSet<Character>();
        }

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                char val = board[r][c];
                // Check if the position is filled with number
                //not number continue dont add to any set
                //board[i][j] is a digit 1-9 or '.'.
                if (val == '.') {
                    continue;
                }
                // Check the row, add returns true if not present,
                // not present in column add in column or return false
                if (!rows[r].add(val)) {
                    return false;
                }
                // Check the column, not present in column add in column or return false
                if (!cols[c].add(val)) {
                    return false;
                }
                // Check the box
                //box 1 - row 1-column 1, row2-col2, row3-col3
                // 0/3 *3 + 0/3 =0
                // 1/3 * 3+ 1/3 =0
                //2 /3 * 3 + 2/3 = 0
                //box 9 - row 6-column 6, row7-col7, row8-col8
                //(6/3) *3 + 6/3 = 8
                //(7/3) *3 + 7/3= 8
                //(8/3 )*3 + 8/3 = 8
                
                int idx = (r / 3) * 3 + c / 3;
                //width of box = 3
                //height of subbox = 3
                if (!boxes[idx].add(val)) {
                    return false;
                }
            }
        }
        return true;
    }
}


// there is another solution to reduce the number of hashsets, and using string 
// not neccessarily faster, but code looks simple enough

class Solution {
    public boolean isValidSudoku(char[][] board) {
              
        HashSet<String> seen = new HashSet();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char val = board[r][c];
                if (val == '.') {
                    continue;
                }
                if (!seen.add(val + "in row" + r) || !seen.add(val + "in column" + c) || 
                                !seen.add(val + "in box" + r/3 + "-" + c/3) ) {
                    return false;
                }
               
            }
        }
        return true;
    }
}
