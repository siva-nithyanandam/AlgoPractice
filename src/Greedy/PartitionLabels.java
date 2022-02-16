package Greedy;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/14/2021  1:08 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/partition-labels/
 *
 * You are given a string s. We want to partition the string into as many parts as possible so that each letter appears in at most one part.
 *
 * Return a list of integers representing the size of these parts.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "ababcbacadefegdehijhklij"
 * Output: [9,7,8]
 * Explanation:
 * The partition is "ababcbaca", "defegde", "hijhklij".
 * This is a partition so that each letter appears in at most one part.
 * A partition like "ababcbacadefegde", "hijhklij" is incorrect, because it splits s into less parts.
 * Example 2:
 *
 * Input: s = "eccbbbbdec"
 * Output: [10]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of lowercase English letters.
 */
public class PartitionLabels {

  public static void main(String[] args) {
    PartitionLabels o = new PartitionLabels();
    System.out.println(o.partitionLabels("ababcbacadefegdehijhklij"));
  }

  public List<Integer> partitionLabels(String S) {
    int[] last = new int[26];
    for (int i = 0; i < S.length(); ++i)
      last[S.charAt(i) - 'a'] = i;

    int j = 0, anchor = 0;
    List<Integer> ans = new ArrayList();
    for (int i = 0; i < S.length(); ++i) {
      j = Math.max(j, last[S.charAt(i) - 'a']);
      if (i == j) {
        ans.add(i - anchor + 1);
        anchor = i + 1;
      }
    }
    return ans;
  }
}
