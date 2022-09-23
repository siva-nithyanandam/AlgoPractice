package MathAndLogic;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/11/2021  4:13 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/explore/interview/card/google/66/others-4/3100/
 *
 * You are playing the Bulls and Cows game with your friend.
 *
 * You write down a secret number and ask your friend to guess what the number is. When your friend makes a guess, you provide a hint with the following info:
 *
 * The number of "bulls", which are digits in the guess that are in the correct position.
 * The number of "cows", which are digits in the guess that are in your secret number but are located in the wrong position. Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
 * Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.
 *
 * The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows. Note that both secret and guess may contain duplicate digits.
 *
 *
 *
 * Example 1:
 *
 * Input: secret = "1807", guess = "7810"
 * Output: "1A3B"
 * Explanation: Bulls are connected with a '|' and cows are underlined:
 * "1807"
 *   |
 * "7810"
 * Example 2:
 *
 * Input: secret = "1123", guess = "0111"
 * Output: "1A1B"
 * Explanation: Bulls are connected with a '|' and cows are underlined:
 * "1123"        "1123"
 *   |      or     |
 * "0111"        "0111"
 * Note that only one of the two unmatched 1s is counted as a cow since the non-bull digits can only be rearranged to allow one 1 to be a bull.
 * Example 3:
 *
 * Input: secret = "1", guess = "0"
 * Output: "0A0B"
 * Example 4:
 *
 * Input: secret = "1", guess = "1"
 * Output: "1A0B"
 *
 *
 * Constraints:
 *
 * 1 <= secret.length, guess.length <= 1000
 * secret.length == guess.length
 * secret and guess consist of digits only.
 */
public class BullsAndCows {

  public static void main(String[] args) {
    BullsAndCows o = new BullsAndCows();
    System.out.println(o.getHint("1123", "0111"));
    System.out.println(o.getHint("1122", "1222"));
  }

  public String getHint(String secret, String guess) {

    int bulls = 0, cows = 0;
    int[] digits = new int[10];

    for(int i = 0 ; i < secret.length() ; i++)
    {
      char c1 = secret.charAt(i);
      char c2 = guess.charAt(i);
      if(c1 == c2)
      {
        bulls++;
      }
      else
      {
        if(digits[c1 - '0'] < 0)
          cows++;
        if(digits[c2 - '0'] > 0)
          cows++;
        digits[c1 - '0']++;
        digits[c2 - '0']--;
      }
    }

    return new StringBuilder().append(bulls).append("A").append(cows).append("B").toString();
  }
}
