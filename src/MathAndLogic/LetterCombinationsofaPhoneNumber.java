package MathAndLogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 * Example:
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class LetterCombinationsofaPhoneNumber {

  Map<Character, List<String>> map = new HashMap<Character, List<String>>(){{
    put('1', new ArrayList<String>(){{add("");}});
    put('2', new ArrayList<String>(){{add("a");add("b");add("c");}});
    put('3', new ArrayList<String>(){{add("d");add("e");add("f");}});
    put('4', new ArrayList<String>(){{add("g");add("h");add("i");}});
    put('5', new ArrayList<String>(){{add("j");add("k");add("l");}});
    put('6', new ArrayList<String>(){{add("m");add("n");add("o");}});
    put('7', new ArrayList<String>(){{add("p");add("q");add("r");add("s");}});
    put('8', new ArrayList<String>(){{add("t");add("u");add("v");}});
    put('9', new ArrayList<String>(){{add("w");add("x");add("y");add("z");}});
  }};


  public static void main(String[] args) {
    LetterCombinationsofaPhoneNumber o = new LetterCombinationsofaPhoneNumber();
    List<String> letterCombinations;

    letterCombinations = o.letterCombinations("");
    printList(letterCombinations);
    System.out.println("");

    letterCombinations = o.letterCombinations_faster("23");
    printList(letterCombinations);
    System.out.println("");

    letterCombinations = o.letterCombinations("234");
    printList(letterCombinations);
    System.out.println("");

    letterCombinations = o.letterCombinations("12");
    printList(letterCombinations);
    System.out.println("");

    letterCombinations = o.letterCombinations("67");
    printList(letterCombinations);
    System.out.println("");
  }

  private static void printList(List<String> letterCombinations) {
    for(String s : letterCombinations) {
      System.out.print(s + ",");
    }
  }

  public List<String> letterCombinations(String digits) {
    List<String> letterCombinations = new ArrayList<>();
    if (digits == null || digits.length() == 0) {
      return letterCombinations;
    }
    List<String> sb1 = map.get(digits.charAt(0));
    List<String> sb2;
    for (int i = 1; i < digits.length(); i++) {
      sb2 = map.get(digits.charAt(i));
      sb1 = letterCombinations(sb1, sb2);
    }
    return sb1;
  }

  private List<String> letterCombinations(List<String> sb1, List<String> sb2) {
    List<String> sb = new ArrayList<>(sb1.size() * sb2.size());
    for (int i = 0; i < sb1.size(); i++) {
      for (int j = 0; j < sb2.size(); j++) {
        sb.add(sb1.get(i) + sb2.get(j));
      }
    }
    return sb;
  }

  private Map<Character, String> letters = Map.of('2', "abc",
      '3', "def", '4', "ghi", '5', "jkl",
      '6', "mno", '7', "pqrs", '8', "tuv", '9', "wxyz");

  char[][] m = {{Character.MIN_VALUE},
      {'a','b','c'},
      {'d','e','f'},
      {'g','h','i'},
      {'j','k','l'},
      {'m','n','o'},
      {'p','q','r','s'},
      {'t','u','v'},
      {'w','x','y','z'}};

  public List<String> letterCombinations_faster(String digits) {
    List<String> letterCombinations = new ArrayList<>();
    if (digits == null || digits.length() == 0) {
      return letterCombinations;
    }
    letterCombinations(digits, 0, new StringBuilder(), letterCombinations);
    return letterCombinations;
  }

  private void letterCombinations(String digits, int pos, StringBuilder sb, List<String> letterCombinations) {
    if (pos == digits.length()) {
      letterCombinations.add(sb.toString());
    } else {
      int given = digits.charAt(pos) - '0';
      for (char c : m[given-1]) {
        sb.append(c);
        letterCombinations(digits, pos+1, sb, letterCombinations);
        sb.deleteCharAt(sb.length()-1);
      }
    }
  }

}
