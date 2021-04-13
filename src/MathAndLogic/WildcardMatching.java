package MathAndLogic;

/**
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 * Example 1:
 *
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input:
 * s = "aa"
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * Example 3:
 *
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 * Example 4:
 *
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
 * Example 5:
 *
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 */
public class WildcardMatching {
    public static void main(String[] args) {
        WildcardMatching o = new WildcardMatching();
        System.out.println(o.isMatch("aab", "c*a*b"));//false
        System.out.println(o.isMatch_faster("abefcdgiescdfimde", "ab*cd?i*de"));//true
        System.out.println(o.isMatch("a", "aa"));//false
        System.out.println(o.isMatch("aa", "a"));//false
        System.out.println(o.isMatch_faster("aa", "*"));//true
        System.out.println(o.isMatch("cb", "?a"));//false
        System.out.println(o.isMatch_own("adceb", "*a*b"));//true
        System.out.println(o.isMatch_own("acdcb", "a*c?b"));//false
        System.out.println(o.isMatch_own("", "*"));//false
    }

    public boolean isMatch_own(String s, String p) {
        Boolean[][] res = new Boolean[s.length()+1][p.length()+1];
        return isMatch(s.toCharArray(), 0, p.toCharArray(), 0, res);
    }

    private boolean isMatch(char[] strArr, int i, char[] patArr, int j, Boolean[][] res) {
        if (res[i][j] != null) {
            return res[i][j];
        }
        if (j >= patArr.length && i >= strArr.length) {
            return true;
        }
        if (j >= patArr.length) {
            return false;
        }
        if (i >= strArr.length) {
            for (int k = j; k < patArr.length; k++) {
                if(patArr[k] != '*') {
                    return false;
                }
            }
            return true;
        }

        boolean b = false;
        if (i < strArr.length && strArr[i] == patArr[j]) {
            b = isMatch(strArr, i+1, patArr, j+1, res);
        } else if (patArr[j] == '?' && i < strArr.length) {
            b = isMatch(strArr, i+1, patArr, j+1, res);
        } else if (patArr[j] == '*') {
            b = isMatch(strArr, i+1, patArr, j, res)
                || isMatch(strArr, i, patArr, j+1, res);
        }
        res[i][j] = b;
        return b;
    }

    public boolean isMatch_faster(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        int sIdx = 0, pIdx = 0;
        int starIdx = -1, sTmpIdx = -1;

        while (sIdx < sLen) {
            if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))){
                sIdx++;
                pIdx++;
            }
            // If pattern character = '*'
            else if (pIdx < pLen && p.charAt(pIdx) == '*') {
                starIdx = pIdx;
                sTmpIdx = sIdx;
                pIdx = starIdx + 1;
            }
            // If pattern character != string character or pattern is used up and there was no '*' character in pattern
            else if (starIdx == -1) return false;
                // If pattern character != string character or pattern is used up and there was '*' in pattern before
            else {
                // Backtrack: check the situation when '*' matches one more character
                pIdx = starIdx + 1;
                sIdx = sTmpIdx + 1;
                sTmpIdx = sIdx;
            }
        }

        // The remaining characters in the pattern must all be '*' characters, otherwise, false
        for(int i = pIdx; i < pLen; i++)
            if (p.charAt(i) != '*') return false;
        return true;
    }


    public boolean isMatch_youtube(String s, String p) {
        char[] str = s.toCharArray();
        char[] pattern = p.toCharArray();

        //replace multiple * with one *
        //e.g a**b***c --> a*b*c
        int writeIndex = 0;
        boolean isFirst = true;
        for ( int i = 0 ; i < pattern.length; i++) {
            if (pattern[i] == '*') {
                if (isFirst) {
                    pattern[writeIndex++] = pattern[i];
                    isFirst = false;
                }
            } else {
                pattern[writeIndex++] = pattern[i];
                isFirst = true;
            }
        }

        boolean T[][] = new boolean[str.length + 1][writeIndex + 1];

        if (writeIndex > 0 && pattern[0] == '*') {
            T[0][1] = true;
        }

        T[0][0] = true;

        for (int i = 1; i < T.length; i++) {
            for (int j = 1; j < T[0].length; j++) {
                if (pattern[j-1] == '?' || str[i-1] == pattern[j-1]) {
                    T[i][j] = T[i-1][j-1];
                } else if (pattern[j-1] == '*'){
                    T[i][j] = T[i-1][j] || T[i][j-1];
                }
            }
        }

        return T[str.length][writeIndex];
    }

    public boolean isMatch(String s, String p) {
        return isMatch(s.toCharArray(), 0, p.toCharArray(), 0);
    }

    private boolean isMatch(char[] sChars, int sIdx, char[] pChars, int pIdx) {
        if (sIdx >= sChars.length && pIdx >= pChars.length) {
            return true;
        } else if (sIdx >= sChars.length) {
            if (pChars[pIdx] == '*') {
                return true;
            } else {
                return false;
            }
        } else if (pIdx >= pChars.length) {
            return false;
        }

        if (pChars[pIdx] == sChars[sIdx] || pChars[pIdx] == '?') {
            return isMatch(sChars, sIdx + 1, pChars, pIdx + 1);
        } else if (pChars[pIdx] == '*') {
            return !isMatch(sChars, sIdx + 1, pChars, pIdx)
                    || !isMatch(sChars, sIdx, pChars, pIdx + 1);
        }
        return false;
    }

    public boolean isMatch_fastest(String s, String p) {
        int sp=0;
        int pp=0;
        int match=0;
        int star=-1;

        while(sp<s.length()){
            if(pp<p.length()&& (s.charAt(sp)==p.charAt(pp)||p.charAt(pp)=='?'  ) ){
                sp++;
                pp++;
            }else if(pp<p.length() && p.charAt(pp)=='*'){
                star =pp;
                match = sp;
                pp++;
            }else if (star !=-1){
                pp=star+1;
                match++;
                sp=match;
            }else{
                return false;
            }
        }

        while(pp<p.length() && p.charAt(pp)=='*') pp++;
        return pp==p.length();
    }
}
