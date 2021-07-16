package arrays;

/**
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * All occurrences of a character must be replaced with another character while preserving the
 * order of characters. No two characters may map to the same character but a character may map to itself.
 * Example 1:
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 * Input: s = "paper", t = "title"
 * Output: true
 * Note:
 * You may assume both s and t have the same length.
 */
public class IsomorphicStrings {

  public static void main(String[] args) {
    IsomorphicStrings o = new IsomorphicStrings();
    System.out.println(o.isIsomorphic("ab", "aa"));
    System.out.println(o.isIsomorphic_self("egg", "add"));
    System.out.println(o.isIsomorphic("foo", "bar"));
    System.out.println(o.isIsomorphic("paper", "title"));
  }

  public boolean isIsomorphic_1ms(String s, String t) {

    char[] map= new char[256];
    boolean[] used=new boolean[256];
    char[] sc=s.toCharArray();

    char[] tc= t.toCharArray();

    for(int i=0; i<sc.length; i++){
      if(map[sc[i]]==0){
        if(used[tc[i]]) return false;

        map[sc[i]]=tc[i];
        used[tc[i]]=true;
      }else if(map[sc[i]] != tc[i]){
        return false;
      }
    }
    return true;
  }

  public boolean isIsomorphic_self(String s, String t) {
    if (s.length() != t.length()) {
      return false;
    }

    char[] sChars = new char[256];
    char[] tChars = new char[256];

    for (int i = 0; i < s.length(); i++) {
      char sc = s.charAt(i);
      char tc = t.charAt(i);
      if (sChars[sc] == Character.MIN_VALUE && tChars[tc] == Character.MIN_VALUE) {
        sChars[sc] = tc;
        tChars[tc] = sc;
        continue;
      }
      if (sChars[sc] != tc || tChars[tc] != sc) {
        return false;
      }
    }
    return true;
  }

  public boolean isIsomorphic(String s, String t) {
    char[] sIdx = new char[128];
    char[] tIdx = new char[128];
    char[] sArr = s.toCharArray();
    char[] tArr = t.toCharArray();
    for (int i = 0; i < sArr.length; i++) {
      if (tIdx[tArr[i]] != '\0' || sIdx[sArr[i]] != '\0') {
        if (sIdx[sArr[i]] != tArr[i]) {
          return false;
        }
      } else {
        tIdx[tArr[i]] = sArr[i];
        sIdx[sArr[i]] = tArr[i];
      }
    }
    return true;
  }
}
