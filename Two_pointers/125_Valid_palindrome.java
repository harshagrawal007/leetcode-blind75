class Solution {
    public boolean isPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int left =0;
        int right = s.length()-1;
        int mid = left + (right - left) / 2;
        while(left<=mid && right>= mid ){
            if(s.charAt(left)  != s.charAt(right)){
                return false;
            }   
            ++left;
            --right;
        }
        return true;
    }
}
//replace all costs more than isLetterOrDigit

//faster
class Solution {
    public boolean isPalindrome(String s) {
        if (s.isEmpty()) {
        	return true;
        }
        int start = 0;
        int last = s.length() - 1;
        while(start <= last) {
        	char currFirst = s.charAt(start);
        	char currLast = s.charAt(last);
        	if (!Character.isLetterOrDigit(currFirst )) {
        		start++;
        	} else if(!Character.isLetterOrDigit(currLast)) {
        		last--;
        	} else {
        		if (Character.toLowerCase(currFirst) != Character.toLowerCase(currLast)) {
        			return false;
        		}
        		start++;
        		last--;
        	}
        }
        return true;
    }
}

// another way to do, using for 

class Solution {
    public boolean isPalindrome(String s) {
      for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
        while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
          i++;
        }
        while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
          j--;
        }
  
        if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j)))
          return false;
      }
  
      return true;
    }
  }