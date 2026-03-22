/**
 * LeetCode 36: Valid Sudoku
 * 
 * PROBLEM: Validate a partial 9x9 Sudoku board (not complete)
 * Return true if current state is valid, false if any rule is violated
 * 
 * SUDOKU RULES:
 * - Each row must have unique digits 1-9 (empty cells = '.')
 * - Each column must have unique digits 1-9
 * - Each 3x3 box must have unique digits 1-9
 * 
 * APPROACH 1: Three Separate HashSet Arrays (Cleaner)
 * APPROACH 2: Single HashSet with String Keys (More Compact)
 * 
 * KEY INSIGHT:
 * - Don't need to validate full 81 cells, just filled ones
 * - For each cell, check if number already exists in:
 *   1. Its row
 *   2. Its column
 *   3. Its 3x3 box
 * - Box index formula: (r / 3) * 3 + c / 3 gives box 0-8
 * 
 * WHY BOX INDEXING MAT TERS:
 * - (r/3, c/3) gives which 3x3 section
 * - Converting to linear index (0-8): (r/3)*3 + c/3
 * - Example: (6,6) → (2,2) box position → index 8 (bottom-right box)
 */

/**
 * SOLUTION 1: Three Separate HashSet Arrays (RECOMMENDED)
 * 
 * Complexity: Time O(81) = O(1) | Space O(3*9) = O(1)
 * 
 * TIME ANALYSIS:
 * - Outer loop: 9 rows
 * - Inner loop: 9 columns
 * - For each cell: HashSet add() operation = O(1) average
 * - Total iterations: 9*9 = 81 (constant for Sudoku)
 * - Time: O(81) = O(1)
 * 
 * SPACE ANALYSIS:
 * - rows array: 9 HashSets
 * - cols array: 9 HashSets
 * - boxes array: 9 HashSets
 * - Each HashSet stores at most 9 elements (digits 1-9)
 * - Total: O(27 * 9) = O(1) constant
 * 
 * ADVANTAGES:
 * - Clear semantics: easy to understand what each set tracks
 * - Direct access: rows[r], cols[c], boxes[idx]
 * - Best for interviews: shows clear thinking
 */
class Solution {
    public boolean isValidSudoku(char[][] board) {
        // STEP 0: Define dimensions
        int N = 9;  // Sudoku is always 9x9
        
        // STEP 1: Create HashSet arrays to track numbers in rows, columns, and boxes
        // rows[i] = set of numbers already in row i
        // cols[j] = set of numbers already in column j
        // boxes[k] = set of numbers already in box k (k = 0-8)
        HashSet<Character>[] rows = new HashSet[N];
        HashSet<Character>[] cols = new HashSet[N];
        HashSet<Character>[] boxes = new HashSet[N];
        
        // Initialize all HashSets
        for (int r = 0; r < N; r++) {
            rows[r] = new HashSet<Character>();
            cols[r] = new HashSet<Character>();
            boxes[r] = new HashSet<Character>();
        }

        // STEP 2: Validate each cell
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                char val = board[r][c];
                
                // Skip empty cells
                // Empty cells are marked with '.' and don't violate rules
                if (val == '.') {
                    continue;
                }
                
                // RULE 1: Check if digit already exists in this row
                // HashSet.add() returns false if element already exists
                if (!rows[r].add(val)) {
                    return false;  // Duplicate found in row
                }
                
                // RULE 2: Check if digit already exists in this column
                if (!cols[c].add(val)) {
                    return false;  // Duplicate found in column
                }
                
                // RULE 3: Check if digit already exists in this 3x3 box
                // Box index formula: (row/3)*3 + col/3
                // Floor division groups cells into 3x3 sections:
                // 
                // Example grid positions to box indices:
                // (0,0) → box 0 (top-left)
                // (1,4) → (0,1)*3 + 1 = 1 (top-middle)
                // (8,8) → (2,2)*3 + 2 = 8 (bottom-right)
                //
                // Visual 3x3 box layout:
                // [0][1][2]
                // [3][4][5]
                // [6][7][8]
                int boxIndex = (r / 3) * 3 + c / 3;
                if (!boxes[boxIndex].add(val)) {
                    return false;  // Duplicate found in box
                }
            }
        }
        
        // All validations passed
        return true;
    }
}

/**
 * SOLUTION 2: Single HashSet with String Keys (More Compact)
 * 
 * Complexity: Time O(81) = O(1) | Space O(1)
 * 
 * KEY IDEA: Instead of three separate sets, use one set
 * Store encodings: "digit-in-row-r", "digit-in-col-c", "digit-in-box-b"
 * Example: '5' in row 2 → "5 in row2"
 * 
 * PROS: Less boilerplate, single HashSet
 * CONS: String creation overhead, less readable
 */
class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet<String> seen = new HashSet();
        
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                char val = board[r][c];
                
                // Skip empty cells
                if (val == '.') {
                    continue;
                }
                
                // Create string encodings for row, column, and box constraints
                // If any encoding is already in set, duplicate exists
                if (!seen.add(val + " in row " + r) ||         // Check row
                    !seen.add(val + " in col " + c) ||         // Check column
                    !seen.add(val + " in box " + r/3 + "-" + c/3)) {  // Check 3x3 box
                    return false;
                }
            }
        }
        
        return true;
    }
}

/* ============================================================================
 * SPACE OPTIMIZATION ANALYSIS
 * ============================================================================
 * Solution 1 (Array of Sets): O(27*9) = O(243) constants
 * Solution 2 (Single Set): O(81*3) = O(243) constants (string keys)
 * 
 * Both use O(1) constant space relative to problem size
 * The exact constant factors differ slightly
 * 
 * For Sudoku specifically:
 * - Always 9x9 grid
 * - Always at most 81 cells
 * - At most 27 constraints (9 rows + 9 cols + 9 boxes)
 * - Space complexity doesn't scale with input
 * ============================================================================
 */

/* ============================================================================
 * KEY CITATIONS & REFERENCES
 * ============================================================================
 * 1. Peter Norvig - "Solving Every Sudoku Puzzle"
 *    - Algorithmic approach to Sudoku solving
 *    - Constraint satisfaction problem formulation
 * 
 * 2. Thomas Cormen et al. - "Introduction to Algorithms (CLRS)"
 *    - Hash table applications
 *    - Constraint validation patterns
 * 
 * 3. Donald Knuth - "Dancing Links and Sudoku"
 *    - Advanced Sudoku solving algorithms
 *    - Efficient constraint management
 * 
 * RELATED PROBLEMS TO PRACTICE (Similar Patterns):
 * - LeetCode 37 (Sudoku Solver) - Actually solve the puzzle
 * - LeetCode 1275 (Find Winner in Tic Tac Toe) - Game state validation
 * - LeetCode 1905 (Count Sub-Islands) - Multi-constraint checking
 * - LeetCode 2127 (Maximum Employees to Invite) - Constraint propagation
 * ============================================================================
 */
        return true;
    }
}
