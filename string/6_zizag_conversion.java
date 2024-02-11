/* 
A _ _ _ I _ _ _ Q _ _ _ 
B _ _ H J _ _ P R _ _ X 
C _ G _ K _ O _ S _ W _ 
D F _ _ L N _ _ T V _ _ 
E _ _ _ M _ _ _ U _ _ _ 

append all the letters in first row then second row 
section 1

A _ _ _ 
B _ _ H 
C _ G _ 
D F _ _ 
E _ _ _ 

sectiion 2
I _ _ _ 
J _ _ P 
K _ O _ 
L N _ _ 
M _ _ _ 

section 3
Q _ _ _ 
R _ _ X 
S _ W _ 
T V _ _ 
U _ _ _ 

*/

class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        
        StringBuilder answer = new StringBuilder();
        int n = s.length();
        int charsInSection = 2 * (numRows - 1);
        
        for (int currRow = 0; currRow < numRows; ++currRow) {
            int index = currRow;
            while (index < n) {
                answer.append(s.charAt(index));

                // If currRow is not the first or last row
                // then we have to add one more character of current section.
                if (currRow != 0 && currRow != numRows - 1) {
                    int charsInBetween = charsInSection - 2 * currRow;
                    int secondIndex = index + charsInBetween;
                    
                    if (secondIndex < n) {
                        answer.append(s.charAt(secondIndex));
                    }
                }
                // Jump to same row's first character of next section.
                index += charsInSection;
            }
        }
        
        return answer.toString();
    }
}

