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

// Using Priority queue min heap
//  Time Complexity:         O(n*log(k))
//  Extra Space Complexity:  O(k)
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>( new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1,ListNode o2){
                    return o1.val-o2.val;
            }
        });
        // PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>((a, b) -> a.val - b.val);
        // O(logk)
        for (ListNode node: lists) {
            if (node != null) {
                minHeap.add(node);
            }
        }
        // Take the smallest element from the minHeap and after adding to result ListNode,
        // take a next element from the same List

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!minHeap.isEmpty()) {
            tail.next = minHeap.poll();
            tail = tail.next;
            if (tail.next != null) {
                minHeap.add(tail.next);
            }
        }
        return dummy.next;
    }
}

//  Solution using Iterative Merge Sort
//  Time Complexity:         O(n*log(k))
//  Extra Space Complexity:  O(1)

class Solution2 {

    public ListNode mergeKLists(ListNode[] lists) {
        int size = lists.length;
        int interval = 1;

        // basically merge sets of two 
        // then add that merged linked list back to array
        // for e.g. list[0] = merge (lists[0], lists[0+1]);

        //lets say lists size is either 8 or 9 
        // then upper bound -> size-interval = 9-1 or 8-1
        // then increment i = i+ 2 * interval -> i = 0+ 2*2 -> 4
        // interval is also increasing with each iteration then upper bound decreases 

        while (interval < size) {
            for (int i = 0; i < size - interval; i += 2 * interval) {
                lists[i] = merge(lists[i], lists[i + interval]);
            }

            interval *= 2;
        }

        return size > 0 ? lists[0] : null;
    }

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