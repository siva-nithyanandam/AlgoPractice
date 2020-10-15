package TreesAndGraphs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of
 * shortest transformation sequence from beginWord to endWord, such that: Only one letter can be
 * changed at a time. Each transformed word must exist in the word list. Note that beginWord is not
 * a transformed word. Note: Return 0 if there is no such transformation sequence. All words have
 * the same length. All words contain only lowercase alphabetic characters. You may assume no
 * duplicates in the word list. You may assume beginWord and endWord are non-empty and are not the
 * same. Example 1: Input: beginWord = "hit", endWord = "cog", wordList =
 * ["hot","dot","dog","lot","log","cog"]
 * <p>
 * Output: 5 Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" ->
 * "cog", return its length 5.
 * <p>
 * Example 2: Input: beginWord = "hit" endWord = "cog" wordList = ["hot","dot","dog","lot","log"]
 * <p>
 * Output: 0 Explanation: The endWord "cog" is not in wordList, therefore no possible
 * transformation.
 */
public class WordLadder {

  static class WordTracker {

    private final String word;
    private final int step;

    public WordTracker(final String word, final int step) {
      this.word = word;
      this.step = step;
    }
  }

  public static void main(String[] args) {
    WordLadder wl = new WordLadder();
    List<String> wordList = new ArrayList<>();
    wordList.add("hot");
    wordList.add("dot");
    wordList.add("dog");
    wordList.add("lot");
    wordList.add("log");
    wordList.add("cog");
    System.out.println(wl.ladderLength("hit", "cog", wordList));

    wordList.clear();
    wordList.add("hot");
    wordList.add("dot");
    wordList.add("dog");
    wordList.add("lot");
    wordList.add("log");
    System.out.println(wl.ladderLength("hit", "cog", wordList));
  }

  public int ladderLength(String beginWord, String endWord, List<String> wordList) {
    if (beginWord == null || endWord == null || wordList == null) {
      return 0;
    }
    Set<String> beginSet = new HashSet<String>();
    beginSet.add(beginWord);

    Set<String> endSet = new HashSet<>();
    endSet.add(endWord);

    Set<String> dict = new HashSet<>(wordList);
    if (!dict.contains(endWord)) {
      return 0;
    }
    return ladderLength(beginSet, endSet, dict, 1);
  }

  public int ladderLength(Set<String> beginSet, Set<String> endSet, Set<String> dict, int count) {
    if (beginSet.isEmpty() || endSet.isEmpty()) {
      return 0;
    }
    dict.removeAll(beginSet);
    Set<String> nextSet = new HashSet<>();
    for (String s : beginSet) {
      char[] arr = s.toCharArray();
      for (int i = 0; i < arr.length; i++) {
        char temp = arr[i];
        for (char c = 'a'; c <= 'z'; c++) {
          arr[i] = c;
          String newWord = new String(arr);
          if (dict.contains(newWord)) {
            if (endSet.contains(newWord)) {
              return count + 1;
            }
            nextSet.add(newWord);
          }
        }
        arr[i] = temp;
      }
    }
    return endSet.size() < nextSet.size() ?
        ladderLength(endSet, nextSet, dict, count + 1)
        : ladderLength(nextSet, endSet, dict, count + 1);
  }

  public int ladderLength_2(String beginWord, String endWord, List<String> wordList) {
    if (beginWord == null || endWord == null || wordList == null) {
      return 0;
    }
    WordTracker wordTracker = new WordTracker(beginWord, 1);
    Queue<WordTracker> queue = new LinkedList<WordTracker>();
    queue.add(wordTracker);
    while (!queue.isEmpty()) {
      WordTracker wt = queue.remove();
      String word = wt.word;
      if (endWord.equals(word)) {
        return wt.step;
      }
      wordList.remove(word);
      Iterator<String> itr = wordList.iterator();
      while (itr.hasNext()) {
        String s = itr.next();
        if (isDifferByOneLetter(word, s)) {
          queue.add(new WordTracker(s, wt.step + 1));
          itr.remove();
        }
      }
    }
    return 0;
  }

  private boolean isDifferByOneLetter(String s1, String s2) {
    char[] c1 = s1.toCharArray();
    char[] c2 = s2.toCharArray();
    int count = 0;
    for (int i = 0; i < c1.length; i++) {
      if (c1[i] != c2[i]) {
        count++;
      }
    }
    return count == 1;
  }

  public int ladderLength_1(String beginWord, String endWord, List<String> wordList) {
    if (beginWord == null || endWord == null || wordList == null) {
      return 0;
    }
    WordTracker wordTracker = new WordTracker(beginWord, 1);
    Queue<WordTracker> queue = new LinkedList<WordTracker>();
    queue.add(wordTracker);
    while (!queue.isEmpty()) {
      WordTracker wt = queue.remove();
      String word = wt.word;
      if (endWord.equals(word)) {
        return wt.step;
      }
      char[] arr = word.toCharArray();
      for (int i = 0; i < arr.length; i++) {
        for (char c = 'a'; c <= 'z'; c++) {
          char temp = arr[i];
          if (arr[i] != c) {
            arr[i] = c;
          }
          String newWord = new String(arr);
          if (wordList.contains(newWord)) {
            queue.add(new WordTracker(newWord, wt.step + 1));
            wordList.remove(newWord);
          }
          arr[i] = temp;
        }
      }
    }
    return 0;
  }
}
