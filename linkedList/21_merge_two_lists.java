/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode result = new ListNode(0);
        ListNode pointer = result;
    
        while (list1!=null && list2!=null ){

            if(list1.val<=list2.val){ 
                //assing next of pointer, remember your are not moving pointer here 
                pointer.next= list1;
                list1=list1.next;
            } 
            else {
                //assing next of pointer, remember your are not moving pointer here 
                pointer.next= list2;
                list2=list2.next;
            }
            // next of pointer becomes current for pointer
            pointer= pointer.next;
        }
        if(list2!=null){
            pointer.next= list2;
            list2=list2.next;
        }
        if(list1!=null){
                  pointer.next= list1;
            list1=list1.next;
        }
        return result.next;
    }
}