package test;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 5/21/2022  11:27 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */


public class Test {

  @FunctionalInterface
  interface Square {
    int calculate(int x);
  }

  public static void main(String[] args) {
    int[] res = {2};
    Square s = x -> x * x;
    System.out.println(s.calculate(5));
  }
}
