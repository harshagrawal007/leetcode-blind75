class Solution {
   
    public int maxVowels(String s, int k) {
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
        int count= 0;
        // Build the window of size k, count the number of vowels it contains.

        for(int i = 0; i < k; i ++) {
            if(vowels.contains(s.charAt(i))){
                count++;
            }
        }
        int result = count;
        for (int i=k ;i< s.length(); i++){
            if(vowels.contains(s.charAt(i))){
                count++;
            }
            if(vowels.contains(s.charAt(i - k))){
                count--;
            }   
            result = Math.max(result, count);
        }
        return result;
    }
}