package MathAndLogic;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/11/2022  10:55 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 *
 */
public class CalculateAmountPaidinTaxes {

  public static void main(String[] args) {
    CalculateAmountPaidinTaxes o = new CalculateAmountPaidinTaxes();
    System.out.println(o.calculateTax(new int[][]{{3,50},{7,10},{12,25}}, 10));
  }

  public double calculateTax(int[][] brackets, int income) {

    int i = 0;
    double tax = 0;
    int prevAmount = 0;

    do {
      int amount = Math.min(brackets[i][0], income) - prevAmount;
      prevAmount = brackets[i][0];
      tax += amount * (double)(brackets[i][1]) / 100;
    } while (income > brackets[i++][0]);
    return tax;
  }
}
