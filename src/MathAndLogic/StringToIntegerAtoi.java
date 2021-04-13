package MathAndLogic;

/**
 * Implement atoi which converts a string to an integer.
 *
 * The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.
 *
 * The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.
 *
 * If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned.
 *
 * Note:
 *
 * Only the space character ' ' is considered a whitespace character.
 * Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. If the numerical value is out of the range of representable values, 231 − 1 or −231 is returned.
 *
 *
 * Example 1:
 *
 * Input: str = "42"
 * Output: 42
 * Example 2:
 *
 * Input: str = "   -42"
 * Output: -42
 * Explanation: The first non-whitespace character is '-', which is the minus sign. Then take as many numerical digits as possible, which gets 42.
 * Example 3:
 *
 * Input: str = "4193 with words"
 * Output: 4193
 * Explanation: Subsets stops at digit '3' as the next character is not a numerical digit.
 * Example 4:
 *
 * Input: str = "words and 987"
 * Output: 0
 * Explanation: The first non-whitespace character is 'w', which is not a numerical digit or a +/- sign. Therefore no valid conversion could be performed.
 * Example 5:
 *
 * Input: str = "-91283472332"
 * Output: -2147483648
 * Explanation: The number "-91283472332" is out of the range of a 32-bit signed integer. Thefore INT_MIN (−231) is returned.
 *
 *
 * Constraints:
 *
 * 0 <= s.length <= 200
 * s consists of English letters (lower-case and upper-case), digits, ' ', '+', '-' and '.'.
 */
public class StringToIntegerAtoi {

  public static void main(String[] args) {
    StringToIntegerAtoi o = new StringToIntegerAtoi();
    System.out.println(o.myAtoi("42"));
    System.out.println(o.myAtoi("   -42"));
    System.out.println(o.myAtoi("4193 with words"));
    System.out.println(o.myAtoi("words and 987"));
    System.out.println(o.myAtoi("-91283472332"));
    System.out.println(o.myAtoi("+1"));
    System.out.println(o.myAtoi("+-12"));
  }
  public int myAtoi(String s) {
    if (s.length() == 0) {
      return 0;
    }
    Long val = 0L;
    boolean isNegative = false;
    boolean foundNbr = false;
    for (char c : s.toCharArray()) {
      switch(c) {
        case ' ':
          if (foundNbr) {
            if (isNegative) {
              return -1 * val.intValue();
            } else {
              return val.intValue();
            }
          }
          break;
        case '-':
          if (foundNbr) {
            if (isNegative) {
              return -1 * val.intValue();
            } else {
              return val.intValue();
            }
          } else {
            isNegative = true;
            foundNbr = true;
          }
          break;
        case '+':
          if (foundNbr) {
            if (isNegative) {
              return -1 * val.intValue();
            } else {
              return val.intValue();
            }
          } else {
            foundNbr = true;
          }
          break;
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
          foundNbr = true;
          val = (val * 10) + (c - '0');
          if (val > Integer.MAX_VALUE) {
            if (isNegative) {
              return Integer.MIN_VALUE;
            } else {
              return Integer.MAX_VALUE;
            }
          }
          break;
        default:
          if (foundNbr) {
            if (isNegative) {
              return -1 * val.intValue();
            } else {
              return val.intValue();
            }
          } else {
            return 0;
          }
      }
    }
    if (isNegative) {
      return -1 * val.intValue();
    } else {
      return val.intValue();
    }
  }
}
