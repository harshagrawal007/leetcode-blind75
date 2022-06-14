class Solution {
    public boolean isAnagram(String s, String t) {
         
        if(s.length() != t.length()){
            return false;
        }
        
         int[] array= new int[26];
        for (char c: s.toCharArray()){
            array[c-'a']=    array[c-'a']+1;
        }
        
        for (char d: t.toCharArray()){
            
            if (array[d-'a'] <=0){
                return false;
            }
            array[d-'a']--;
        }
        
    return true;
        
    }
}
