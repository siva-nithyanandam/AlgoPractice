package MathAndLogic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 * Explanation: Return true because "leetcode" can be segmented as "leet code".
 * Example 2:
 *
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 * Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 *              Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 */
public class WordBreak {

    public static void main(String[] args) {
        WordBreak o = new WordBreak();
        System.out.println(o.wordBreak("leetcode", Arrays.asList("leet", "code")));
        System.out.println(o.wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(o.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
        System.out.println(o.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict.size() == 0) {
            return false;
        }
        Map<String, Boolean> mem = new HashMap<String, Boolean>();
        return helper(s, wordDict, mem);
    }

    private boolean helper(String s, List<String> wordDict, Map<String, Boolean> mem) {
        if (s.length() == 0) {
            return true;
        }
        if (mem.containsKey(s)) {
            return mem.get(s);
        }
        for (int i = 1; i <= s.length(); i++) {
            if (wordDict.contains(s.substring(0, i)) && helper(s.substring(i), wordDict, mem)) {
                mem.put(s.substring(i), true);
                return true;
            }
        }
        return false;
    }
}
