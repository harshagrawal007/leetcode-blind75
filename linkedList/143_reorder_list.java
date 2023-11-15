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
    public void reorderList(ListNode head) {
         // if head will be null or head.next will be null simply return ;
        if(head==null || head.next==null)return ;

        //head of the first half 
        ListNode l1 = head ;
        // mid of the linkedlist
        ListNode prev = mid(head);
        //head of second list
        ListNode prevNext = prev.next;
        // tail of first half
        prev.next =null;
        //head of reversed second list
        ListNode l2= reverse(prevNext);
        merge(l1, l2);

   }

   public ListNode mid (ListNode head){
        //find the middle 
        ListNode slow = head;
        ListNode fast = head;
        while (fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow=slow.next;
        }
        return slow;
   }

    public ListNode reverse (ListNode head){
        ListNode result = null;
        ListNode pointer = head ;
        while (pointer!=null){
            ListNode nextTemp = pointer.next;
            pointer.next= result;
            result = pointer;
            pointer = nextTemp;
        }
        return result;
    }


    public void merge (ListNode l1, ListNode l2){
        ListNode temp;
        while(l1!=null && l2!=null) {

        ListNode l1_next = l1.next;
        ListNode l2_next = l2.next;
        l1.next =l2;
        if(l1_next ==null){
            break;
        }
        l2.next=l1_next;
        l1=l1_next;
        l2=l2_next;
        }

    }

}