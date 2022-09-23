package arrays;
/**
 * Author - Sivaprakash Nithyanandam Timestamp - 7/10/2021  3:29 PM
 *
 * @see <a href="https://github.com/trysivaprakash">trysivaprakash</a>
 */

/**
 *
 */
public class Maximumsubarraysum {

  public static void main(String[] args) {
    Maximumsubarraysum o = new Maximumsubarraysum();

    // Test case 1
    int arr1[] = {-2, -3, 4, -1, -2, 1, 5, -3};
    int n1 = arr1.length;
    System.out.println(maximumSumSubarray(arr1, n1));

    // Test case 2
    int arr2[] = {4, -8, 9, -4, 1, -8, -1, 6};
    int n2 = arr2.length;
    System.out.println(maximumSumSubarray(arr2, n2));
  }

  static int maximumSumSubarray(int arr[], int n) {
    // Initialize minimum
    // prefix sum to 0.
    int min_prefix_sum = 0;

    // Initialize maximum subarray
    // sum so far to -infinity.
    int res = Integer.MIN_VALUE;

    // Initialize and compute
    // the prefix sum array.
    int prefix_sum[] = new int[n];
    prefix_sum[0] = arr[0];
    for (int i = 1; i < n; i++) {
      prefix_sum[i] = prefix_sum[i - 1] + arr[i];
    }

    // loop through the array, keep
    // track of minimum prefix sum so
    // far and maximum subarray sum.
    for (int i = 0; i < n; i++) {
      res = Math.max(res, prefix_sum[i] - min_prefix_sum);
      min_prefix_sum = Math.min(min_prefix_sum, prefix_sum[i]);
    }

    return res;
  }
}
