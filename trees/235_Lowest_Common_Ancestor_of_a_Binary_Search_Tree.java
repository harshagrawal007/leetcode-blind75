/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

 class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
     
        if(p.val > root.val && q.val >root.val){
            return lowestCommonAncestor(root.right, p, q );
        }
        if (p.val < root.val && q.val <root.val){
            return lowestCommonAncestor(root.left, p, q );
        }
        
        return root;
    }
}

// solution always exist 
// if its not less than and not greater than then its a match
// a node can be a descendant of itself according to the LCA definition.
// The lowest common ancestor is defined between two nodes p and q as the lowest node
// in T that has both p and q as descendants (where we allow a node to be a descendant of itself)
