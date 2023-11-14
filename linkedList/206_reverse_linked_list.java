class Solution {
    public ListNode reverseList(ListNode head) {
        
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
}