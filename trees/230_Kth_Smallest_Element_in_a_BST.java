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
class Solution {
    int count=0;
    int ans=0;
    public int kthSmallest(TreeNode root, int k) {
        //List<Integer> list = new ArrayList<>();
        // inorder(root, list);
        inorder(root, k);
        //return list.get(k - 1);
        return ans;
        }
    
    //private void inorder(TreeNode root, List<Integer> list) {
    public void inorder(TreeNode root, int k ) {
        if (root == null) return;

        inorder(root.left, k);
        count++;
        if(k==count){
           ans=root.val;
           return;
        }
        //list.add(root.val);
        inorder(root.right, k);
    }
}
