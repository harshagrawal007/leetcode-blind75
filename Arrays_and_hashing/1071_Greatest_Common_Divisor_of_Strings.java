/*Suppose str1 = "ABCABC" and str2 = "ABC"
str1 + str2 = ABCABCABC
str2 + str1 = ABCABCABC
str1 + str2 == str2 + str1
return str.substr(0, gcd(size(str1), gcd(size(str2))))
where gcd(3, 6) = 3
So, answer is "ABC"

Also str1 = "LEET", str2 = "CODE"
str1 + str2 = "LEETCODE"
str2 + str1 = "CODELEET"
So, return ""
*/

class Solution {
    public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }

        int gcd = gcd(str1.length(), str2.length());
        return str2.substring(0, gcd);
    }

    public int gcd(int a, int b) {
        return (b == 0)? a : gcd(b, a % b);
    }
}
//calculating greatest common divisor of the string lengths
class Solution {
public String gcdOfStrings(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        int gcdLength = gcd(str1.length(), str2.length());
        return str1.substring(0, gcdLength);
    }
    
    public int gcd(int x, int y) {
            if (y == 0) {
                return x;
            } else {
                return gcd(y, x % y);
            }    
    }
}