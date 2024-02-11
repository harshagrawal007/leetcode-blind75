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
// this one is slow on leetcode
class Solution {
    int pathLength = 0;
    public int longestZigZag(TreeNode root) {
        dfs(root, false, 0);
        dfs(root, true, 0);
        return pathLength;
    }

    public void dfs(TreeNode node, boolean goLeft, int steps){
        if(node ==null) return;
        pathLength = Math.max(pathLength, steps);
        if(goLeft){
            dfs(node.left, false, steps + 1);
             dfs(node.right, true, 1);
        }  else {
            dfs(node.left, false, 1);
            dfs(node.right, true, steps + 1);
        }
    }
}

// this one beats 99% submissions
class Solution {
    int pathLength = 0;

    private void dfs(TreeNode root, int left, int right) {
        if (root == null) {
            return;
        }

        pathLength = Math.max(pathLength, Math.max(left, right));

        // suppose you are coming from right and going left
        if (root.left != null) {
            dfs(root.left, right + 1, 0);
        } 
        // suppose you are coming from left and going right
        if (root.right != null) {
            dfs(root.right, 0, left + 1);
        }
    }

    public int longestZigZag(TreeNode root) {
        dfs(root, 0, 0);
        return pathLength;
    }
};