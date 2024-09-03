/*
URL: https://leetcode.com/problems/sum-of-digits-of-string-after-convert/description/?envType=daily-question&envId=2024-09-03

1945. Sum of Digits of String After Convert

You are given a string s consisting of lowercase English letters, and an integer k.
First, convert s into an integer by replacing each letter with its position in the alphabet (i.e., replace 'a' with 1, 'b' with 2, ..., 'z' with 26). 
Then, transform the integer by replacing it with the sum of its digits. Repeat the transform operation k times in total.

For example, if s = "zbax" and k = 2, then the resulting integer would be 8 by the following operations:
	Convert: "zbax" ➝ "(26)(2)(1)(24)" ➝ "262124" ➝ 262124
	Transform #1: 262124 ➝ 2 + 6 + 2 + 1 + 2 + 4 ➝ 17
	Transform #2: 17 ➝ 1 + 7 ➝ 8
Return the resulting integer after performing the operations described above.
 
Example 1:
Input: s = "iiii", k = 1
Output: 36
Explanation: The operations are as follows:
- Convert: "iiii" ➝ "(9)(9)(9)(9)" ➝ "9999" ➝ 9999
- Transform #1: 9999 ➝ 9 + 9 + 9 + 9 ➝ 36
Thus the resulting integer is 36.

Example 2:
Input: s = "leetcode", k = 2
Output: 6
Explanation: The operations are as follows:
- Convert: "leetcode" ➝ "(12)(5)(5)(20)(3)(15)(4)(5)" ➝ "12552031545" ➝ 12552031545
- Transform #1: 12552031545 ➝ 1 + 2 + 5 + 5 + 2 + 0 + 3 + 1 + 5 + 4 + 5 ➝ 33
- Transform #2: 33 ➝ 3 + 3 ➝ 6
Thus the resulting integer is 6.

Example 3:
Input: s = "zbax", k = 2
Output: 8
 
Constraints:
	1 <= s.length <= 100
	1 <= k <= 10
	s consists of lowercase English letters.
*/
// Brute force Approach ---- TC - O(n+k×logN) = O(n) ---------- SC - O(n)
class Solution {
    public int getLucky(String s, int k) {
        StringBuilder result = new StringBuilder();
        for(char c : s.toCharArray()){
            int pos = c - 'a'+1;
            result.append(pos);
        }
        int num = sumOfDigits(result.toString());
        for(int i=1;i<k;i++){
            num = sumOfDigits(Integer.toString(num));
        }
        return num;
    }
    public int sumOfDigits(String numStr){
        int sum = 0;
        for(char c : numStr.toCharArray()){
            sum += c - '0';
        }
        return sum;
    }
}
// Optimal Approach --- TC - O(N+K∗Len(Num)) = O(n) --------- SC - O(1)
class Solution {
    public int getLucky(String s, int k) {
        int num = 0;
        // Step 1: Convert the string to its numeric representation and sum digits directly
        for(char c : s.toCharArray()){
            int pos = c - 'a' + 1;
            num += sumOfDigits(pos);
        }
        // Step 2: Perform the transformation k-1 times
        for(int i=1;i<k;i++){
            num = sumOfDigits(num);
        }
        return num;
    }
  // Helper function to calculate the sum of digits of a given number
    public static int sumOfDigits(int num) {
        int sum = 0;
        while (num > 0) {
            sum += num % 10; 
            num /= 10; 
        }
        return sum;
    }
}
