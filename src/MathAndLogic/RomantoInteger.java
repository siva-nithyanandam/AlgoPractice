package MathAndLogic;

/**
 *
 * https://leetcode.com/problems/roman-to-integer/
 *
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 *
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * For example, 2 is written as II in Roman numeral, just two one's added together. 12 is written as XII, which is simply X + II. The number 27 is written as XXVII, which is XX + V + II.
 *
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 *
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "III"
 * Output: 3
 * Example 2:
 *
 * Input: s = "IV"
 * Output: 4
 * Example 3:
 *
 * Input: s = "IX"
 * Output: 9
 * Example 4:
 *
 * Input: s = "LVIII"
 * Output: 58
 * Explanation: L = 50, V= 5, III = 3.
 * Example 5:
 *
 * Input: s = "MCMXCIV"
 * Output: 1994
 * Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 15
 * s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
 * It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 */
public class RomantoInteger {

  public static void main(String[] args) {
    RomantoInteger o = new RomantoInteger();
    System.out.println(o.romanToInt("III"));
    System.out.println(o.romanToInt("IV"));
    System.out.println(o.romanToInt("IX"));
    System.out.println(o.romanToInt("LVIII"));
    System.out.println(o.romanToInt("MCMXCIV"));
  }

  public int romanToInt_faster(String s) {
    int result = 0;
    for(int i = s.length()-1; i >= 0; i--) {
      switch(s.charAt(i)) {
        case 'I':
          result += (result >= 5 ? -1 : 1);
          break;
        case 'V':
          result += 5;
          break;
        case 'X':
          result += (result >= 50 ? -10 : 10);
          break;
        case 'L':
          result += 50;
          break;
        case 'C':
          result += (result >= 500 ? -100 : 100);
          break;
        case 'D':
          result += 500;
          break;
        case 'M':
          result += 1000;
          break;
        default:
          return 0;
      }
    }
    return result;
  }

  public int romanToInt(String s) {
    int res = 0;
    int len = s.length();
    for (int i = 0; i < len; i++) {
      switch(s.charAt(i)) {
        case 'I':
          if (i < len-1) {
            if (s.charAt(i+1) == 'V') {
              res += 4;
              i++;
            } else if (s.charAt(i+1) == 'X') {
              res += 9;
              i++;
            } else {
              res += 1;
            }
          } else {
            res += 1;
          }
          break;
        case 'V':
          res += 5;
          break;
        case 'X':
          if (i < len-1) {
            if (s.charAt(i+1) == 'L') {
              res += 40;
              i++;
            } else if (s.charAt(i+1) == 'C') {
              res += 90;
              i++;
            } else {
              res += 10;
            }
          } else {
            res += 10;
          }
          break;
        case 'L':
          res += 50;
          break;
        case 'C':
          if (i < len-1) {
            if (s.charAt(i+1) == 'D') {
              res += 400;
              i++;
            } else if (s.charAt(i+1) == 'M') {
              res += 900;
              i++;
            } else {
              res += 100;
            }
          } else {
            res += 100;
          }
          break;
        case 'D':
          res += 500;
          break;
        case 'M':
          res += 1000;
          break;
      }
    }
    return res;
  }
}
