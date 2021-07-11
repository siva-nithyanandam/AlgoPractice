package MathAndLogic;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words.
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
        System.out.println(o.wordBreak_faster("acccbccb", Arrays.asList("cc","bc","ac","ca")));
        System.out.println(o.wordBreak("a", Arrays.asList("a")));
        System.out.println(o.wordBreak1("leetcode", Arrays.asList("leet", "code")));
        System.out.println(o.wordBreak("applepenapple", Arrays.asList("apple", "pen")));
        System.out.println(o.wordBreak("catsandog", Arrays.asList("cats", "dog", "sand", "and", "cat")));
        System.out.println(o.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa" +
                        "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab",
                Arrays.asList("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")));
    }

    public boolean wordBreak_faster(String s, List<String> wordDict) {
        if(s == null || s.length()==0)
            return false;
        boolean[] visited = new boolean[s.length()];
        return dfs(s, 0, wordDict, visited);
    }

    private boolean dfs(String s, int start, List<String> wordDict, boolean[] visited){
        if(start == s.length())
            return true;
        if(visited[start])
            return false;
        visited[start] = true;
        for(String word : wordDict) {
            if(s.startsWith(word, start)){
                if(dfs(s, start + word.length(), wordDict, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean wordBreak1(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    public boolean wordBreak_another(String s, List<String> wordDict) {
        HashSet<String> set = new HashSet<String>();
        int maxWordSize = 0;
        for (String string : wordDict) {
            set.add(string);
            maxWordSize = Math.max(maxWordSize, string.length());
        }
        maxWordSize = Math.min(maxWordSize, s.length());
        return wordBreak(s, set, 0, new boolean[s.length()], maxWordSize);
    }

    public boolean wordBreak(String s, Set<String> wordDict, int index, boolean[] dp, int maxWordSize) {
        if (index >= dp.length) {
            return true;
        }
        int len=Math.min(index+maxWordSize,dp.length);
        for (int i = index; i < len; i++) {

            if (!dp[i] && wordDict.contains(s.substring(index, i + 1))) {
                dp[i] = true;
                wordBreak(s, wordDict, i + 1, dp, maxWordSize);

            }
        }
        return dp[dp.length - 1];

    }


    public boolean wordBreak(String s, List<String> wordDict) {
        if (s == null || wordDict.size() == 0) {
            return false;
        }
        boolean[][] b = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            b[i][i] = wordDict.contains(s.substring(i, i+1));
        }
        for (int len = 2; len <= s.length(); len++) {
            for (int j = 0; j <= s.length()-len; j++) {
                String a = s.substring(j, j+len);
                if (wordDict.contains(a)) {
                    b[j][j+len-1] = true;
                } else {
                    for (int split = j; split < j+len-1; split++) {
                        if (b[j][split] && b[split+1][j+len-1]) {
                            b[j][j+len-1] = true;
                            break;
                        }
                    }
                }
            }
        }
        return b[0][s.length()-1];
    }

    public boolean wordBreak_time_exceeded(String s, List<String> wordDict) {
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
