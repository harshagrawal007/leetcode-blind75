/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(null ==head){
            return false;
        }
        ListNode slowPointer = head;
        ListNode fastPointer = head.next;
      
        while (slowPointer !=fastPointer){
        
            if(fastPointer ==null || fastPointer.next ==null){
                return false;
            }
            slowPointer = slowPointer.next;
            fastPointer= fastPointer.next.next; 
        }
        return true;
    }
}

//logic behind the solution is that at some point fast pointer will be same as slow pointer 
// if there is no cycle then fast pointer will reach an end