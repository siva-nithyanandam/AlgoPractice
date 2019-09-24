package MathAndLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Numbers can be regarded as product of its factors. For example,
 * 8 = 2 x 2 x 2;
 *   = 2 x 4.
 * Write a function that takes an integer n and return all possible combinations of its factors.
 * Note:
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 */

/**
 * Get factors upto the given square root, and recursive call for quotient.
 */
public class FactorCombinations {
  public static void main(String[] args) {
    FactorCombinations o = new FactorCombinations();
    List<List<Integer>> res = new ArrayList<>();
    o.getFactors(8, res, new Stack<>());
    printRes(res);

    res.clear();
    o.getFactors(10, res, new Stack<>());
    printRes(res);

    res.clear();
    o.getFactors(100, res, new Stack<>());
    printRes(res);

    res.clear();
    o.getFactors(72, res, new Stack<>());
    printRes(res);
  }

  private static void printRes(List<List<Integer>> res) {
    for (List<Integer> list : res) {
      for (Integer i : list) {
        System.out.print(i + ",");
      }
      System.out.println();
    }
    System.out.println();
  }

  private void getFactors(int num, List<List<Integer>> res, Stack<Integer> subRes) {
    if (num == 1) {
      return;
    }
    List<Integer> subSubRes;
    for (int i = 2; i <= Math.sqrt(num); i++) {
      if (num % i == 0) {
        subSubRes = new ArrayList<>();
        subSubRes.addAll(subRes);
        subSubRes.add(i);
        subSubRes.add(num/i);
        res.add(subSubRes);

        subRes.add(i);
        getFactors(num/i, res, subRes);
        subRes.pop();
      }
    }
  }
}
