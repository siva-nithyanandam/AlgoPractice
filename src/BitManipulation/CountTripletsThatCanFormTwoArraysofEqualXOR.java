package BitManipulation;
/**
 * @time 5/30/24-9:34 AM
 * @author sivaprakashnithyanandam
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/description/?envType=daily-question&envId=2024-05-30
 *
 * Given an array of integers arr.
 *
 * We want to select three indices i, j and k where (0 <= i < j <= k < arr.length).
 *
 * Let's define a and b as follows:
 *
 * a = arr[i] ^ arr[i + 1] ^ ... ^ arr[j - 1]
 * b = arr[j] ^ arr[j + 1] ^ ... ^ arr[k]
 * Note that ^ denotes the bitwise-xor operation.
 *
 * Return the number of triplets (i, j and k) Where a == b.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [2,3,1,6,7]
 * Output: 4
 * Explanation: The triplets are (0,1,2), (0,2,2), (2,3,4) and (2,4,4)
 * Example 2:
 *
 * Input: arr = [1,1,1,1,1]
 * Output: 10
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 300
 * 1 <= arr[i] <= 108
 */
public class CountTripletsThatCanFormTwoArraysofEqualXOR {

    public static void main(String[] args) {
        CountTripletsThatCanFormTwoArraysofEqualXOR o = new CountTripletsThatCanFormTwoArraysofEqualXOR();
        System.out.println(o.countTriplets_1ms(new int[]{2,3,1,6,7}));
    }

    public int countTriplets_1ms(int[] nums) {

        int count = 0;

        for(int i = 0; i < nums.length; i++){
            int xor = 0;
            for(int j = i; j < nums.length; j++){
                xor ^= nums[j];
                if(xor == 0) {
                    count += (j - i);
                }
            }
        }

        return count;
    }


    public int countTriplets_4ms(int[] arr) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        map.computeIfAbsent(0, none -> new ArrayList<>()).add(-1);

        int xor = 0;

        int ans = 0;

        for (int i = 0; i < arr.length; i++) {
            xor ^= arr[i];

            if (map.containsKey(xor)) {
                for (int x : map.get(xor)) {
                    ans += i - x - 1;
                }
            }
            map.computeIfAbsent(xor, none -> new ArrayList<>()).add(i);
        }
        return ans;
    }
}
