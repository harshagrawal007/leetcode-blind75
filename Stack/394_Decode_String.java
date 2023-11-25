class Solution {
    public String decodeString(String s) {
        
        Stack<Integer> counts= new Stack();
        Stack<String> result = new Stack();
        StringBuilder res = new StringBuilder();
        int index =0;
        while(index<s.length()){
            if(Character.isDigit(s.charAt(index))){
                int count =0;
                while(Character.isDigit(s.charAt(index))){
                    count = 10* count + (s.charAt(index) - '0');
                    index +=1;
                }
                counts.push(count);
            } else if (s.charAt(index) =='['){
                result.push(res.toString());
                res = new StringBuilder();
                index +=1;
            } else if (s.charAt(index) == ']'){
                StringBuilder temp = new StringBuilder(result.pop());
                int count = counts.pop();
                for(int i=0;i<count;i++){
                    temp.append(res);
                }
                res= temp;
                index +=1;
            } else{
                res.append(s.charAt(index));
                index += 1;
            }
        }
        return res.toString();
    }
}