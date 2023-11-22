public class ProductOfArrayExceptSelf {
    public static int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];

        // Perform Prefix product operation by traversing Left -> Right
        int prefix = 1;
        for (int i = 0; i < nums.length; i++) {
            result[i] = prefix;
            prefix *= nums[i];
        }

        // Perform Postfix product operation by traversing Right -> Left
        int postfix = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            result[i] *= postfix;
            postfix *= nums[i];
        }
        return result;
    }
}

class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        // Perform left product operation by traversing Left -> Right
        result[0] =1;
        for (int i = 1; i < n; i++) {
            result[i] = nums[i-1] * result[i-1];
        }

        // Perform right product operation by traversing Right -> Left
        int right_product = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] = result[i] * right_product;
            right_product = right_product * nums[i];
        }
        return result;
        
    }
}
