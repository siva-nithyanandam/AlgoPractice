package arrays;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/2/2021  7:09 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * https://leetcode.com/explore/interview/card/google/59/array-and-strings/3057/
 *
 * You are given a 0-indexed string s that you must perform k replacement operations on.
 * The replacement operations are given as three 0-indexed parallel arrays, indices, sources, and targets, all of length k.
 *
 * To complete the ith replacement operation:
 *
 * Check if the substring sources[i] occurs at index indices[i] in the original string s.
 * If it does not occur, do nothing.
 * Otherwise if it does occur, replace that substring with targets[i].
 * For example, if s = "abcd", indices[i] = 0, sources[i] = "ab", and targets[i] = "eee", then the result of this replacement will be "eeecd".
 *
 * All replacement operations must occur simultaneously, meaning the replacement operations should not affect the indexing of each other. The testcases will be generated such that the replacements will not overlap.
 *
 * For example, a testcase with s = "abc", indices = [0, 1], and sources = ["ab","bc"] will not be generated because the "ab" and "bc" replacements overlap.
 * Return the resulting string after performing all replacement operations on s.
 *
 * A substring is a contiguous sequence of characters in a string.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: s = "abcd", indices = [0, 2], sources = ["a", "cd"], targets = ["eee", "ffff"]
 * Output: "eeebffff"
 * Explanation:
 * "a" occurs at index 0 in s, so we replace it with "eee".
 * "cd" occurs at index 2 in s, so we replace it with "ffff".
 * Example 2:
 *
 *
 * Input: s = "abcd", indices = [0, 2], sources = ["ab","ec"], targets = ["eee","ffff"]
 * Output: "eeecd"
 * Explanation:
 * "ab" occurs at index 0 in s, so we replace it with "eee".
 * "ec" does not occur at index 2 in s, so we do nothing.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * k == indices.length == sources.length == targets.length
 * 1 <= k <= 100
 * 0 <= indexes[i] < s.length
 * 1 <= sources[i].length, targets[i].length <= 50
 * s consists of only lowercase English letters.
 * sources[i] and targets[i] consist of only lowercase English letters.
 */
public class FindAndReplaceinString {

  public static void main(String[] args) {
    FindAndReplaceinString o = new FindAndReplaceinString();
    System.out.println(o.findReplaceString_faster("abcd", new int[]{0, 2}, new String[]{"a", "cd"}, new String[]{"eee", "ffff"}));
  }

  public String findReplaceString_faster(String S, int[] indexes, String[] sources, String[] targets)    {
    // store string index that that should be replaced.
    int[] matchingIndex = new int[S.length()];
    Arrays.fill(matchingIndex, -1);
    for (int i = 0; i < indexes.length; i++) {
      if (indexes[i] < S.length() && S.startsWith(sources[i], indexes[i])) { //match found
        matchingIndex[indexes[i]] = i;
      }
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < matchingIndex.length; )
      if (matchingIndex[i] != -1) {
        sb.append(targets[matchingIndex[i]]);
        i += sources[matchingIndex[i]].length(); //very important
      } else {
        sb.append(S.charAt(i));
        i++;
      }
    return sb.toString();
  }


  public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {

    List<int[]> sorted = new ArrayList<>();
    for (int i = 0; i < indices.length; i++) {
      sorted.add(new int[]{indices[i], i});
    }

    Collections.sort(sorted, Comparator.comparing(i -> -i[0]));

    for (int[] arr : sorted) {
      int i = arr[0];
      int j = arr[1];
      String tempSource = sources[j];
      String tempTarget = targets[j];

      if (s.substring(i, i+tempSource.length()).equals(tempSource)) {
        s = s.substring(0, i) + tempTarget + s.substring(i+tempSource.length());
      }
    }

    return s;
  }

}
