class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // if you can complete the cycle the total tank will always be positive or exactly zero
        // since we are travelling from each point once, and its only possible either we have extra gas left 
        // also doesnt matter if you subtract cost to travel first or later 
        int total_tank =0, curr_tank=0;
        //current tank tells the fill and usage from current position to next 
        //if current tank is in negative value that means current position cant be starting point 
        // if we were to travel over the current point to another we must have had gas from somewhere else
        int start=0;
        for (int i= 0; i< gas.length; i++){
            total_tank= total_tank + gas[i] - cost[i];
            curr_tank = curr_tank+ gas[i] -cost[i];
            if ( curr_tank < 0){
                start = i+1;
                curr_tank =0;
            }
        }
        return total_tank >=0 ? start : -1;
    }
}