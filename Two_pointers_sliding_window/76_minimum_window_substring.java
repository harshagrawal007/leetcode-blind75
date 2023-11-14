class Solution {

    //sliding window
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        // frequency map, add all charcters from t to this map
        for (char x : t.toCharArray()) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        int matched = 0;
        //start pointer, at first element
        int start = 0;
        //we need to find min length keep is bigger than size of s string
        int minLen = s.length() + 1;
        int subStr = 0;
        //start endwindow at first element
        for (int endWindow = 0; endWindow < s.length(); endWindow++) {
            //get the character at endwindow
            char right = s.charAt(endWindow);
            //check if character exists in frequency map
            if (map.containsKey(right)) {
                //decrease the current count of that character in frequency map
                map.put(right, map.get(right) - 1);
                // if we decreased the count to zero then we increase the count of matched variable
                if (map.get(right) == 0) matched++;
            }
            // if the value of matched variable is equals to size of frequence map 
            while (matched == map.size()) {
                // check in minlen of substring is greater then endwindow - start window  +1
                if (minLen > endWindow - start + 1) {
                    //if true then assing that value to minlen
                    minLen = endWindow - start + 1;
                    subStr = start;
                }
                char deleted = s.charAt(start++);
                if (map.containsKey(deleted)) {
                    if (map.get(deleted) == 0) matched--;
                    map.put(deleted, map.get(deleted) + 1);
                }
            }
        }
        return minLen > s.length() ? "" : s.substring(subStr, subStr + minLen);
    }
}