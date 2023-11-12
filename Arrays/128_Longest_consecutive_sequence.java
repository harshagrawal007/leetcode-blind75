class Solution {
    public int longestConsecutive(int[] nums) {
        
        // add all number in array to hashset
        // max length of hastset is equals to size of nums array
        HashSet<Integer> numSet = new HashSet<>();
        for (int num: nums ){
            numSet.add(num);
        }
       // longest sequence is of length 0
        int longestSequence =0;
        for (int num: nums ){
            //check if start of sequence
            // if a number one less than current number doesnt exist in set, then the number is start of sequence
           if( !numSet.contains(num-1)){
            // start length of sequence as 0
               int length =0;
               while (numSet.contains(num + length)){
                   length ++;
                   longestSequence = Math.max(length,longestSequence );
                      
               }
           }
                
        }
           return longestSequence;
    }
                   
}