package test;

/**
 * Author - Sivaprakash Nithyanandam Timestamp - 5/21/2022  11:27 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */


public class Test {

  public static void main(String[] args) {
    Test o = new Test();
    System.out.println(o.checkRecord(2));
  }

  public int checkRecord(int n) {
    int[] mem = new int[n+1];
    helper(n, 0, 0, 0, 0, mem);
    return mem[0];
  }

  private int helper(int n, int p, int a, int l, int total, int[] mem) {
    if (total == n) {
      return 1;
    }

    if (mem[total] > 0) {
      return mem[total];
    }

    int ans = helper(n, 1, 0, 0, total+1, mem);
    if (a < 1) {
      ans += helper(n, 0, a+1, 0, total+1, mem);
    }
    if (l < 2) {
      ans += helper(n, 0, 0, l+1, total+1, mem);
    }
    return mem[total] = ans;
  }

}
