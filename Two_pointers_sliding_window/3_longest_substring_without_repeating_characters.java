class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0 || s.length() == 1) return s.length();
        int leftptr =0,rightptr=0;
        int maxsize= 0;
        HashSet<Character> charset = new HashSet<>();
        while (rightptr < s.length()){
            if(!charset.contains(s.charAt(rightptr))){
                charset.add(s.charAt(rightptr));
                rightptr++;
            } else {
                charset.remove(s.charAt(leftptr));
                leftptr++;
            }
            maxsize = Math.max(maxsize,rightptr-leftptr);
        }
        return maxsize;
    }
}