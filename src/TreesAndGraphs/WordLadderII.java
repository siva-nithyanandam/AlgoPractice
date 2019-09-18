package TreesAndGraphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest
 * transformation sequence(s) from beginWord to endWord, such that:
 * Only one letter can be changed at a time
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * Note:
 * Return an empty list if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * Example 1:
 * Input:
 * beginWord = "hit",
 * endWord = "cog",
 * wordList = ["hot","dot","dog","lot","log","cog"]
 *
 * Output:
 * [
 *   ["hit","hot","dot","dog","cog"],
 *   ["hit","hot","lot","log","cog"]
 * ]
 * Example 2:
 * Input:
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log"]
 *
 * Output: []
 *
 * Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */
public class WordLadderII {

  public static void main(String[] args) {
    WordLadderII wl = new WordLadderII();
    List<String> wordList = new ArrayList<>();
    wordList.add("hot");
    wordList.add("dot");
    wordList.add("dog");
    wordList.add("lot");
    wordList.add("log");
    wordList.add("cog");
    List<List<String>> res = wl.ladderLength("hit", "cog", wordList);

    wordList.clear();
    wordList.add("hot");
    wordList.add("dot");
    wordList.add("dog");
    wordList.add("lot");
    wordList.add("log");
    res = wl.ladderLength("hit", "cog", wordList);
  }

  public List<List<String>> ladderLength(String beginWord, String endWord, List<String> wordList) {
    List<List<String>> res = new ArrayList<>();
    if (beginWord == null || endWord == null || wordList == null) {
      return res;
    }
    Map<String, Integer> beginMap = new HashMap<>();
    beginMap.put(beginWord, 0);

    Map<String, Integer> endMap = new HashMap<>();
    Map<String, Integer> dict = new HashMap<>();
    for (int i = 0; i < wordList.size(); i++) {
      dict.put(wordList.get(i), i+1);
      if (wordList.get(i).equals(endWord)) {
        endMap.put(endWord, i+1);
      }
    }
    if (endMap.size() == 0) return res;
    int[] track = new int[wordList.size()+1];
    Arrays.fill(track,-1);

    ladderLength(beginMap, endMap, dict, 1, track);
    return res;
  }

  public Integer ladderLength(Map<String, Integer> beginMap, Map<String, Integer> endMap,
      Map<String, Integer> dict, int count, int[] track) {
    if (beginMap.isEmpty()) {
      return -1;
    }
    for (String s : beginMap.keySet()) {
      dict.remove(s);
    }
    Map<String, Integer> nextMap = new HashMap<>();
    for (Map.Entry<String, Integer> entry : beginMap.entrySet()) {
      String k = entry.getKey();
      int v = entry.getValue();

      char[] sArr = k.toCharArray();
      for (int i = 0; i < sArr.length; i++) {
        char temp = sArr[i];
        for (char c = 'a'; c <= 'z'; c++) {
          sArr[i] = c;
          String newWord = new String(sArr);
          if (dict.containsKey(newWord)) {
            track[dict.get(newWord)] = v;
            if (endMap.containsKey(newWord)) {
              //return dict.get(newWord);
            }
            nextMap.put(newWord, dict.get(newWord));
          }
        }
        sArr[i] = temp;
      }
    }
    return ladderLength(nextMap, endMap, dict, count+1, track);
  }
}
