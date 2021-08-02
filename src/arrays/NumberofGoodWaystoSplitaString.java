package arrays;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/20/2021  8:35 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/problems/number-of-good-ways-to-split-a-string/
 *
 * You are given a string s, a split is called good if you can split s into 2 non-empty strings p and q where its concatenation is equal to s and the number of distinct letters in p and q are the same.
 *
 * Return the number of good splits you can make in s.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aacaba"
 * Output: 2
 * Explanation: There are 5 ways to split "aacaba" and 2 of them are good.
 * ("a", "acaba") Left string and right string contains 1 and 3 different letters respectively.
 * ("aa", "caba") Left string and right string contains 1 and 3 different letters respectively.
 * ("aac", "aba") Left string and right string contains 2 and 2 different letters respectively (good split).
 * ("aaca", "ba") Left string and right string contains 2 and 2 different letters respectively (good split).
 * ("aacab", "a") Left string and right string contains 3 and 1 different letters respectively.
 * Example 2:
 *
 * Input: s = "abcd"
 * Output: 1
 * Explanation: Split the string as follows ("ab", "cd").
 * Example 3:
 *
 * Input: s = "aaaaa"
 * Output: 4
 * Explanation: All possible splits are good.
 * Example 4:
 *
 * Input: s = "acbadbaada"
 * Output: 2
 *
 *
 * Constraints:
 *
 * s contains only lowercase English letters.
 * 1 <= s.length <= 10^5
 */
public class NumberofGoodWaystoSplitaString {

  public static void main(String[] args) {
    NumberofGoodWaystoSplitaString o = new NumberofGoodWaystoSplitaString();
    System.out.println(o.numSplits("aacaba"));
  }

  public int numSplits_Better(String s) {
    if (s == null || s.length() == 0) return 0;
    int n = s.length();
    boolean[] leftVisited = new boolean[26];
    boolean[] rightVisited = new boolean[26];
    char[] cArray = s.toCharArray();
    int[] leftCount = new int[n];
    int count = 0;

    for(int i=0; i<n; i++){
      if(!leftVisited[cArray[i]-'a']){
        leftVisited[cArray[i]-'a'] = true;
        count++;
      }
      leftCount[i] = count;
    }
    int rightCount = 0;
    int res = 0;
    for(int i=n-1; i>0; i--){
      if(!rightVisited[cArray[i]-'a']){
        rightVisited[cArray[i]-'a'] = true;
        rightCount++;
      }
      if(rightCount==leftCount[i-1]){
        res++;
      }
    }
    return res;
  }

  public int numSplits(String s) {

    int[] right = new int[26];
    int[] left = new int[26];

    for (char c : s.toCharArray()) {
      right[c - 'a']++;
    }

    int res = 0;
    for (char c : s.toCharArray()) {
      right[c - 'a']--;
      left[c - 'a']++;

      int check = 0;
      for (int i = 0; i < 26; i++) {
        check += left[i] > 0 ? 1 : 0;
        check -= right[i] > 0 ? 1 : 0;
      }
      res += check == 0 ? 1 : 0;
    }
    return res;
  }
}
