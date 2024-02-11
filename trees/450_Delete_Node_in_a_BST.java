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
    public TreeNode deleteNode(TreeNode root, int key) {
    if(root ==null ) return null;


        if(root.val < key ){
           root.right = deleteNode(root.right,key);
            return root;
        } else if(root.val > key){
            root.left = deleteNode(root.left,key);
            return root;
        } else {
            if(root.left==null){
                return root.right;
            }
            else if(root.right==null){
                return root.left;
            }
            else{
                TreeNode min = root.right;
                while(min.left!=null){
                    min = min.left;
                }
                
                root.val = min.val;
                root.right = deleteNode(root.right,min.val);
                return root;
            }

        }
    }
}



/*

question:

Given a root node reference of a BST and a key, delete the node with the given key in the BST. 
Return the root node reference (possibly updated) of the BST.
Basically, the deletion can be divided into two stages:

1.Search for a node to remove.
2.If the node is found, delete the node.

answer/algo:

Intution: Use the BST property to search the node and then delete if found the required node.

So if the target node has value less than root then we will surely get it in the left subtree 
-> just call ur recursive function for the left subtree.
If the traget node has value greater than root then we will surely get it in the right subtreee
-> just call ur recursive function for the right subtree.

case, root itself is the required node to be deleted.
three cases:
If left of root is null and root node is to be deleted
->then just simply return the right subtree.

If right of root is null and root node is to be deleted
-> then just simply return the left subtree.

Both are not null then you have to not just delete the node but also maintain the BST structure.

if you delete the root node then which node can optimally replace it 
so that all the nodes on left are still small and on right are larger.
->that node will be the node just greater than the largest node in the left subtree 
->which is the smallest node in the right subtree
point your pointer on the right subtree and then move it to the left most node of its subtree 
->that will be your required node and so now replace the value of your root with this node value 
->which will ensure that the key which u wanted to delete is deleted and the value there is the right value.
Now you have to delete that node whose value is already present in the root...
so now that work will be done by the recursion so now just pass that right subtree in which the value is present
with that nodes value which will be now the target

*/