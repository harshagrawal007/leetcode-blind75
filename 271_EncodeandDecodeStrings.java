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
