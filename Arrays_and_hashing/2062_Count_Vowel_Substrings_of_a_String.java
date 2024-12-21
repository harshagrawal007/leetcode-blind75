class Solution {
    public int countVowelSubstrings(String word) {
        if(word == null || word.length() < 5) return 0;
        
        Map<Character, Integer> vowelIdxMap = new HashMap<>();
        char[] vowels = new char[] {'a', 'e', 'i', 'o', 'u'};
        
        for(char c : vowels) {
            vowelIdxMap.put(c, -1);
        }

        int lastConstantIdx = -1, count = 0;
        //aebaeiou
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i); // a
           
            if(!vowelIdxMap.containsKey(c)) // false
                lastConstantIdx = i;
            else {
                vowelIdxMap.put(c, i);      // a-> 0
                int minVowelIdx = word.length(); // 8
                for(int idx : vowelIdxMap.values())
                    minVowelIdx = Math.min(minVowelIdx, idx); // -1
                count += Math.max(minVowelIdx - lastConstantIdx, 0); 
            }
        }
        /*
        Keep track of 6 values: the last index we saw a consonant, and the last
        index where we saw each of the 5 vowels. 
        At each index: the number of valid substrings ending here is the
        distance backwards from the earliest last seen vowel to the last 
        seen consonant.
        */
            
        return count;
    }
}