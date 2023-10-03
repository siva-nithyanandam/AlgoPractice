package MathAndLogic;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/group-anagrams/
 *
 * Given an array of strings strs, group the anagrams together. You can return the answer in any
 * order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 *
 *
 * Example 1:
 *
 * Input: strs = ["eat","tea","tan","ate","nat","bat"] Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 * Example 2:
 *
 * Input: strs = [""] Output: [[""]] Example 3:
 *
 * Input: strs = ["a"] Output: [["a"]]
 *
 *
 * Constraints:
 *
 * 1 <= strs.length <= 104 0 <= strs[i].length <= 100 strs[i] consists of lower-case English
 * letters.
 */
public class GroupAnagrams {

  public static void main(String[] args) {
    GroupAnagrams ga = new GroupAnagrams();
    List<List<String>> result = ga
        .groupAnagrams_faster(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"});
    result = ga
        .groupAnagrams_faster(new String[]{"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
                "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab"});
  }

  public List<List<String>> groupAnagrams_faster(String[] strs) {
    List<List<String>> result = new ArrayList<List<String>>();
    HashMap<BigInteger, List<String>> keyAnagramListMap = new HashMap<>();
    BigInteger key;
    for (String str : strs) {
      key = calKey(str);
      if (keyAnagramListMap.containsKey(key)) {
        keyAnagramListMap.get(key).add(str);
      } else {
        List<String> agList = new ArrayList<>();
        agList.add(str);
        keyAnagramListMap.put(key, agList);
      }
    }
    for (List<String> anagramList : keyAnagramListMap.values()) {
      result.add(anagramList);
    }
    return result;
  }

  int[] alphabets = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43,
      47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
  // 2. calculate key
  private BigInteger calKey(String s) {
    BigInteger key = BigInteger.ONE;
    for (int i = 0; i < s.length(); i++) {
      key = key.multiply(BigInteger.valueOf(alphabets[s.charAt(i) - 97]));
    }
    return key;
  }

  public List<List<String>> groupAnagrams(String[] strs) {
    List<List<String>> result = new ArrayList<>();
    Map<String, List<String>> map = new HashMap<>();
    for (String str : strs) {
      char[] chars = str.toCharArray();
      Arrays.sort(chars);
      String s = new String(chars);
      if (!map.containsKey(s)) {
        map.put(s, new ArrayList<>());
      }
      map.get(s).add(str);
    }
    result.addAll(map.values());
    return result;
  }
}
