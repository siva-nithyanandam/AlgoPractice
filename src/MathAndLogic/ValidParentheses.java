package MathAndLogic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Note that an empty string is also considered valid.
 * Example 1:
 * Input: "()"
 * Output: true
 * Example 2:
 * Input: "()[]{}"
 * Output: true
 * Example 3:
 * Input: "(]"
 * Output: false
 * Example 4:
 * Input: "([)]"
 * Output: false
 * Example 5:
 * Input: "{[]}"
 * Output: true
 */
public class ValidParentheses {

  Map<Character, Character> map = new HashMap<Character, Character>(){{
    put(')', '(');
    put('}', '{');
    put(']', '[');
    put('>', '<');
  }};

  public static void main(String[] args) {
    ValidParentheses o = new ValidParentheses();
    System.out.println(o.isValid(""));
    System.out.println(o.isValid("()"));
    System.out.println(o.isValid("()[]{}"));
    System.out.println(o.isValid("(]"));
    System.out.println(o.isValid("([)]"));
    System.out.println(o.isValid("{[]}"));
  }

  public boolean isValid_faster(String s) {
    char[] stack = new char[s.length()+1];
    int top = 1;
    for (char c : s.toCharArray()) {
      if (c == '(' || c == '[' || c =='{') {
        stack[top++] = c;
      } else if (c == ')') {
        if (stack[--top] != '(') {
          return false;
        }
      } else if (c == ']') {
        if (stack[--top] != '[') {
          return false;
        }
      } else if (c == '}') {
        if (stack[--top] != '{') {
          return false;
        }
      }
    }
    return top == 1;
  }

  public boolean isValid(String s) {
    int[] opens = new int[s.length()];
    Arrays.fill(opens, -1);
    int currOpen = -1;
    char[] chars = s.toCharArray();
    for (int j = 0; j < chars.length; j++) {
      if (map.containsKey(chars[j])) {
        if (currOpen == -1 || map.get(chars[j]) != chars[currOpen]) {
          return false;
        }
        currOpen = opens[currOpen];
      } else {
        opens[j] = currOpen;
        currOpen = j;
      }
    }
    return currOpen == -1;
  }
}
