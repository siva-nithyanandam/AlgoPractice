package MathAndLogic;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/29/2021  6:31 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/explore/interview/card/google/67/sql-2/3044/
 *
 * Every valid email consists of a local name and a domain name, separated by the '@' sign. Besides lowercase letters, the email may contain one or more '.' or '+'.
 *
 * For example, in "alice@leetcode.com", "alice" is the local name, and "leetcode.com" is the domain name.
 * If you add periods '.' between some characters in the local name part of an email address, mail sent there will be forwarded to the same address without dots in the local name. Note that this rule does not apply to domain names.
 *
 * For example, "alice.z@leetcode.com" and "alicez@leetcode.com" forward to the same email address.
 * If you add a plus '+' in the local name, everything after the first plus sign will be ignored. This allows certain emails to be filtered. Note that this rule does not apply to domain names.
 *
 * For example, "m.y+name@email.com" will be forwarded to "my@email.com".
 * It is possible to use both of these rules at the same time.
 *
 * Given an array of strings emails where we send one email to each email[i], return the number of different addresses that actually receive mails.
 *
 *
 *
 * Example 1:
 *
 * Input: emails = ["test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"]
 * Output: 2
 * Explanation: "testemail@leetcode.com" and "testemail@lee.tcode.com" actually receive mails.
 * Example 2:
 *
 * Input: emails = ["a@leetcode.com","b@leetcode.com","c@leetcode.com"]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= emails.length <= 100
 * 1 <= emails[i].length <= 100
 * email[i] consist of lowercase English letters, '+', '.' and '@'.
 * Each emails[i] contains exactly one '@' character.
 * All local and domain names are non-empty.
 * Local names do not start with a '+' character.
 */
public class UniqueEmailAddresses {

  public static void main(String[] args) {
    UniqueEmailAddresses o = new UniqueEmailAddresses();
    System.out.println(o.numUniqueEmails(new String[]{"test.email+alex@leetcode.com","test.e.mail+bob.cathy@leetcode.com","testemail+david@lee.tcode.com"}));
  }

  Set<Integer> canonicalEmails = new HashSet();

  public int numUniqueEmails(String[] emails) {

    for(int i=0; i<emails.length; i++){
      canonicalEmails.add(canonicalEmails(emails[i]));
    }

    return canonicalEmails.size();
  }

  private int canonicalEmails(String email){
    //Unique solution for 2ms:
    //luckly the hashCode is enough for this problem in particular.
    // notice that the solution may fail if different combinations of
    // local+domain have the same hashCode;

    final int length = email.length();

    int h=0;

    //local part
    for (int i = 0; i < length; i++) {
      char ch = email.charAt(i);
      if (ch == '.') {
        continue;
      }
      if (ch == '+' || ch == '@') {
        break;
      }
      h = 31 * h + ch;
    }

    //domain part
    for(int i=length-1; i>=0; i--){
      char ch = email.charAt(i);

      if (ch == '@') {
        break;
      }
      h = 31 * h + ch;
    }

    return h;
  }
}
