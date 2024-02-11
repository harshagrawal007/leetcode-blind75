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
    public int maxLevelSum(TreeNode root) {
        
        if(root == null){
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList();
        queue.add(root);
        int level=0;
        int result =0;
        int sum = Integer.MIN_VALUE;
        while( !queue.isEmpty() ) {
            int len = queue.size();
            int currentSum =0;
            level++;
            for (int i =0; i<len ; i++){
                TreeNode curr = queue.remove();
                currentSum += curr.val;
                
                if (curr.left != null) {
                    queue.add(curr.left);
                }
                if (curr.right != null) {
                    queue.add(curr.right);
                }
            }
            if(currentSum > sum){
                sum=currentSum;
                result = level;
            }
        }

        return result;
    }
}