package arrays;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/2/2021  6:36 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/explore/interview/card/google/59/array-and-strings/3056/
 * <p>
 * Sometimes people repeat letters to represent extra feeling, such as "hello" -> "heeellooo", "hi"
 * -> "hiiii".  In these strings like "heeellooo", we have groups of adjacent letters that are all
 * the same:  "h", "eee", "ll", "ooo".
 * <p>
 * For some given string s, a query word is stretchy if it can be made to be equal to s by any
 * number of applications of the following extension operation: choose a group consisting of
 * characters c, and add some number of characters c to the group so that the size of the group is 3
 * or more.
 * <p>
 * For example, starting with "hello", we could do an extension on the group "o" to get "hellooo",
 * but we cannot get "helloo" since the group "oo" has size less than 3.  Also, we could do another
 * extension like "ll" -> "lllll" to get "helllllooo".  If s = "helllllooo", then the query word
 * "hello" would be stretchy because of these two extension operations: query = "hello" -> "hellooo"
 * -> "helllllooo" = s.
 * <p>
 * Given a list of query words, return the number of words that are stretchy.
 * <p>
 * <p>
 * <p>
 * Example: Input: s = "heeellooo" words = ["hello", "hi", "helo"] Output: 1 Explanation: We can
 * extend "e" and "o" in the word "hello" to get "heeellooo". We can't extend "helo" to get
 * "heeellooo" because the group "ll" is not size 3 or more.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 0 <= len(s) <= 100. 0 <= len(words) <= 100. 0 <= len(words[i]) <= 100. s and all words in words
 * consist only of lowercase letters
 */
public class ExpressiveWords {

  public static void main(String[] args) {
    ExpressiveWords o = new ExpressiveWords();
    System.out.println(o.expressiveWords("heeellooo", new String[]{"hello", "hi", "helo"}));
  }

  public int expressiveWords(String s, String[] words) {

    int res = 0;
    for (String word : words) {
      if (isStretchy(s, word)) {
        res++;
      }
    }
    return res;
  }

  private boolean isStretchy(String s, String w) {

    int i = 0, j = 0;

    while (i < s.length() && j < w.length()) {

      if (s.charAt(i) != w.charAt(j)) {
        return false;
      }
      i++;
      j++;

      int p = 1, q = 1;
      while (i < s.length() && s.charAt(i) == s.charAt(i - 1)) {
        i++;
        p++;
      }

      while (j < w.length() && w.charAt(j) == w.charAt(j - 1)) {
        j++;
        q++;
      }

      if (p < q || (p > q && p < 3)) {
        return false;
      }
    }

    return i == s.length() && j == w.length();
  }
}
