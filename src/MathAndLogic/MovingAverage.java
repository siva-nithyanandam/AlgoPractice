package MathAndLogic;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 6/26/2021  11:54 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 * https://leetcode.com/problems/moving-average-from-data-stream/
 *
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * Implement the MovingAverage class:
 *
 * MovingAverage(int size) Initializes the object with the size of the window size.
 * double next(int val) Returns the moving average of the last size values of the stream.
 *
 *
 * Example 1:
 *
 * Input
 * ["MovingAverage", "next", "next", "next", "next"]
 * [[3], [1], [10], [3], [5]]
 * Output
 * [null, 1.0, 5.5, 4.66667, 6.0]
 *
 * Explanation
 * MovingAverage movingAverage = new MovingAverage(3);
 * movingAverage.next(1); // return 1.0 = 1 / 1
 * movingAverage.next(10); // return 5.5 = (1 + 10) / 2
 * movingAverage.next(3); // return 4.66667 = (1 + 10 + 3) / 3
 * movingAverage.next(5); // return 6.0 = (10 + 3 + 5) / 3
 *
 *
 * Constraints:
 *
 * 1 <= size <= 1000
 * -105 <= val <= 105
 * At most 104 calls will be made to next.
 */
public class MovingAverage {

  public static void main(String[] args) {
    MovingAverage o = new MovingAverage(3);
    System.out.println(o.next(1));
    System.out.println(o.next(10));
    System.out.println(o.next(3));
    System.out.println(o.next(5));
  }

  int[] numbers;
  int index;
  int total;
  int totalNumber;

  /** Initialize your data structure here. */
  public MovingAverage(int size) {
    numbers = new int[size];
    index = -1;
    total = 0;
    totalNumber = 0;
  }

  public double next(int val) {
    totalNumber = totalNumber==numbers.length ? totalNumber : totalNumber+1;
    index = (index+1)%numbers.length;
    total = total - numbers[index] + val;
    numbers[index] = val;

    return (double)total/totalNumber;
  }
}
