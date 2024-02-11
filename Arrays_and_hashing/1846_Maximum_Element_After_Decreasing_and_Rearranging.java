class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        int n = arr.length;
        int[] counts = new int[n + 1];
        
        for (int num : arr) {
            counts[Math.min(num, n)]++;
        }
        
        int ans = 1;
        for (int num = 2; num <= n; num++) {
            ans = Math.min(ans + counts[num], num);
        }
        
        return ans;
    }
}
Initialize an array counts with length n + 1 and values of 0.
Iterate over each num in arr:
Increment counts[min(num, n)].
Initialize ans = 1.
Iterate num from 2 to n:
Set ans = min(ans + counts[num], num).
Return ans.




/* 
Given nnn as the length of arr,

Time complexity: O(n)

To calculate counts, we iterate over arr once which costs O(n).
Then, we iterate between 2 and n. which costs O(n).

Space complexity: O(n)
counts has a length of n+1 .
*/

//with sorting

class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);
        int ans = 1;
        
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] >= ans + 1) {
                ans++;
            }
        }
        
        return ans;
    }
}

/* 
Given n as the length of arr,

Time complexity: O(n⋅log⁡n)
We sort arr which costs O(n⋅log⁡n). Then, we iterate over it once which costs O(n).

Space Complexity: O(log⁡n) or O(n)
The space complexity of the sorting algorithm depends on the implementation of each programming language:

In Java, Arrays.sort() for primitives is implemented using a variant of the Quick Sort algorithm, 
which has a space complexity of O(log⁡n)