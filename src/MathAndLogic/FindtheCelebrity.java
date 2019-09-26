package MathAndLogic;

/**
 * In a party of N people, only one person is known to everyone. Such a person may be present in the party,
 * if yes, (s)he doesn’t know anyone in the party. We can only ask questions like “does A know B? “.
 * Find the stranger (celebrity) in minimum number of questions.
 * We can describe the problem input as an array of numbers/characters representing persons in the party.
 * We also have a hypothetical function HaveAcquaintance(A, B) which returns true if A knows B, false otherwise.
 * How can we solve the problem.
 */
public class FindtheCelebrity {

  static int MATRIX[][] = { { 0, 0, 1, 0, 0, 1},
      { 0, 0, 1, 1, 0, 0 },
      { 0, 0, 1, 1, 0, 0 },
      { 1, 0, 0, 0, 0, 0 },
      { 0, 0, 1, 1, 0, 0 },
      { 0, 0, 1, 1, 0, 0 } };

  public static void main(String[] args) {
    FindtheCelebrity o = new FindtheCelebrity();
    int n = 6;
    int result = o.findCelebrity(n);
    if (result == -1) {
      System.out.println("No Celebrity");
    } else {
      System.out.println("Celebrity ID " + result);
    }
  }

  public boolean haveAcquaintance(int a, int b) {
    return MATRIX[a][b] == 1;
  }

  public int findCelebrity(int n) {
    int a = 0;
    int b = n - 1;
    while (a < b) {
      if (haveAcquaintance(a, b)) {
        a++;
      } else {
        b--;
      }
    }
    for (int i = 0; i < n; i++) {
      if (i != a && (haveAcquaintance(a,i) || !haveAcquaintance(i,a))) {
        return -1;
      }
    }
    return a;
  }
}
