package backtracking;

/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-partitioning/
 *
 * Given a string s, partition s such that every substring of the partition is a palindrome. Return
 * all possible palindrome partitioning of s.
 *
 * A palindrome string is a string that reads the same backward as forward.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aab" Output: [["a","a","b"],["aa","b"]] Example 2:
 *
 * Input: s = "a" Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 16 s contains only lowercase English letters.
 */
public class PalindromePartitioning {

  public static void main(String[] args) {
    PalindromePartitioning o = new PalindromePartitioning();
    System.out.println(o.partition_faster("aab"));
    System.out.println(o.partition("a"));
    System.out.println(o.partition("aabcaaba"));
  }

  String[][] mem;
  ArrayList<List<String>> partitions;
  public List<List<String>> partition_faster(String s) {
    partitions = new ArrayList<List<String>>();
    mem = new String[s.length()][s.length()];
    ArrayList<String> curr = new ArrayList<String>();
    dfs(0,s,curr);
    return partitions;
  }

  public void dfs (int left, String s, ArrayList<String> curr){
    if(left>=s.length()){
      ArrayList<String> temp = new ArrayList<String>(curr);
      partitions.add(temp);
    }
    for(int right = left; right<s.length(); right++){
      if(mem[left][right] == null){
        if(palindrome(s.substring(left,right+1))){
          mem[left][right] = s.substring(left,right+1);
          curr.add(s.substring(left,right+1));
          dfs(right+1, s, curr);
          curr.remove(curr.size()-1);
        }else{
          mem[left][right] = "";
        }

      }else if(mem[left][right] != ""){
        curr.add(mem[left][right]);
        dfs(right+1, s, curr);
        curr.remove(curr.size()-1);
      }
    }
  }

  public Boolean palindrome(String s){
    int left = 0;
    int right = s.length() -1;
    while(left<=right){
      if(s.charAt(left) != s.charAt(right)) return false;
      left++;
      right--;
    }
    return true;
  }

  public List<List<String>> partition(String s) {
    List<List<String>> result = new ArrayList<>();
    List<String> subRes = new ArrayList<>();
    partition(s, s.toCharArray(), 0, result, subRes);
    return result;
  }

  private void partition(String s, char[] arr, int idx, List<List<String>> result,
      List<String> subRes) {

    if (idx == s.length()) {
      result.add(new ArrayList<>(subRes));
      return;
    }
    for (int i = idx; i < s.length(); i++) {
      if (isPalindrome(arr, idx, i)) {
        subRes.add(s.substring(idx, i+1));
        partition(s, arr, i + 1, result, subRes);
        subRes.remove(subRes.size()-1);
      }
    }
  }

  private boolean isPalindrome(char[] arr, int start, int end) {
    while (start < end) {
      if (arr[start++] != arr[end--]) {
        return false;
      }
    }
    return true;
  }
}
