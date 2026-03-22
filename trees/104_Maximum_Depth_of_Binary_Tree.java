/**
 * LeetCode 104: Maximum Depth of Binary Tree
 *
 * PROBLEM: Find the maximum depth (height) of a binary tree.
 * Depth = number of nodes along longest path from root to leaf.
 *
 * Example: Tree [3,9,20,null,null,15,7] → depth 3
 *         3
 *        / \
 *       9  20
 *          / \
 *         15  7
 *
 * APPROACH: Multiple traversal methods (BFS/DFS)
 * PATTERN: Tree height calculation using level-order or recursion
 *
 * KEY INSIGHT:
 * - Depth = max path length from root to any leaf
 * - Can use BFS (level order) or DFS (recursion/iteration)
 * - Base case: null node has depth 0
 * - Recursive: 1 + max(left_depth, right_depth)
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
/**
 * SOLUTION 1: BFS (Level Order Traversal)
 * Time: O(n) | Space: O(w) where w = max width
 *
 * ALGORITHM:
 * - Use queue for level-order traversal
 * - Add null markers between levels to count depth
 * - Each null marker = one level completed
 *
 * WHY BFS WORKS:
 * - Visits nodes level by level
 * - Null markers count completed levels
 * - Space efficient for wide trees (vs recursion)
 */
// using BFS no recursion
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        
        int depth = 0;
        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        q.add(null);

        while( !q.isEmpty() ) {
            TreeNode node = q.remove();

            if( node == null ) {
                depth += 1;
                if( !q.isEmpty() ) {
                    q.add(null);
                }
            }
            else {
                if(node.left != null) {
                    q.add(node.left);
                }
                if(node.right != null) {
                    q.add(node.right);
                }
            }
        }

        return depth;
    }
}

//using dfs with recursion
class Solution {

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}


// using iterative dfs, 


public int maxDepth(TreeNode root) {
    if(root == null) {
        return 0;
    }
    
    Stack<TreeNode> stack = new Stack<>();
    Stack<Integer> value = new Stack<>();
    stack.push(root);
    value.push(1);
    int max = 0;
    while(!stack.isEmpty()) {
        TreeNode node = stack.pop();
        int temp = value.pop();
        max = Math.max(temp, max);
        if(node.left != null) {
            stack.push(node.left);
            value.push(temp+1);
        }
        if(node.right != null) {
            stack.push(node.right);
            value.push(temp+1);
        }
    }
    return max;
}