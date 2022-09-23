package MathAndLogic;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/13/2021  7:33 AM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * https://leetcode.com/problems/guess-the-word/
 * <p>
 * This is an interactive problem.
 * <p>
 * You are given an array of unique strings wordlist where wordlist[i] is 6 letters long, and one
 * word in this list is chosen as secret.
 * <p>
 * You may call Master.guess(word) to guess a word. The guessed word should have type string and
 * must be from the original list with 6 lowercase letters.
 * <p>
 * This function returns an integer type, representing the number of exact matches (value and
 * position) of your guess to the secret word. Also, if your guess is not in the given wordlist, it
 * will return -1 instead.
 * <p>
 * For each test case, you have exactly 10 guesses to guess the word. At the end of any number of
 * calls, if you have made 10 or fewer calls to Master.guess and at least one of these guesses was
 * secret, then you pass the test case.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"], numguesses = 10
 * Output: You guessed the secret word correctly. Explanation: master.guess("aaaaaa") returns -1,
 * because "aaaaaa" is not in wordlist. master.guess("acckzz") returns 6, because "acckzz" is secret
 * and has all 6 matches. master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
 * master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches. master.guess("abcczz") returns
 * 4, because "abcczz" has 4 matches. We made 5 calls to master.guess and one of them was the
 * secret, so we pass the test case. Example 2:
 * <p>
 * Input: secret = "hamada", wordlist = ["hamada","khaled"], numguesses = 10 Output: You guessed the
 * secret word correctly.
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= wordlist.length <= 100 wordlist[i].length == 6 wordlist[i] consist of lowercase English
 * letters. All the strings of wordlist are unique. secret exists in wordlist. numguesses == 10
 */
public class GuesstheWord {

  public static void main(String[] args) {
    GuesstheWord o = new GuesstheWord();
    o.findSecretWord(
        new String[]{"bbbbbb", "cccccc", "dddddd", "eeeeee", "ffffff", "gggggg", "hhhhhh", "iiiiii",
            "kkkkkk", "llllll", "mmmmmm", "aaaaaa", "ccbazz", "eiowzz", "abcczz"}, o.new Master());
  }

  class Master {

    public int guess(String word) {
      String secret = "aaaaaa";
      return matches(word, secret);
    }
  }

  public void findSecretWord(String[] wordlist, Master master) {
    for (int i = 0, m = 0; i < 10 && m < 6; i++) {
      String guess = wordlist[new Random().nextInt(wordlist.length)];
      m = master.guess(guess);
      if (m == 6) {
        System.out.println("You passed testcase");
      }
      List<String> newWordlist = new ArrayList<>();
      for (String w : wordlist) {
        if (matches(w, guess) == m) {
          newWordlist.add(w);
        }
      }
      wordlist = newWordlist.toArray(new String[newWordlist.size()]);
    }
  }

  private int matches(String a, String b) {
    int count = 0;
    for (int i = 0; i < a.length(); i++) {
      if (a.charAt(i) == b.charAt(i)) {
        count++;
      }
    }
    return count;
  }

  public void findSecretWord_1(String[] wordlist, Master master) {
    Set<String> words = new HashSet<>(Arrays.asList(wordlist));
    while (true) {
      Iterator iterator = words.iterator();

      // Make a guess. If the secret is guessed, just return. If not, store that word in curWord.
      String curWord = (String) iterator.next();
      int currentMatching = master.guess(curWord);
      if (currentMatching == 6) {
        System.out.println("You passed testcase");
        return;
      }

      // Start a new loop and delete all strings that have a different matching count.
      // Let's say the secret and curWord have 3 matching count.
      // It is safe to delete all words that do not have 3 matching count.
      while (iterator.hasNext()) {
        if (!doTheyHaveSameMatchingCount(curWord, (String) iterator.next(), currentMatching)) {
          iterator.remove();
        }
      }
      words.remove(curWord);
    }
  }

  public boolean doTheyHaveSameMatchingCount(String curWord, String nextWord, int k) {
    int count = 0;
    for (int i = 0; i < 6; i++) {
      if (nextWord.charAt(i) == curWord.charAt(i)) {
        count++;
      }
    }
    return k == count;
  }

}
