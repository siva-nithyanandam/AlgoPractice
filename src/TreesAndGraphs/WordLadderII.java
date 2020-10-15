package TreesAndGraphs;

import java.util.*;

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

/**
 * Using BFS and DFS. Complex logic. Got to go through
 * https://www.youtube.com/watch?v=Tlq4x5ln9Rg
 */
public class WordLadderII {

  public static void main(String[] args) {
    WordLadderII wl = new WordLadderII();
    List<List<String>> res;

    List<String> wordList = new ArrayList<>();
    wordList.add("hot");
    wordList.add("dot");
    wordList.add("dog");
    wordList.add("lot");
    wordList.add("log");
    wordList.add("cog");
    res = wl.findLadders("hit", "cog", wordList);
    printRes(res);

    wordList.clear();
    wordList.add("hot");
    wordList.add("dot");
    wordList.add("dog");
    wordList.add("lot");
    wordList.add("log");
    res = wl.findLadders("hit", "cog", wordList);
    printRes(res);

    wordList.clear();
    wordList.add("ted");
    wordList.add("tex");
    wordList.add("red");
    wordList.add("tax");
    wordList.add("tad");
    wordList.add("den");
    wordList.add("rex");
    wordList.add("pee");
    res = wl.findLadders("red", "tax", wordList);
    printRes(res);

    wordList.clear();
    wordList.add("hot");
    wordList.add("dog");
    res = wl.findLadders("hot", "dog", wordList);
    printRes(res);
  }

  private static void printRes(List<List<String>> res) {
    for (List<String> l : res) {
      for (String s : l) {
        System.out.print(s + ",");
      }
      System.out.println();
    }
  }

  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    List<List<String>> res = new ArrayList<>();
    Set<String> dict = new HashSet<>();
    for (int i = 0; i < wordList.size(); i++) {
      dict.add(wordList.get(i));
    }
    if (!dict.contains(endWord)) {
      return res;
    }
    Set<String> beginSet = new HashSet<>();
    beginSet.add(beginWord);

    Set<String> endSet = new HashSet<>();
    endSet.add(endWord);

    Map<String, List<String>> bfsMap = new HashMap<>();

    findLadders(beginSet, endSet, bfsMap, dict, 1, res);
    return res;
  }

  private void findLadders(Set<String> beginSet, Set<String> endSet, Map<String,
      List<String>> bfsMap, Set<String> dict, int level,
      List<List<String>> res) {

    if (beginSet.isEmpty() || !res.isEmpty()) {
      return;
    }
    for (String s : beginSet) {
      dict.remove(s);
    }
    Set<String> nextSet = new HashSet<>();
    for (String s : beginSet) {
      char[] sArr = s.toCharArray();
      for (int i = 0; i < sArr.length; i++) {
        char temp = sArr[i];
        for (char c = 'a'; c <= 'z'; c++) {
          sArr[i] = c;
          String newWord = new String(sArr);
          if (dict.contains(newWord)) {
            putInBfsMap(newWord, bfsMap, s);
            if (endSet.contains(newWord)) {
              List<String> subRes = new ArrayList<>();
              subRes.add(s);
              subRes.add(newWord);
              helper(res, bfsMap, subRes, s);
            }
            nextSet.add(newWord);
          }
        }
        sArr[i] = temp;
      }
    }
    findLadders(nextSet, endSet, bfsMap, dict, level+1, res);
  }

  private void helper(List<List<String>> res, Map<String, List<String>> bfsMap, List<String> subRes,
      String word) {
    List<String> l = bfsMap.get(word);
    if (l != null) {
      for (String s : l) {
        subRes.add(0, s);
        helper(res, bfsMap, subRes, s);
        subRes.remove(s);
      }
    } else {
      res.add(new ArrayList<>(subRes));
    }
  }

  private void putInBfsMap(String s, Map<String, List<String>> bfsMap, String newWord) {
    if (bfsMap.get(s) == null) {
      List<String> l = new ArrayList<>();
      l.add(newWord);
      bfsMap.put(s, l);
    } else {
      bfsMap.get(s).add(newWord);
    }
  }
}
