package pointers;/**
 * Author - Sivaprakash Nithyanandam
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 *https://leetcode.com/problems/fruit-into-baskets/
 *
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 *
 * You start at any tree of your choice, then repeatedly perform the following steps:
 *
 * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 * Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
 *
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
 *
 * What is the total amount of fruit you can collect with this procedure?
 *
 *
 *
 * Example 1:
 *
 * Input: [1,2,1]
 * Output: 3
 * Explanation: We can collect [1,2,1].
 * Example 2:
 *
 * Input: [0,1,2,2]
 * Output: 3
 * Explanation: We can collect [1,2,2].
 * If we started at the first tree, we would only collect [0, 1].
 * Example 3:
 *
 * Input: [1,2,3,2,2]
 * Output: 4
 * Explanation: We can collect [2,3,2,2].
 * If we started at the first tree, we would only collect [1, 2].
 * Example 4:
 *
 * Input: [3,3,3,1,2,1,1,2,3,3,4]
 * Output: 5
 * Explanation: We can collect [1,2,1,1,2].
 * If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 *
 *
 * Note:
 *
 * 1 <= tree.length <= 40000
 * 0 <= tree[i] < tree.length
 */
public class FruitIntoBaskets {

  public static void main(String[] args) {
    FruitIntoBaskets o = new FruitIntoBaskets();
    System.out.println(o.totalFruit(new int[]{1,2,1}));//3
    System.out.println(o.totalFruit_faster(new int[]{1,0,1,1,4,1,4,1,2,3}));//6
    System.out.println(o.totalFruit(new int[]{3,3,3,1,2,1,1,2,3,3,4}));//5
  }

  public int totalFruit_faster(int[] tree) {
    if (tree.length == 0) {
      return 0;
    }
    int maxTotal = 0;
    int currentStart = 0;
    int previousPickLastIndex = -1;
    for (int i = 1; i < tree.length; i++) {
      if (tree[i] != tree[i-1]) {
        if (previousPickLastIndex >= 0 && tree[i] != tree[previousPickLastIndex]) {
          maxTotal = Math.max(maxTotal, i - currentStart);
          currentStart = previousPickLastIndex + 1;
        }
        previousPickLastIndex = i - 1;
      }
    }

    maxTotal = Math.max(maxTotal, tree.length - currentStart);
    return maxTotal;
  }

  public int totalFruit(int[] tree) {
    int x = tree[0];
    int y = -1;
    int count = 0;
    int xCount = 1;
    int yCount = 0;

    int max = 1;
    for (int i = 1; i < tree.length; i++) {
      if (tree[i] == x) {
        xCount++;
        count += yCount;
        yCount = 0;
      } else if (tree[i] == y || y == -1) {
        y = tree[i];
        yCount++;
        count += xCount;
        xCount = 0;
      } else {
        max = Math.max(max, count+xCount+yCount);
        if (x == tree[i-1]) {
          y = tree[i];
          yCount = 1;
          count = xCount;
          xCount = 0;
        } else {
          x = tree[i];
          xCount = 1;
          count = yCount;
          yCount = 0;
        }
      }
    }
    return Math.max(max, count+xCount+yCount);
  }
}
