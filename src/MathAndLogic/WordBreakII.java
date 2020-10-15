package MathAndLogic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add
 * spaces in s to construct a sentence where each word is a valid dictionary word. Return all such
 * possible sentences.
 * <p>
 * Note:
 * <p>
 * The same word in the dictionary may be reused multiple times in the segmentation. You may assume
 * the dictionary does not contain duplicate words. Example 1:
 * <p>
 * Input: s = "catsanddog" wordDict = ["cat", "cats", "and", "sand", "dog"] Output: [ "cats and
 * dog", "cat sand dog" ] Example 2:
 * <p>
 * Input: s = "pineapplepenapple" wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output: [ "pine apple pen apple", "pineapple pen apple", "pine applepen apple" ] Explanation:
 * Note that you are allowed to reuse a dictionary word. Example 3:
 * <p>
 * Input: s = "catsandog" wordDict = ["cats", "dog", "sand", "and", "cat"] Output: []
 */
public class WordBreakII {

  public static void main(String[] args) {
    WordBreakII o = new WordBreakII();
    List<String> res = o.wordBreak_own("aaaaaa",
            new HashSet<>(Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
    for (String r : res) {
      System.out.println(r);
    }
    res = o.wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"));
    for (String r : res) {
      System.out.println(r);
    }
  }

    public List<String> wordBreak_own(String s, Set<String> wordDict) {
        return DFS(s, wordDict, new HashMap<String, LinkedList<String>>());
    }

    // DFS function returns an array including all substrings derived from s.
    List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>>map) {
        if (map.containsKey(s))
            return map.get(s);

        LinkedList<String>res = new LinkedList<String>();
        if (s.length() == 0) {
            res.add("");
            return res;
        }
        for (String word : wordDict) {
            if (s.startsWith(word)) {
                List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
                for (String sub : sublist)
                    res.add(word + (sub.isEmpty() ? "" : " ") + sub);
            }
        }
        map.put(s, res);
        return res;
    }

  public List<String> wordBreak(String s, List<String> wordDict) {
    List[] cache = new List[s.length() + 1];
    cache[s.length()] = Collections.singletonList("");

    return DFS(s, 0, new Trie(wordDict), cache);
  }

  private List<String> DFS(String s, int start, Trie root, List[] cache) {
    if (cache[start] != null) {
      return cache[start];
    }

    List<String> result = new LinkedList<String>();
    Trie curr = root;

    for (int pos = start; pos < s.length(); pos++) {
      curr = curr.children[s.charAt(pos) - 'a'];

      if (curr == null) break;

      if (curr.word != null) {
        List<String> lookAhead = DFS(s, pos + 1, root, cache);

        for (String word : lookAhead) {
          result.add(curr.word + (word.isEmpty() ? "" : " " + word));
        }
      }
    }

    cache[start] = result;

    return result;
  }

  private class Trie {
    private Trie[] children = new Trie[26];
    private String word;

    Trie() { }

    Trie(List<String> wordList) {
      if (wordList != null) {
        for (String word : wordList) {
          this.add(word);
        }
      }
    }

    private void add(String word) {
      Trie curr = this;

      for (char letter : word.toCharArray()) {
        if (curr.children[letter - 'a'] == null) {
          curr.children[letter - 'a'] = new Trie();
        }

        curr = curr.children[letter - 'a'];
      }

      curr.word = word;
    }
  }
}
