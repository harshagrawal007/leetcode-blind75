/*

A string a is lexicographically smaller than a string b if in the first position 
where a and b differ, string a has a letter that appears earlier in the alphabet
than the corresponding letter in b. If the first min(a.length, b.length)
 characters do not differ, then the shorter string is the lexicographically 
 smaller one.

ques: Given a string s, remove duplicate letters so that every letter appears 
once and only once. You must make sure your result is the smallest in 
lexicographical order among all possible results

ques: Given a string s, return the lexicographically smallest subsequence of s 
that contains all the distinct characters of s exactly once.
*/


class Solution {
    public String smallestSubsequence(String s) {
         int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++){
            lastIndex[s.charAt(i) - 'a'] = i; // track the lastIndex of character presence
        }
        boolean[] seen = new boolean[26]; // keep track seen
        Stack<Integer> st = new Stack();
        for (int i = 0; i < s.length(); i++) {
            int curr = s.charAt(i) - 'a';
            if (seen[curr]) continue; // if seen continue as we need to pick one char only
            while (!st.isEmpty() && st.peek() > curr && i < lastIndex[st.peek()]){
                seen[st.pop()] = false; // pop out and mark unseen
            }
            st.push(curr); // add into stack
            seen[curr] = true; // mark seen
        }
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty())
            sb.append((char) (st.pop() + 'a'));
        return sb.reverse().toString();
    }
}

class Solution {
    public String smallestSubsequence(String s) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++){
            lastIndex[s.charAt(i) - 'a'] = i; // track the lastIndex of character presence
        }
        boolean[] seen = new boolean[26]; // keep track seen
        Stack<Integer> st = new Stack();// b,c,a,b
        for (int i = 0; i < s.length(); i++) {
             //current character.
            int curr = s.charAt(i) - 'a'; 
            //if we have already included the current character into the stack, just skip it.
            //if we haven't included the element, we have to include it anyhow. 
            //Also we are marking it as 'seen'.. 
            //by updating the value by 1 at that index.

            if (seen[curr]++>0) continue; 
            // if seen continue as we need to pick one char only
            while (!st.isEmpty() && st.peek() > curr && i < lastIndex[st.peek()]){
                //if the incoming character is smaller && we are sure that the 
                //peek() element will occur in future as well.. 
                //then we will just pop that element and make the seen 0 for that element.
                seen[st.pop()] = false; 
                // pop out and mark unseen
            }
            //since it was a new character, we have to include it.
            st.push(curr); // add into stack
            
        }
         //now our stack contains the desired result, append each into the string.
        //note we are just traversing the stack.. not popping, so the elements are in fifo style.
        StringBuilder sb = new StringBuilder();
        for (int i : st) sb.append((char)('a' + i));
        
        //return the string.
        return sb.toString();
    }
}