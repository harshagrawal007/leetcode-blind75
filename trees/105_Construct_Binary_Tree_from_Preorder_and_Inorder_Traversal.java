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
// class Solution {

//     public TreeNode buildTree(int[] preorder, int[] inorder) {
//         if (preorder.length == 0 || inorder.length == 0) return null;

//         TreeNode root = new TreeNode(preorder[0]);
//         int mid = 0;
//         for (int i = 0; i < inorder.length; i++) {
//             if (preorder[0] == inorder[i]) mid = i;
//         }

//         root.left =
//             buildTree(
//                 Arrays.copyOfRange(preorder, 1, mid + 1),
//                 Arrays.copyOfRange(inorder, 0, mid)
//             );
//         root.right =
//             buildTree(
//                 Arrays.copyOfRange(preorder, mid + 1, preorder.length),
//                 Arrays.copyOfRange(inorder, mid + 1, inorder.length)
//             );

//         return root;
//     }
// }


// Solution without using Array copies
// class Solution {

//     Map<Integer, Integer> inorderPositions = new HashMap<>();

//     public TreeNode buildTree(int[] preorder, int[] inorder) {
//         if (preorder.length < 1 || inorder.length < 1) return null;

//         for (int i = 0; i < inorder.length; i++) {
//             inorderPositions.put(inorder[i], i);
//         }

//         return builder(preorder, 0, 0, inorder.length - 1);
//     }

//     public TreeNode builder(int[] preorder, int preorderIndex, int inorderLow,  int  inorderHigh) {
//         if (preorderIndex > preorder.length - 1 || inorderLow > inorderHigh) 
//         return null;

//         int currentVal = preorder[preorderIndex];
//         TreeNode n = new TreeNode(currentVal);
//         int mid = inorderPositions.get(currentVal);

//         n.left = builder(preorder, preorderIndex + 1, inorderLow, mid - 1);
//         n.right = builder(preorder, preorderIndex + (mid - inorderLow) + 1,
//             mid + 1, inorderHigh);

//         return n;
//     }
// }

class Solution {

    public TreeNode buildTree(int[] preorder, int[] inorder) { 
        return helper(0, 0, inorder.length-1, preorder, inorder);
    }

    public TreeNode helper (int preStart, int inStart, int inEnd, int[] preorder, int []inorder){
        if(preStart > preorder.length-1 || inStart > inEnd){
            return null;
        }

        TreeNode root = new TreeNode(preorder[preStart]);

        int inIndex =0;
        for (int i =inStart; i<= inEnd ; i++){
            if(root.val == inorder[i]){
                inIndex = i;
            }
        }

        root.left = helper (preStart+1, inStart, inIndex-1, preorder, inorder);

        //it's helpful to understand that pix represents the nodes created so far,
        // while inIndex-inStart represents the nodes that will belong to the left subtree 
        //but haven't been created yet(Actually, when this line execute the left nodes are already made).
        //Adding 1 to this sum gives the index of the next node to create. 
        //Therefore, (preStart + 1 + (inIndex - inStart) represents the index of the first node in the right subtree 
        //that will be created after the left subtree.

        root.right = helper (preStart + (inIndex - inStart) +1, inIndex+1, inEnd, preorder, inorder);

        return root;

    }

}