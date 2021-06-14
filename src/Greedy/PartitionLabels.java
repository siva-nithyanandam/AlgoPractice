package Greedy;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/14/2021  1:08 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.ArrayList;
import java.util.List;

/**
 *
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
