class Solution {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n = spells.length;
        int m = potions.length;
        int[] pairs = new int[n];
        Arrays.sort(potions);
        for (int i = 0; i < n; i++) {
            int spell = spells[i];
            int left = 0;
            int right = m - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                long product = (long) spell * potions[mid];
                if (product >= success) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            pairs[i] = m - left;
        }
        return pairs;
    }
}

/*

Intuition
For each spell, we need to find the number of potions that can form a successful pair with it. 
A pair is successful if the product of their strengths is at least the given success value. 
To find the number of successful pairs for each spell, we can iterate through the potions array and
check if the product of the spell strength and the potion strength is greater than or equal to the success value. 
If it is, we can increment a counter for that spell. We can then return the counters as an array.

Approach
We start by initializing the output array pairs with all zeros,
and sorting the potions array in ascending order.

For each spell in spells, we perform a binary search on the potions array to find the number of 
potions that form a successful pair with the current spell. We maintain two pointers left and right that
initially point to the first and last indices of the potions array, respectively.

We repeat the binary search until the left and right pointers meet or cross each other. 
In each iteration, we compute the product of the current spell and the middle potion using long integer
multiplication to avoid integer overflow. If the product is greater than or equal to the success threshold, 
we move the right pointer to the left of the middle index. Otherwise, we move the left pointer to the right of the middle index.

Once the binary search is complete, we set the corresponding element of pairs to the number of potions that 
come after the left pointer in the sorted potions array, which are guaranteed to form a successful pair with the current spell.

Finally, we return the pairs array as the result.

Complexity
Time complexity:
The time complexity of this solution is O(nlogm)O(n log m)O(nlogm), 
where n is the length of spells and m is the length of potions, due to the binary search.
The space complexity is O(1).

Space complexity:
The space complexity is O(1)O(1)O(1),
since we only use a constant amount of extra memory to store the pairs array and the binary search pointers.

*/