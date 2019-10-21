package arrays;

/**
 * In some array arr, the values were in arithmetic progression: the values arr[i+1] - arr[i] are all equal for every 0 <= i < arr.length - 1.
 *
 * Then, a value from arr was removed that was not the first or last value in the array.
 *
 * Return the removed value.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [5,7,11,13]
 * Output: 9
 * Explanation: The previous array was [5,7,9,11,13].
 * Example 2:
 *
 * Input: arr = [15,13,12]
 * Output: 14
 * Explanation: The previous array was [15,14,13,12].
 *
 *
 * Constraints:
 *
 * 3 <= arr.length <= 1000
 * 0 <= arr[i] <= 10^5
 */
public class MissingNumberInArithmeticProgression {

    public static void main(String[] args) {
        MissingNumberInArithmeticProgression o = new MissingNumberInArithmeticProgression();
        System.out.println(o.missingNumber(new int[]{5, 7, 11, 13}));
        System.out.println(o.missingNumber(new int[]{5, 6, 8, 9}));
        System.out.println(o.missingNumber(new int[]{5, 7, 8, 9}));
        System.out.println(o.missingNumber(new int[]{15,13,12}));
        System.out.println(o.missingNumber(new int[]{15,14,13,11}));
    }
    public int missingNumber(int[] arr) {
        int tar;
        if (arr[0] > arr[arr.length-1]) {
            tar = Math.max(arr[1] - arr[0], arr[arr.length-1] - arr[arr.length-2]);
        } else {
            tar = Math.min(arr[1] - arr[0], arr[arr.length-1] - arr[arr.length-2]);
        }

        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i] + tar != arr[i+1]) {
                return arr[i] + tar;
            }
        }
        return arr[0];
    }
}
