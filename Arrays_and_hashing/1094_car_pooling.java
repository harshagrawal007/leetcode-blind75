/*
There is a car with capacity empty seats.The vehicle only
drives east (i.e., it cannot turn around and drive west).

You are given the integer capacity and an array trips where 
trips[i] = [numPassengersi, fromi, toi] indicates that the ith trip
has numPassengersi passengers and the locations to pick them up and 
drop them off are fromi and toi respectively. The locations are given
as the number of kilometers due east from the car's initial location.

Return true if it is possible to pick up and drop off all passengers 
for all the given trips, or false otherwise.
*/ 

class Solution {
    public boolean carPooling(int[][] trips, int capacity) {
        int[] locations=new int[1001];
        for(int trip[]:trips){
            int start=trip[1];
            int end=trip[2];
            int passengers=trip[0];
            locations[start]+=passengers;
            locations[end]-=passengers;
        }
        int totalPassengers=0;
        for(int location=0;location<=1000;location++){
            totalPassengers+=locations[location];
            if(totalPassengers>capacity){
                return false;  
            }
        }
        return true;
    }
}