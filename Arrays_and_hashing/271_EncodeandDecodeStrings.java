import java.util.ArrayList;
import java.util.List;

/**
 * LeetCode 271: Encode and Decode Strings
 *
 * PROBLEM: Design an algorithm to encode a list of strings to a single string, and
 * decode it back to the original list. The encoding must handle arbitrary string
 * contents (including empty strings and strings containing digits, letters, and
 * special characters).
 *
 * KEY INSIGHT:
 * - Use a length-prefix encoding: store each string as "<len>#<string>".
 * - During decoding, parse the length prefix, then read exactly that many characters.
 * - This avoids ambiguity even when the string contains the delimiter.
 */
public class Codec {
     /**
     * Encodes a list of strings to a single string.
     * Example: ["Hello", "World"] -> "5#Hello5#World"
     */
    public String encode(List<String> strs) {
         StringBuilder encodedString = new StringBuilder();
        for(String str: strs){
            int length = str.length();
            encodedString.append(length+"#");
            encodedString.append(str);
        }
        return encodedString.toString();
    }
    /**
     * Decodes a single string back into a list of strings.
     */
    public List<String> decode(String s) {
        // System.out.println(s);
         List<String> decodedStrings = new ArrayList();
        for(int i =0;i<s.length();i++){
            String length = "";
//length could be 2 digit or 3 digits
            while(s.charAt(i) != '#'){
                length += s.charAt(i);
                i++;
            }
            int wordLength = Integer.parseInt(length);
            i++;

            String word = "";
            for(int j=i;j<wordLength+i;j++){
                word += s.charAt(j);
            }
            decodedStrings.add(word);
          // increment i to point to first character of length e.g. 45#blahablah
            i=i+wordLength-1;
        }
        return decodedStrings;
    }
}



//Faster way

public class Codec {
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        
        // ["Hello","World"] => "5#Hello5#World"
        for (String str : strs) {
            sb.append(str.length());
            sb.append("#");
            sb.append(str);
        }
        
        return sb.toString();
    }

    public List<String> decode(String s) {
        List<String> list = new ArrayList<>();
        
        int i = 0;
        
        // "5#Hello5#World"
        //  i
        //   j
        //         ↑
        //   (j + len + 1)
        
        while (i < s.length()) {
            int j = i;
            while (s.charAt(j) != '#') {
                j++;
            }
            int len = Integer.parseInt(s.substring(i, j));
            list.add(s.substring(j + 1, j + len + 1));
            i = j + len + 1;
        }
        
        return list;
    }
}


/* ==========================================================================
 * COMPLEXITY ANALYSIS
 * ==========================================================================
 * Time: O(n) where n is the total length of all strings.
 * Space: O(n) for the encoded string and decoded list.
 *
 * The overall work is linear in the size of the input data.
 * ========================================================================== */

/* ==========================================================================
 * KEY CITATIONS & REFERENCES
 * ==========================================================================
 * 1. LeetCode discussion: "Encode and Decode Strings".
 * 2. Serialization patterns: length-prefixed encoding in network protocols.
 * 3. Related problem: "Serialize and Deserialize Binary Tree" (LeetCode 297).
 * ========================================================================== */

