
//using two pointers
class Solution {
    public int maxProfit(int[] prices) {
        int leftPtr = 0;
        int rightptr = 1;
        int maxProfit= 0;
        while (rightptr < prices.length){
            if(prices[rightptr] > prices[leftPtr] ) {
                maxProfit = Math.max(maxProfit, (prices[rightptr] - prices[leftPtr]));
            } else{
                leftPtr = rightptr;
            }
            rightptr++;
        }

        return maxProfit;
    }
}

// working based on profit, using minprice and maxprofit
class Solution {
    public int maxProfit(int[] prices) {
   
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
}