class Solution {
    public int largestAltitude(int[] gain) {
        int max = 0;
        int[] altitude = new int[gain.length+1];
        altitude[0]=0;
        for(int i =0; i< gain.length;i++){
            altitude[i+1]= altitude[i] + gain[i];
            
            if(max< altitude[i+1]){
                max= altitude[i+1];
            }
        }
        //Arrays.stream(altitude).forEach(System.out::println);
            return max;
        }
    }
