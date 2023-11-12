public class Codec {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
         StringBuilder encodedString = new StringBuilder();
        for(String str: strs){
            int length = str.length();
            encodedString.append(length+"#");
            encodedString.append(str);
        }
        return encodedString.toString();
    }

    // Decodes a single string to a list of strings.
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
